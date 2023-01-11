package com.example.warehousemanagementapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManufactureDTO {
    @Nationalized
    private String manufactureName;
    @Nationalized
    private String address;
    private String phoneNumber;
}
