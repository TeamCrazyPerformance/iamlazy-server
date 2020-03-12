package com.tcp.iamlazy.service;

import com.tcp.iamlazy.dao.CompanyDao;
import com.tcp.iamlazy.dto.Company;
import com.tcp.iamlazy.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao dao;

    public List<Company> get() {
        return dao.getCompanyList();
    }

}
