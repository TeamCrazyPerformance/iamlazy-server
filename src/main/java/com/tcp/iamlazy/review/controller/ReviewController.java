package com.tcp.iamlazy.review.controller;

import com.tcp.iamlazy.auth.controller.payload.ApiResponse;
import com.tcp.iamlazy.configuration.security.CurrentUser;
import com.tcp.iamlazy.configuration.security.UserPrincipal;
import com.tcp.iamlazy.review.entity.Review;
import com.tcp.iamlazy.review.service.ReviewService;
import com.tcp.iamlazy.util.valid.RequestResultValidationProcessor;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{reviewDate}")
    public ResponseEntity<List<Review>> getCompanyList(@CurrentUser UserPrincipal userPrincipal,
                                                       @PathVariable(value = "reviewDate") String reviewDate){

        final List<Review> weekReviews = reviewService
            .getWeekReviews(reviewDate, userPrincipal.getUsername());

        return ResponseEntity.ok(weekReviews);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> insertReview(@CurrentUser UserPrincipal userPrincipal,
                                                    @ApiParam(value = "userIdx : 사용자 고유 식별자(로그인 된 사용자 아니면 거부됨)(필수)\n"
                                                        + "todoDate : 등록 일자(필수, yyyy-MM-dd 포맷)(필수)\n"
                                                        + "reviewContent : 회고 내용(필수)\n"
                                                        + "emoticon : 이모티콘 식별자")
                                                    @RequestBody Review review,
                                                    Errors errors){

        if (errors.hasErrors()) {
            return RequestResultValidationProcessor.returnErrorResponse(errors);
        }

        reviewService.insertReview(review, userPrincipal.getUsername());

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/reviews/{userId}/{reviewDate}")
            .buildAndExpand(review.getUserIdx(), review.getToDoDate()).toUri();

        return ResponseEntity.created(location)
            .body(new ApiResponse(true, "Reivew registered successfully@"));
    }

    @PutMapping(value = "/{userIdx}/{reviewDate}")
    public ResponseEntity<ApiResponse> updateReview(@CurrentUser UserPrincipal userPrincipal,
                                                    @PathVariable(value = "userIdx") int id,
                                                    @PathVariable(value = "reviewDate") String reviewDate,
                                                    @RequestBody Review review,
                                                    Errors errors) {
        if (errors.hasErrors()) {
            return RequestResultValidationProcessor.returnErrorResponse(errors);
        }

        final int userId = Integer.parseInt(userPrincipal.getUsername());

        if ( userId != id ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(false, "Invalid userId was taken. Current user can't edit this reivew."));
        }

        review.setToDoDate(reviewDate);

        reviewService.updateReview(userId, review);

        return ResponseEntity.ok().body(new ApiResponse(true, "Review updated successfully@"));
    }

}
