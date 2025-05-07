package com.ecaraid.intern.vehicle;

import com.ecaraid.intern.vehicle.entity.Vehicle;
import com.ecaraid.intern.vehicle.services.VehicleService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/cars")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getListOfCar() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Vehicle> vehicles = this.vehicleService.findAllVehicle();
            result.put("data", vehicles);
            result.put("status", "success");
            return new ResponseEntity<Object>(result, HttpStatus.OK );
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Vehicle vehicle = this.vehicleService.findById(id);
            result.put("status", "success");
            if(vehicle != null) {
                result.put("data", vehicle);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createVehicle(@RequestBody Vehicle vehicle) {
        Map<String, Object> result = new HashMap<>();
        try {
            Vehicle savedVehicle = vehicleService.create(vehicle);
            result.put("status", "success");
            result.put("data", savedVehicle);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable String id, @RequestBody Vehicle vehicle) {
        Map<String, Object> result = new HashMap<>();
        try {
            Vehicle updatedVehicle = vehicleService.update(id, vehicle);
            result.put("status", "success");
            result.put("data", updatedVehicle);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            vehicleService.delete(id);
            result.put("status", "success");
            result.put("data", "Vehicle deleted successfully.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam(name = "brand_id", required = false) String brandId,
                                         @RequestParam(name = "year", required = false) Integer year,
                                         @RequestParam(name = "price", required = false) Integer price,
                                         @RequestParam(name = "owner", required = false) String owner) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Vehicle> vehicles = this.vehicleService.search(brandId, year, price, owner);
            result.put("status", "success");
            result.put("data", vehicles);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/request")
    public ResponseEntity<Object> findByRequest() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Vehicle> vehicles = this.vehicleService.findByRequest();
            result.put("status", "success");
            result.put("data", vehicles);
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
