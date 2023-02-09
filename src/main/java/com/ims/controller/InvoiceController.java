package com.ims.controller;

import com.ims.dto.InvoiceDTO;
import com.ims.entity.Category;
import com.ims.service.CategorySerivce;
import com.ims.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping("/sale/{id}")
    public InvoiceDTO getInvoiceBySale(@PathVariable int id){return invoiceService.getInvoiceBySale(id);}

    @GetMapping("/order/{id}")
    public InvoiceDTO getInvoiceByOrder(@PathVariable int id){return invoiceService.getInvoiceByOrder(id);}

}
