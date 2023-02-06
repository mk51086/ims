package com.ims.dto;

import com.ims.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    private int id;
    private String name;
    private Date date;
    private String saleType;
    private double price;

    public SaleDTO(Sale sale){
        BeanUtils.copyProperties(sale, this);
    }
}