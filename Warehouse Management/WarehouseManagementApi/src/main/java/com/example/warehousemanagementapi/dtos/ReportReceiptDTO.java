package com.example.warehousemanagementapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportReceiptDTO {
    private int index;
    private String productId;
    private String productName;
    private int amount;
    private double price;
    private double total;
}
