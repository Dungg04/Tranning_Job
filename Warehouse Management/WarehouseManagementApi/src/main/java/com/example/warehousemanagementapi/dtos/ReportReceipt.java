package com.example.warehousemanagementapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReportReceipt {
    private Integer receiptID;
    private String manufactureID;
    private String manufactureName;
    private String inputDay;
    private double intoMoney;
}
