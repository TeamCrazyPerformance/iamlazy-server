package com.tcp.iamlazy.setting.controller;

import com.tcp.iamlazy.review.entity.Review;
import com.tcp.iamlazy.setting.entity.Setting;
import com.tcp.iamlazy.setting.service.SettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/setting")
public class SettingController {
    private final SettingService settingService;

    public SettingController(SettingService settingService){
        this.settingService = settingService;
    }


}
