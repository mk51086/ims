package com.ims.entity;

import com.ims.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Integer quantity;
    @OneToMany   (fetch = FetchType.EAGER,mappedBy = "order")
    private List<InventoryItem> inventoryItems;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Company supplier;

}
