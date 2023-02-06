package com.ims.controller;

import com.ims.dto.SaleDTO;
import com.ims.entity.InventoryItem;
import com.ims.entity.Sale;
import com.ims.service.SaleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@Api( tags = "Sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public void addSale(@RequestBody SaleDTO sale) {
        saleService.addSale(sale);
    }

    @PostMapping("/invoice")
    public void generateInvoice(@RequestBody InventoryItem[] items) {
        saleService.generateInvoice(items);
    }

    @PutMapping("/{id}")
    public void modifySale(@PathVariable int id, @RequestBody SaleDTO sale) {
        saleService.modifySale(id, sale);
    }

    @DeleteMapping("/{id}")
    public void removeSale(@PathVariable int id) {
        saleService.removeSale(id);
    }
}
