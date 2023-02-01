package com.example.warehousemanagementapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RECEIPTS")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiptID;
    private LocalDate inputDay;
    private double intoMoney;
    private String hasAccount;
    private String debtAccount;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "manufactureID")
    private Manufacture manufacture;

    @OneToMany(mappedBy = "receipt")
    @JsonIgnore
    private List<ReceiptDetail> receiptDetails;
}
