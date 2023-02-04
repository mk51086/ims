package com.ims.service.impl;

import com.ims.dto.CompanyDTO;
import com.ims.entity.Company;
import com.ims.exception.company.CompanyNotFoundException;
import com.ims.repository.CompanyRepository;
import com.ims.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository repo){
        this.companyRepository = repo;
    }

    @Override
    public CompanyDTO addCompany(CompanyDTO companyDTO) {
        Company company = dtoToEntity(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return new CompanyDTO(savedCompany);
    }
    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        Company company = dtoToEntity(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return new CompanyDTO(savedCompany);
    }
    @Override
    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
    @Override
    public List<CompanyDTO> getSuppliers() {
        return this.companyRepository
                .findAll()
                .stream().filter(Company::isSupplier)
                .map(CompanyDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> getCustomers() {
        return this.companyRepository
                .findAll()
                .stream().filter(Company::isCustomer)
                .map(CompanyDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(int id) {
        Company company = this.companyRepository
                .findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        return new CompanyDTO(company);
    }

    private Company dtoToEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO, company);
        return company;
    }
}
