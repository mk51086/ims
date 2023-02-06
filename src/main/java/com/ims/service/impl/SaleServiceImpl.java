package com.ims.service.impl;

import com.ims.dto.SaleDTO;
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
    public void addSale(SaleDTO saleDTO) {
        Sale sale1 = toEntity(saleDTO);
        saleRepository.save(sale1);
    }

    @Override
    public void generateInvoice(InventoryItem[] items) {
        // TODO: implementation of generateInvoice
    }

    @Override
    public void modifySale(int id, SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(id).orElse(null);
        if (existingSale != null) {
            existingSale.setName(saleDTO.getName());
            existingSale.setDate(saleDTO.getDate());
            existingSale.setSaleType(saleDTO.getSaleType());
            existingSale.setPrice(saleDTO.getPrice());
            saleRepository.save(existingSale);
        }
    }

    @Override
    public void removeSale(int id) {
        saleRepository.deleteById(id);
    }

    private Sale toEntity(SaleDTO saleDTO) {
        return Sale.builder()
                .name(saleDTO.getName())
                .date(saleDTO.getDate())
                .saleType(saleDTO.getSaleType())
                .price(saleDTO.getPrice())
                .build();
    }
}
