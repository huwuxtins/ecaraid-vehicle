package com.ecaraid.intern.vehicle.repositories;

import com.ecaraid.intern.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByBrandIdOrYearOrPriceOrOwner(String brandId, Integer year, Integer price, String owner);

    @Query(value = """
    SELECT v.*
    FROM vehicle v
    JOIN brand b ON v.brand_id = b.id
    WHERE
        (v.price > 10000000 AND b.name LIKE 'S%')
        OR
        (v.price <= 10000000 AND b.type = 'bus')
    """, nativeQuery = true)
    List<Vehicle> findByRequest();
}
