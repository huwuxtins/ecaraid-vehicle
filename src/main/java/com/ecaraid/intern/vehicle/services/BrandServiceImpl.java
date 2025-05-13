package com.ecaraid.intern.vehicle.services;

import com.ecaraid.intern.vehicle.entity.Brand;
import com.ecaraid.intern.vehicle.repositories.BrandRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;
    private final EntityManager entityManager;

    public List<Brand> findAllBrand(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Brand> brands = this.brandRepository.findAll();
        session.disableFilter("deletedProductFilter");
        return brands;
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
