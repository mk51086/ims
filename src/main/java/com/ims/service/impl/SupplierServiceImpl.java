package com.ims.service.impl;

import com.ims.dto.CompanyDTO;
import com.ims.entity.Company;
import com.ims.exception.company.CompanyNotFoundException;
import com.ims.repository.CompanyRepository;
import com.ims.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final CompanyRepository supplierRepository;
    public SupplierServiceImpl (CompanyRepository repo){
        this.supplierRepository = repo;
    }

    @Override
    public CompanyDTO addSupplier(CompanyDTO supplierDto) {
        Company supplier = dtoToEntity(supplierDto);
        Company savedSupplier = supplierRepository.save(supplier);
        return new CompanyDTO(savedSupplier);
    }
    @Override
    public CompanyDTO updateSupplier(CompanyDTO supplierDto) {
        Company supplier = dtoToEntity(supplierDto);
        Company savedSupplier = supplierRepository.save(supplier);
        return new CompanyDTO(savedSupplier);
    }
    @Override
    public void deleteSupplier(int id) {
        supplierRepository.deleteById(id);
    }
    @Override
    public List<CompanyDTO> getSuppliers() {
        return this.supplierRepository
                .findAll()
                .stream()
                .map(CompanyDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getSupplierById(int id) {
        Company supplier = this.supplierRepository
                .findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        return new CompanyDTO(supplier);
    }

    private Company dtoToEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO, company);
        return company;
    }
}
