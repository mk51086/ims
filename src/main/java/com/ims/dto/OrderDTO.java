package com.ims.dto;

import com.ims.entity.Order;
import com.ims.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private String name;
    private String description;
    private OrderStatus orderStatus;
    private LocalDateTime date;
    private Integer quantity;
    private Integer supplierId;

    public OrderDTO(Order order){
        BeanUtils.copyProperties(order, this);
    }
    public Integer getSupplierId() {
        return supplierId;
    }
}
