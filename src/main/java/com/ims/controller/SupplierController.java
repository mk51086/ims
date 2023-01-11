package com.ims.controller;

import com.ims.entity.Supplier;
import com.ims.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @PostMapping
    public Supplier addSupplier (@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @PutMapping
    public Supplier updateSupplier (@RequestBody Supplier supplier){
        return supplierService.updateSupplier(supplier);
    }
    @DeleteMapping("/{id}")
    public void deleteSupplier (@PathVariable int id){
         supplierService.deleteSupplier(id);
    }
    @GetMapping
    public List<Supplier> getSuppliers (){
        return supplierService.getSuppliers();
    }
    @GetMapping("/{id}")
    public Supplier getSupplierById (@PathVariable int id){
        return supplierService.getSupplierById(id);
    }

}
