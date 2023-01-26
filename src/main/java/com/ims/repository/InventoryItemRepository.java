package com.ims.repository;

import com.ims.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
    @Query("SELECT i FROM InventoryItem i WHERE i.category.category_id = :categoryId")
    List<InventoryItem> findByCategoryId(@Param("categoryId")int categoryId);

    @Query("SELECT i FROM InventoryItem i WHERE i.quantity < 5")
    List<InventoryItem> getLowStockItems();
}
