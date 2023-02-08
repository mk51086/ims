package com.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_table", schema = "ims")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<InventoryItem> inventoryItems;

}
