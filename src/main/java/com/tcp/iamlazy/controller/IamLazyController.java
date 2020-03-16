package com.tcp.iamlazy.controller;

import com.tcp.iamlazy.dto.Company;
import com.tcp.iamlazy.service.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/companies")
public class IamLazyController {
    final
    CompanyService companyService;

    public IamLazyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanyList(){
        return ResponseEntity.ok(companyService.get());
    }
}
