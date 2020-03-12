package com.tcp.iamlazy.response;

import com.tcp.iamlazy.apiResponse.ApiResponse;
import com.tcp.iamlazy.dto.Company;
import lombok.Builder;

import java.util.List;

public class CompanyResponse extends ApiResponse<Company> {
    @Builder
    public CompanyResponse(final Company company, final List<String> errors) {
        super(company);
        this.setErrors(errors);
    }


}

