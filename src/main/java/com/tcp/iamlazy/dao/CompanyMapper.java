package com.tcp.iamlazy.dao;

import com.tcp.iamlazy.dto.Company;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyMapper {
    List<Company> getCompanyList();
}
