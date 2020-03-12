package com.tcp.iamlazy.controller;

import com.tcp.iamlazy.adapter.CompanyAdapter;
import com.tcp.iamlazy.dto.Company;
import com.tcp.iamlazy.response.CompanyResponse;
import com.tcp.iamlazy.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/companies")
public class IamLazyController {
    @Autowired
    CompanyService companyService;

    @RequestMapping(value="", method=RequestMethod.GET)
    public @ResponseBody List<Company> getCompanyList(){
//        List<String> errors = new ArrayList<>();
//        Company result = null;
//
//        try {
//            result = companyService.get();
//            System.out.println(result);
//        } catch (final Exception e) {
//            errors.add(e.getMessage());
//        }
//        return CompanyAdapter.companyResponse(result, errors);
        return companyService.get();
    }
}
