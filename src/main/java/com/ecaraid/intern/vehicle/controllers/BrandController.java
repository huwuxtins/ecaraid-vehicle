package com.ecaraid.intern.vehicle.controllers;

import com.ecaraid.intern.vehicle.entity.Brand;
import com.ecaraid.intern.vehicle.services.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Brand> brands = this.brandService.findAllBrand();
            result.put("status", "success");
            result.put("data", brands);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Brand brand) {
        Map<String, Object> result = new HashMap<>();
        try {
            Brand created = this.brandService.create(brand);
            result.put("status", "success");
            result.put("data", created);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            this.brandService.delete(id);
            result.put("status", "success");
            result.put("data", "");
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result.put("status", "failure");
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
