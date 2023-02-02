package com.ims.service.impl;

import com.ims.entity.InventoryItem;
import com.ims.repository.InventoryItemRepository;
import com.ims.service.InventoryItemService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public InventoryItem addItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    @Override
    public InventoryItem updateItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    @Override
    public void deleteItem(int id) {
        inventoryItemRepository.deleteById(id);
    }

    @Override
    public List<InventoryItem> getItems() {
        return inventoryItemRepository.findAll();
    }

    @Override
    public InventoryItem getItemById(int id) {
        return inventoryItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<InventoryItem> findByCategoryId(int categoryId) {
        return inventoryItemRepository.findByCategoryId(categoryId);
    }
    @Override
    public List<InventoryItem> getLowStockItems() {
        return inventoryItemRepository.findByQuantityLessThan(5);
    }
    @Override
    public InventoryItem restock(int id, int quantity) {
        return null;
    }

    @Override
    public List<InventoryItem> getOutOfStockItems() {
        return null;
    }

}
