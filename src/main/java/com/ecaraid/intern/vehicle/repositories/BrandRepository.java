package com.ecaraid.intern.vehicle.repositories;

import com.ecaraid.intern.vehicle.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

}
