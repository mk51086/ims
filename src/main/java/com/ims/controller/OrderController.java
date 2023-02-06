package com.ims.controller;

import com.ims.dto.OrderDTO;
import com.ims.enums.OrderStatus;
import com.ims.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController (OrderService orderService){
        this.orderService=orderService;
    }
    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder (@PathVariable int id){
        orderService.deleteOrder(id);
    }
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(){
        List<OrderDTO> orders = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById (@PathVariable int id){
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> changeOrderStatus(@PathVariable Integer orderId, @RequestParam OrderStatus orderStatus){
        orderService.changeOrderStatus(orderId,orderStatus);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
