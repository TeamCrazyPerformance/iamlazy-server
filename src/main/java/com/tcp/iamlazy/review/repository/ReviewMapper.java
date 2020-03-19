package com.tcp.iamlazy.review.repository;

import com.tcp.iamlazy.review.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {
//  주간 회고 요청
    List<Review> getWeekRv();

}