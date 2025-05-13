package com.ecaraid.intern.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private int year;
    private int price;
    private String owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonIgnoreProperties({"vehicles"})
    private Brand brand;

    private Date instant;

    public Vehicle(String id, String name, int year, int price, String owner, Date instant) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
        this.owner = owner;
        this.instant = instant;
    }

    public Vehicle(String name, int year, int price, String owner, Date instant) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.owner = owner;
        this.instant = instant;
    }
}
