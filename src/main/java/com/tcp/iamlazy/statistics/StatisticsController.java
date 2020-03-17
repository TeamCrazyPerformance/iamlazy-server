package com.tcp.iamlazy.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/statis")
public class StatisticsController {
    @Autowired
    StatisticsService statsService;
}
