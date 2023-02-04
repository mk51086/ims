package com.ims.service;

import com.ims.dto.CompanyDTO;
import com.ims.entity.Company;

import java.util.List;

public interface SupplierService {
    CompanyDTO addSupplier(CompanyDTO supplier);
    CompanyDTO updateSupplier(CompanyDTO supplier);
    void deleteSupplier(int id);
    List<CompanyDTO> getSuppliers();
    CompanyDTO getSupplierById(int id);
}
