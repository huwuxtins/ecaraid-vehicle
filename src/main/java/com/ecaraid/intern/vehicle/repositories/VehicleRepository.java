package com.ecaraid.intern.vehicle.repositories;

import com.ecaraid.intern.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    @Query("""
    SELECT v FROM Vehicle v
    WHERE (:brandName IS NULL OR v.brand.name = :brandName)
      AND (:year IS NULL OR v.year = :year)
      AND (:price IS NULL OR v.price = :price)
      AND (:owner IS NULL OR v.owner = :owner)
    """)
    List<Vehicle> findByBrandNameOrYearOrPriceOrOwner(
            @Param("brandName") String brandName,
            @Param("year") Integer year,
            @Param("price") Integer price,
            @Param("owner") String owner
    );

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
