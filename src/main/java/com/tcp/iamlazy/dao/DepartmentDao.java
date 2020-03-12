package com.tcp.iamlazy.dao;

import com.tcp.iamlazy.dto.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentDao {
    List<Department> getDepartmentList(Department department);
}
