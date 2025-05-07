package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.entity.Vehicle;
import com.ecaraid.intern.vehicle.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllVehicle() {
        return this.vehicleRepository.findAll();
    }

    public Vehicle findById(String id) {
        return this.vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle create(Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    public Vehicle update(String id, Vehicle vehicle) {
        Vehicle exist = this.vehicleRepository.findById(id).orElse(null);

        if(exist != null) {
            return this.vehicleRepository.save(vehicle);
        }
        return null;
    }

    public void delete(String id) {
        this.vehicleRepository.deleteById(id);
    }

    public List<Vehicle> search(String brandId, Integer year, Integer price, String owner) {
        return this.vehicleRepository.findByBrandIdOrYearOrPriceOrOwner(brandId, year, price, owner);
    }

    public List<Vehicle> findByRequest(){
        return this.vehicleRepository.findByRequest();
    }
}
