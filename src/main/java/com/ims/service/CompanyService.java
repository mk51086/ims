package com.ims.service;

import com.ims.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO addCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(CompanyDTO companyDTO);
    void deleteCompany(int id);
    List<CompanyDTO> getSuppliers();
    List<CompanyDTO> getCustomers();
    CompanyDTO getCompanyById(int id);
}
