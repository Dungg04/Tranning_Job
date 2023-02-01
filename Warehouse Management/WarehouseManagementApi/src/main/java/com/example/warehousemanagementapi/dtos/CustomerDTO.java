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
public class CustomerDTO {
    @Nationalized
    private String customerName;
    @Nationalized
    private String address;
    private String phoneNumber;
}
