package com.ecaraid.intern.vehicle;

import com.ecaraid.intern.vehicle.entity.Brand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;

public class VehicleDto {

    private String name;
    private int year;
    private int price;
    private String owner;
    private String brand;

    private Date instant;
}
