package com.example.warehousemanagementapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WAREHOUSES")
public class Warehouse {
    @Id
    private String warehouseID;
    @Nationalized
    private String warehouseName;
    @Nationalized
    private String address;
    private String phoneNumber;
    @Nationalized
    private String stocker;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
//    @JsonIgnore
//    private List<Inventory> reports;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
    @JsonIgnore
    private List<Product> products;
}
