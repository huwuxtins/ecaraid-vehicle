package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    public List<Vehicle> findAllVehicle();

    public Vehicle findById(String id);

    public Vehicle create(Vehicle vehicle);

    public Vehicle update(String id, Vehicle vehicle);

    public void delete(String id);

    public List<Vehicle> search(String brand, Integer year, Integer price, String owner);

    public List<Vehicle> findByRequest();
}
