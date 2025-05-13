package com.ecaraid.intern.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchObjectDto {
     private String brand;
     private String owner;
     private Integer year;
     private Integer price;
}
