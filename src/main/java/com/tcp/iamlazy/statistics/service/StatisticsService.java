package com.tcp.iamlazy.statistics.service;

import com.tcp.iamlazy.statistics.repository.StatisticsMapper;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    private final StatisticsMapper statisticsMapper;

    public  StatisticsService(StatisticsMapper statisticsMapper){
        this.statisticsMapper = statisticsMapper;
    }


}
