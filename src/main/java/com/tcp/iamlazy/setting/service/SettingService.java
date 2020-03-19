package com.tcp.iamlazy.setting.service;

import com.tcp.iamlazy.setting.repository.SettingMapper;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
   private final SettingMapper settingMapper;

   public SettingService(SettingMapper settingMapper){
       this.settingMapper = settingMapper;
   }

}
