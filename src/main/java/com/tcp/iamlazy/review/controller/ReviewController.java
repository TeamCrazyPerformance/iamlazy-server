package com.tcp.iamlazy.review.controller;

import com.tcp.iamlazy.review.service.ReviewService;
import com.tcp.iamlazy.review.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/weekRv")
    public ResponseEntity<List<Review>> getCompanyList(){
        return ResponseEntity.ok(reviewService.getWeekRv());
    }

}
