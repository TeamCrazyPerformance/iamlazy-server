package com.tcp.iamlazy.dto;

import java.util.List;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company{

    private int companyNo;
    private String companyNaAme;
    private String startDate;
    private String endDate;
    private List<Department> departmentList;

}
