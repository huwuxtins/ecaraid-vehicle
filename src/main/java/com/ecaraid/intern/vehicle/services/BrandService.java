package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.entity.Brand;

import java.util.List;

public interface BrandService {
    public List<Brand> findAllBrand(boolean isDeleted);
    public Brand findById(String id);
    public Brand create(Brand brand);
    public Brand update(String id, Brand brand);
    public void delete(String id);
}
