package com.ims.service.impl;

import com.ims.entity.InventoryItem;
import com.ims.entity.Sale;
import com.ims.repository.SaleRepository;
import com.ims.service.SaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void addSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void generateInvoice(InventoryItem[] items) {
        // TODO: implementation of generateInvoice
    }

    @Override
    public void modifySale(int id, Sale sale) {
        Sale existingSale = saleRepository.findById(id).orElse(null);
        if (existingSale != null) {
            existingSale.setName(sale.getName());
            existingSale.setDate(sale.getDate());
            existingSale.setSaleType(sale.getSaleType());
            existingSale.setPrice(sale.getPrice());
            saleRepository.save(existingSale);
        }
    }

    @Override
    public void removeSale(int id) {
        saleRepository.deleteById(id);
    }
}
