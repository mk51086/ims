package com.ims.service;

import com.ims.entity.Category;
import com.ims.entity.InventoryItem;
import org.springframework.stereotype.Service;

import java.util.List;
public interface InventoryItemService {
    InventoryItem addItem(InventoryItem item);
    InventoryItem updateItem(InventoryItem item);
    void deleteItem(int id);
    List<InventoryItem> getItems();
    InventoryItem getItemById(int id);
    List<InventoryItem> getItemsByCategory(Category category);
}
