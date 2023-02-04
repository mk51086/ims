package com.ims.controller;

import com.ims.dto.CompanyDTO;
import com.ims.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public CompanyDTO addSupplier(@RequestBody CompanyDTO supplier) {
        return supplierService.addSupplier(supplier);
    }

    @PutMapping
    public CompanyDTO updateSupplier(@RequestBody CompanyDTO supplier) {
        return supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable int id) {
        supplierService.deleteSupplier(id);
    }

    @GetMapping
    public List<CompanyDTO> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping("/{id}")
    public CompanyDTO getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

}
