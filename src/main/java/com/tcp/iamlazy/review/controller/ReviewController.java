package com.tcp.iamlazy.review.controller;

import com.tcp.iamlazy.review.service.ReviewService;
import com.tcp.iamlazy.review.entity.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{toDoDate}")
    public ResponseEntity<List<Review>> getCompanyList(@PathVariable(value = "toDoDate") String toDoDate){
        HashMap<String, Object> review = new HashMap<>();

        review.put("reviewItem", reviewService.getWeekRv(toDoDate));

        return new ResponseEntity(review, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<Review>> insertReview(@RequestBody Review rv){
        ResponseEntity<List<Review>> rvItems = null;

        reviewService.insertReview(rv);

        rvItems = new ResponseEntity("success", HttpStatus.OK);

        return rvItems;
    }

    @PutMapping(value = "/{userIdx}")
    public ResponseEntity<List<Review>> updateReview(@PathVariable(value = "userIdx") int id,
                                                     @RequestBody Review rv){
        ResponseEntity<List<Review>> rvItems = null;

        reviewService.updateReview(id, rv);

        rvItems =  new ResponseEntity("success", HttpStatus.OK);

        return rvItems;

    }




}
