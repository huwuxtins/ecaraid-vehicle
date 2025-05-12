package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.entity.Brand;
import com.ecaraid.intern.vehicle.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<Brand> findAllBrand(){
        return this.brandRepository.findAll();
    }

    public Brand findById(String id) { return this.brandRepository.findById(id).orElse(null);}

    public Brand create(Brand brand) {
        return this.brandRepository.save(brand);
    }

    public Brand update(String id, Brand brand) {
        Brand exist = this.brandRepository.findById(id).orElse(null);
        if(exist != null) {
            return this.brandRepository.save(brand);
        }
        return null;
    }

    public void delete(String id) {
        this.brandRepository.deleteById(id);
    }
}
