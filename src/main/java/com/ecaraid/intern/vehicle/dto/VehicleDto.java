package com.ecaraid.intern.vehicle.dto;

import com.ecaraid.intern.vehicle.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
  private String id;

  private String name;
  private int year;
  private int price;
  private String owner;
  private String brandId;

  private Date instant;

  public Vehicle convert() {
    return new Vehicle(this.id, this.name, this.year, this.price, this.owner, this.instant);
  }

  public Vehicle convertNotId() {
    return new Vehicle(this.name, this.year, this.price, this.owner, this.instant);
  }

  public static VehicleDto reconvert(Vehicle vehicle) {
    return new VehicleDto(
        vehicle.getId(),
        vehicle.getName(),
        vehicle.getYear(),
        vehicle.getPrice(),
        vehicle.getOwner(),
        vehicle.getBrand().getId(),
        vehicle.getInstant());
  }

  public VehicleDto(String name, Integer year, Integer price,String owner, String brandId, Date instant) {
    this.name= name;
    this.year = year;
    this.price = price;
    this.owner = owner;
    this.brandId = brandId;
    this.instant = instant;
  }
}
