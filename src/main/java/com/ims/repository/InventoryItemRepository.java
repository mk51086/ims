package com.ims.repository;

import com.ims.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
    List<InventoryItem> findInventoryItemByCategory(int categoryId);
    List<InventoryItem> findByQuantityLessThan(int quantity);

}
