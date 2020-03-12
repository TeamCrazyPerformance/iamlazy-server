package com.tcp.iamlazy;

import com.tcp.iamlazy.dto.Company;
import com.tcp.iamlazy.response.CompanyResponse;

import java.util.List;

public class CompanyAdapter {
    public static CompanyResponse companyResponse(final Company company, final List<String> errors) {
        return CompanyResponse.builder().company(company).errors(errors).build();
    }

}
