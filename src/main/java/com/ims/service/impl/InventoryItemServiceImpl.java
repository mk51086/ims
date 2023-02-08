package com.ims.service.impl;

import com.ims.dto.InventoryItemDTO;
import com.ims.entity.InventoryItem;
import com.ims.exception.company.CompanyNotFoundException;
import com.ims.repository.InventoryItemRepository;
import com.ims.service.InventoryItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public InventoryItemDTO addItem(InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItem = dtoToEntity(inventoryItemDTO);
        InventoryItem savedInventoryItem = inventoryItemRepository.save(inventoryItem);
        return new InventoryItemDTO(savedInventoryItem);
    }

    @Override
    public InventoryItemDTO updateItem(InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItem = dtoToEntity(inventoryItemDTO);
        InventoryItem savedInventoryItem = inventoryItemRepository.save(inventoryItem);
        return new InventoryItemDTO(savedInventoryItem);
    }

    @Override
    public void deleteItem(int id) {
        inventoryItemRepository.deleteById(id);
    }

    @Override
    public List<InventoryItemDTO> getItems() {
        List<InventoryItem> inventoryItems = inventoryItemRepository.findAll();
        return inventoryItems.stream().map(InventoryItemDTO::new).collect(Collectors.toList());
    }

    @Override
    public InventoryItemDTO getItemById(int id) {
        InventoryItem inventoryItem = this.inventoryItemRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        return new InventoryItemDTO(inventoryItem);
    }

    @Override
    public List<InventoryItem> findByCategoryId(int categoryId) {
        return inventoryItemRepository.findInventoryItemByCategory(categoryId);
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

    private InventoryItem dtoToEntity(InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItem = new InventoryItem();
        BeanUtils.copyProperties(inventoryItemDTO, inventoryItem);
        return inventoryItem;
    }

}
