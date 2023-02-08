package com.ims.service;

import com.ims.dto.InventoryItemDTO;
import com.ims.entity.Category;
import com.ims.entity.InventoryItem;
import org.springframework.stereotype.Service;

import java.util.List;
public interface InventoryItemService {
    InventoryItemDTO addItem(InventoryItemDTO inventoryItemDTO);
    InventoryItemDTO updateItem(InventoryItemDTO inventoryItemDTO);
    void deleteItem(int id);
    List<InventoryItemDTO> getItems();
    InventoryItemDTO getItemById(int id);
    List<InventoryItem> findByCategoryId(int categoryId);

    List<InventoryItem> getLowStockItems();
    InventoryItem restock (int id, int quantity);
    List<InventoryItem> getOutOfStockItems();
}
