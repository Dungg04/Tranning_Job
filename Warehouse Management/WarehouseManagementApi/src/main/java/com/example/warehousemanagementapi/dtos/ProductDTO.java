package com.example.warehousemanagementapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO {
    @Nationalized
    private String productName;
    private int amount;
    private double price;
}
