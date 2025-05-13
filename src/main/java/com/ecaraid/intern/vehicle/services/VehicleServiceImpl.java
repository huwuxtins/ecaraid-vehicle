package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.dto.SearchObjectDto;
import com.ecaraid.intern.vehicle.dto.VehicleDto;
import com.ecaraid.intern.vehicle.entity.Vehicle;
import com.ecaraid.intern.vehicle.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
  private final VehicleRepository vehicleRepository;

  public List<Vehicle> findAllVehicle(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return this.vehicleRepository.findAll(pageable).getContent();
  }

  public Vehicle findById(String id) {
    return this.vehicleRepository.findById(id).orElse(null);
  }

  public Vehicle create(Vehicle vehicle) {
    return this.vehicleRepository.save(vehicle);
  }

  public Vehicle update(String id, Vehicle vehicle) {
    Vehicle exist = this.vehicleRepository.findById(id).orElse(null);

    if (exist != null) {
      return this.vehicleRepository.save(vehicle);
    }
    return null;
  }

  public void delete(String id) {
    this.vehicleRepository.deleteById(id);
  }

  public List<Vehicle> search(Integer page, Integer size, SearchObjectDto dto) {
    Pageable pageable = PageRequest.of(page, size);
    return this.vehicleRepository
        .findByBrandNameOrYearOrPriceOrOwner(
            dto.getBrand(), dto.getYear(), dto.getPrice(), dto.getOwner(), pageable)
        .getContent();
  }

  public List<Vehicle> findByRequest() {
    return this.vehicleRepository.findByRequest();
  }

  public byte[] generateReport(List<VehicleDto> vehicles) throws JRException {
    // Load template from classpath
    InputStream reportStream = this.getClass().getResourceAsStream("/reports/vehicle_report.jrxml");
    if (reportStream == null) {
      throw new JRException("Report template not found at /reports/vehicle_report.jrxml");
    }
    JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

    // Data source
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehicles);

    // Parameters
    Map<String, Object> parameters = new HashMap<>();

    // Fill report
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

    // Export to PDF
    return JasperExportManager.exportReportToPdf(jasperPrint);
  }
}
