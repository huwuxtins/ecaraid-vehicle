package com.ecaraid.intern.vehicle.entity;

import com.ecaraid.intern.vehicle.BrandType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE brand SET deleted = true WHERE id=?")
@FilterDef(
    name = "deletedProductFilter",
    parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class Brand {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;

  @Enumerated(EnumType.STRING)
  private BrandType type;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
      mappedBy = "brand",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  private List<Vehicle> vehicles;

  @JsonCreator
  public Brand(@JsonProperty("id") String id) {
    this.id = id;
  }

  private Boolean deleted = Boolean.FALSE;
}
