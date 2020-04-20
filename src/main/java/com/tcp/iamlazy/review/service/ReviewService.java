package com.tcp.iamlazy.review.service;

import com.tcp.iamlazy.review.entity.Review;
import com.tcp.iamlazy.review.entity.dto.ReviewGetCondition;
import com.tcp.iamlazy.review.repository.ReviewMapper;
import com.tcp.iamlazy.util.date.DateFormatCatcher;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;
    @Value("${app.date.param.format")
    private String dateStringFormat;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public List<Review> getWeekReviews(String toDoDate, String username){

        final LocalDateTime targetDateTime = DateFormatCatcher
            .getLocalDateTime(toDoDate, dateStringFormat);

        ReviewGetCondition condition = new ReviewGetCondition(username, targetDateTime);

        List<Review> reviewList = reviewMapper.getWeekReviews(condition);

        return reviewList;
    }

    public void insertReview(Review review, String username){
        Review rvItem = new Review();

        rvItem.setUserIdx(Integer.parseInt(username));
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
