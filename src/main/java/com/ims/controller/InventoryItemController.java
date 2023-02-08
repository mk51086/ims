package com.ims.controller;

import com.ims.dto.InventoryItemDTO;
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
    public InventoryItemDTO addItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        return inventoryItemService.addItem(inventoryItemDTO);
    }
    @PutMapping
    public InventoryItemDTO updateItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        return inventoryItemService.updateItem(inventoryItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        inventoryItemService.deleteItem(id);
    }

    @GetMapping
    public List<InventoryItemDTO> getItems() {
        return inventoryItemService.getItems();
    }

    @GetMapping("/{id}")
    public InventoryItemDTO getItemById(@PathVariable int id) {
        return inventoryItemService.getItemById(id);
    }

    @GetMapping("/{categoryId}")
    public List<InventoryItem> getItemsByCategoryId (@PathVariable int categoryId){
        return inventoryItemService.findByCategoryId(categoryId);
    }
    @GetMapping("/lowStockItems")
    public List<InventoryItem> listOfLowStockItems
            (){
        return inventoryItemService.getLowStockItems();
    }
}
