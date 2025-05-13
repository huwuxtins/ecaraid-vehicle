package com.ecaraid.intern.vehicle.controllers;

import com.ecaraid.intern.vehicle.dto.SearchObjectDto;
import com.ecaraid.intern.vehicle.dto.VehicleDto;
import com.ecaraid.intern.vehicle.entity.Brand;
import com.ecaraid.intern.vehicle.entity.Vehicle;
import com.ecaraid.intern.vehicle.response.MessageResponse;
import com.ecaraid.intern.vehicle.services.BrandService;
import com.ecaraid.intern.vehicle.services.BrandServiceImpl;
import com.ecaraid.intern.vehicle.services.VehicleService;
import com.ecaraid.intern.vehicle.services.VehicleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/vehicles")
@AllArgsConstructor
@Slf4j
public class VehicleController {
  private final VehicleService vehicleService;
  private final BrandService brandService;

  @GetMapping("")
  public ResponseEntity<Object> getListOfCar(
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
    try {
      List<Vehicle> vehicles = this.vehicleService.findAllVehicle(page, size);
      return MessageResponse.returnResponse(vehicles, "success", HttpStatus.OK);
    } catch (Exception e) {
      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.OK);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable String id) {
    try {
      Vehicle vehicle = this.vehicleService.findById(id);
      if (vehicle != null) {
        return MessageResponse.returnResponse(vehicle, "success", HttpStatus.OK);
      }
      return MessageResponse.returnResponse(null, "success", HttpStatus.NOT_FOUND);
    } catch (Exception e) {

      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createVehicle(@RequestBody VehicleDto dto) {
    try {
      Brand brand = this.brandService.findById(dto.getBrandId());

      if (brand == null) {
        throw new Error("Brand not found");
      }
      Vehicle vehicle = dto.convertNotId();
      vehicle.setBrand(brand);

      Vehicle savedVehicle = vehicleService.create(vehicle);

      return MessageResponse.returnResponse(savedVehicle, "success", HttpStatus.CREATED);
    } catch (Exception e) {

      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.OK);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateVehicle(
      @PathVariable String id, @RequestBody VehicleDto dto) {
    try {
      Brand brand = this.brandService.findById(dto.getBrandId());

      if (brand == null) {
        throw new Error("Brand not found");
      }
      Vehicle vehicle = dto.convert();
      vehicle.setBrand(brand);

      Vehicle updatedVehicle = vehicleService.update(id, vehicle);

      return MessageResponse.returnResponse(updatedVehicle, "success", HttpStatus.OK);
    } catch (Exception e) {
      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteVehicle(@PathVariable String id) {
    try {
      vehicleService.delete(id);
      return MessageResponse.returnResponse(id, "success", HttpStatus.OK);
    } catch (Exception e) {
      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/search")
  public ResponseEntity<Object> search(
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
      SearchObjectDto dto) {
    try {
      List<Vehicle> vehicles = this.vehicleService.search(page, size, dto);

      return MessageResponse.returnResponse(vehicles, "success", HttpStatus.OK);
    } catch (Exception e) {
      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/request")
  public ResponseEntity<Object> findByRequest() {
    try {
      List<Vehicle> vehicles = this.vehicleService.findByRequest();
      return MessageResponse.returnResponse(vehicles, "success", HttpStatus.OK);
    } catch (Exception e) {
      return MessageResponse.returnResponse(e.getMessage(), "success", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/report")
  public ResponseEntity<Object> getReport(
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
    try {
      List<Vehicle> vehicles = this.vehicleService.findAllVehicle(page, size);
      List<VehicleDto> dto = vehicles.stream().map((VehicleDto::reconvert)).toList();

      byte[] pdfBytes = this.vehicleService.generateReport(dto);
      return MessageResponse.returnResponse(pdfBytes, "success", HttpStatus.OK);

    } catch (Exception e) {
      return MessageResponse.returnResponse(e.getMessage(), "failure", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
