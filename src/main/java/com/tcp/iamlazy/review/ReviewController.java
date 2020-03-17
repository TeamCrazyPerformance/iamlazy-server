package com.tcp.iamlazy.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @RequestMapping(value="/weekRv", method= RequestMethod.GET)
    public ResponseEntity<List<Review>> getCompanyList(){
        return ResponseEntity.ok(reviewService.getWeekRv());
    }

}
