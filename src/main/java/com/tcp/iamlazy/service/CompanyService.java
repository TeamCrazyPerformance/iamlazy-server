package com.tcp.iamlazy.service;

import com.tcp.iamlazy.dao.CompanyMapper;
import com.tcp.iamlazy.dto.Company;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    public List<Company> get() {
        return companyMapper.getCompanyList();
    }

}
