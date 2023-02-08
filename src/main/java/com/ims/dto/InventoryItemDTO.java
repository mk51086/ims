package com.ims.dto;

import com.ims.entity.InventoryItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InventoryItemDTO {
    private Integer id;
    private String name;
    private String description;
    private int quantity;

    private float purchasePrice;
    private LocalDateTime expirationDate;
    private Integer categoryId;

    public InventoryItemDTO(InventoryItem inventoryItem) {
        BeanUtils.copyProperties(inventoryItem, this);
    }

    public Integer getCategoryId() {
        return categoryId;
    }

}
