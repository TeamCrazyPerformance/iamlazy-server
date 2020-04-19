package com.tcp.iamlazy.setting.controller;

import com.tcp.iamlazy.setting.service.SettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/setting")
public class SettingController {
    private final SettingService settingService;

    public SettingController(SettingService settingService){
        this.settingService = settingService;
    }


}
