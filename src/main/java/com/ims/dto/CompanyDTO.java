package com.ims.dto;

import com.ims.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Integer id;
    private String name;
    private Integer contactNumber;
    private String email;
    private String address;
    private String description;
    private boolean isSupplier;
    private boolean isCustomer;

    public CompanyDTO(Company company) {
        BeanUtils.copyProperties(company, this);
    }
}

