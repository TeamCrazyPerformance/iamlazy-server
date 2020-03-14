package com.tcp.iamlazy.dto;

import java.util.List;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company{

    private int company_no;
    private String company_name;
    private String start_date;
    private String end_date;
}
