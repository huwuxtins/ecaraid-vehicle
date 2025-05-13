package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.dto.SearchObjectDto;
import com.ecaraid.intern.vehicle.dto.VehicleDto;
import com.ecaraid.intern.vehicle.entity.Vehicle;
import net.sf.jasperreports.engine.JRException;

import java.util.List;

public interface VehicleService {
  public List<Vehicle> findAllVehicle(Integer page, Integer size);

  public Vehicle findById(String id);

  public Vehicle create(Vehicle vehicle);

  public Vehicle update(String id, Vehicle vehicle);

  public void delete(String id);

  public List<Vehicle> search(Integer page, Integer size, SearchObjectDto dto);

  public List<Vehicle> findByRequest();
  public byte[] generateReport(List<VehicleDto> dto) throws JRException;
}
