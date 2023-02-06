package com.ims.service;

import com.ims.dto.SaleDTO;
import com.ims.entity.InventoryItem;
public interface SaleService {
        void addSale(SaleDTO sale);
        void generateInvoice(InventoryItem[] items);
        void modifySale(int id, SaleDTO sale);
        void removeSale(int id);
}
