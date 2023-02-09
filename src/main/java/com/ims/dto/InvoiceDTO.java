package com.ims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ims.entity.Invoice;
import com.ims.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InvoiceDTO {
    private int number;
    private int orderItem;
    private int saleItem;
    private double tax;
    private double total;
}
