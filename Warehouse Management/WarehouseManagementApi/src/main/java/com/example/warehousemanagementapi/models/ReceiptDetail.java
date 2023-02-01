package com.example.warehousemanagementapi.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RECEIPT_DETAILS")
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiptDetailID;

    private Integer temp;

    private int amount;
    private double price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "receiptID")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
}
