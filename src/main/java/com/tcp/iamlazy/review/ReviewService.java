package com.tcp.iamlazy.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper reviewMapper;

    public List<Review> getWeekRv(){

        List<Review> reviewList = reviewMapper.getWeekRv();

        return reviewList;
    }

}
