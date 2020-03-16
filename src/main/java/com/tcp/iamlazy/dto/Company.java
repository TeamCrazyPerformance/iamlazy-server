package com.tcp.iamlazy.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Company{

    @NotNull(message = "Company number cannot be null.")
    private int companyNo;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;

}
