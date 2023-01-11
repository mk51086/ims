package com.ims.controller;

import com.ims.entity.InventoryItem;
import com.ims.service.InventoryItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @PostMapping
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return inventoryItemService.addItem(item);
    }
    @PutMapping
    public InventoryItem updateItem(@RequestBody InventoryItem item) {
        return inventoryItemService.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        inventoryItemService.deleteItem(id);
    }

    @GetMapping
    public List<InventoryItem> getItems() {
        return inventoryItemService.getItems();
    }

    @GetMapping("/{id}")
    public InventoryItem getItemById(@PathVariable int id) {
        return inventoryItemService.getItemById(id);
    }
}
