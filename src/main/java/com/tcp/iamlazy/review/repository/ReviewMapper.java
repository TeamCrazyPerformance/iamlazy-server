package com.tcp.iamlazy.review.repository;

import com.tcp.iamlazy.review.entity.Review;
import com.tcp.iamlazy.review.entity.dto.ReviewGetCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {
//  주간 회고 요청
    List<Review> getWeekReviews(ReviewGetCondition searchCondition);

// 회고 등록
    void insertReview(Review review);

// 회고 수정
    void updateReview(Review rvItem);
}