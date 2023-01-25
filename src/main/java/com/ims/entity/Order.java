package com.ims.entity;

import com.ims.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table", schema = "ims")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private OrderStatus status;
    private LocalDateTime date;

    @OneToMany   (mappedBy = "order")
    private List<InventoryItem> inventoryItems;

    @OneToMany(mappedBy = "order")
    private List<Supplier> suppliers;

}
