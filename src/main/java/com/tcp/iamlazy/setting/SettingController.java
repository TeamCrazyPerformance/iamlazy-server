package com.tcp.iamlazy.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/setting")
public class SettingController {
    @Autowired
    SettingService settingService;


}
