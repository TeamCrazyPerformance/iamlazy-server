package com.tcp.iamlazy.service;

import com.tcp.iamlazy.dao.CompanyMapper;
import com.tcp.iamlazy.dto.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyMapper dao;

    public List<Company> get() {
        return dao.getCompanyList();
    }

}
