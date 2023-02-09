package com.ims.controller;

import com.ims.dto.CompanyDTO;
import com.ims.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@Api( tags = "Company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyDTO addCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.addCompany(companyDTO);
    }

    @PutMapping
    public CompanyDTO updateCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.updateCompany(companyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }

    @GetMapping("/suppliers")
    public List<CompanyDTO> getSuppliers() {
        return companyService.getSuppliers();
    }

    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/customers")
    public List<CompanyDTO> getCustomers(){
        return companyService.getCustomers();
    }

}
