package com.tcp.iamlazy.dao;

import com.tcp.iamlazy.dto.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyDao {
    List<Company> getCompanyList();
}
