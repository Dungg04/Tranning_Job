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
@Table(name = "MANUFACTURES")
public class Manufacture {
    @Id
    private String manufactureID;

    @Nationalized
    private String manufactureName;
    @Nationalized
    private String address;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "manufacture")
    @JsonIgnore
    private List<Receipt> receipts;
}
