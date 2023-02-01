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
public class WarehouseDTO {
    @Nationalized
    private String warehouseName;
    @Nationalized
    private String address;
    @Nationalized
    private String phoneNumber;
    @Nationalized
    private String stocker;
}
