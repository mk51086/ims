package com.ims.service;

import com.ims.entity.InventoryItem;
import com.ims.entity.Supplier;
import org.hibernate.criterion.Order;

import java.util.List;

public interface SupplierService {
    Supplier addSupplier(Supplier supplier);
    Supplier updateSupplier(Supplier supplier);
    void deleteSupplier(int id);
    List<Supplier> getSuppliers();
    Supplier getSupplierById(int id);
}
