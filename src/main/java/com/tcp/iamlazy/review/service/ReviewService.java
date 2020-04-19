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

    public List<Review> getWeekRv(String toDoDate){

        List<Review> reviewList = reviewMapper.getWeekRv(toDoDate);

        return reviewList;
    }

    public void insertReview(Review review){
        Review rvItem = new Review();

        rvItem.setUserIdx(review.getUserIdx());
        rvItem.setToDoDate(review.getToDoDate());
        rvItem.setReviewContent(review.getReviewContent());
        rvItem.setEmoticon(review.getEmoticon());

        reviewMapper.insertReview(rvItem);
    }


    public void updateReview(int userIdx, Review review){
        if(review == null){
            throw new NullPointerException("reviews cannot be null");
        }
        Review rvItem = new Review();
        rvItem.setUserIdx(userIdx);
        rvItem.setToDoDate(review.getToDoDate());
        rvItem.setReviewContent(review.getReviewContent());
        rvItem.setEmoticon(review.getEmoticon());

        reviewMapper.updateReview(rvItem);
    }

}
