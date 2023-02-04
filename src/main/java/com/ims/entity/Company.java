package com.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "company_table", schema = "ims")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer contactNumber;
    private String email;
    private String address;
    private String description;

    @OneToMany(mappedBy = "id")
    private List<Order> orders;

    private boolean isSupplier;

    private boolean isCustomer;

}
