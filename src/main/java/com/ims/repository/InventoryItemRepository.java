package com.ims.repository;

import com.ims.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
    /*Nuk e di a funksion qishtu veq duhet me kqyr */
    public List<InventoryItem> getInventoryItemByCategory (Integer category);
}
