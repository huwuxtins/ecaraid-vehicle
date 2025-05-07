package com.ecaraid.intern.vehicle.entity;

import com.ecaraid.intern.vehicle.BrandType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    @Enumerated(EnumType.STRING)
    private BrandType type;
}
