package com.example.warehousemanagementapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REPORTS")
public class Inventory {
    @EmbeddedId
    private InventoryKey inventoryID;
    private int amount;
    private double price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("productID")
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("warehouseID")
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;
}
