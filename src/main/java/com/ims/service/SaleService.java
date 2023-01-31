package com.ims.service;

import com.ims.entity.InventoryItem;
import com.ims.entity.Sale;
public interface SaleService {
        void addSale(Sale sale);
        void generateInvoice(InventoryItem[] items);
        void modifySale(int id, Sale sale);
        void removeSale(int id);
}
