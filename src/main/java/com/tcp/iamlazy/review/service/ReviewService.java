package com.tcp.iamlazy.review.service;

import com.tcp.iamlazy.review.entity.Review;
import com.tcp.iamlazy.review.repository.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public List<Review> getWeekRv(){

        List<Review> reviewList = reviewMapper.getWeekRv();

        return reviewList;
    }

}
