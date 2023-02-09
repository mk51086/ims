package com.ims.service.impl;

import com.ims.dto.InvoiceDTO;
import com.ims.entity.Invoice;
import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.repository.InvoiceRepository;
import com.ims.repository.OrderRepository;
import com.ims.repository.SaleRepository;
import com.ims.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final SaleRepository saleRepository;
    private final OrderRepository orderRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, SaleRepository saleRepository, OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.saleRepository = saleRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public InvoiceDTO getInvoiceBySale(int saleId) {
        Sale sale = saleRepository.findById(saleId).stream().findFirst().orElse(null);
        Invoice invoice = invoiceRepository.findInvoiceBySaleItem(sale);
        return entityToModel(invoice);
    }

    @Override
    public InvoiceDTO getInvoiceByOrder(int orderId) {
        Order order = orderRepository.findById(orderId).stream().findFirst().orElse(null);
        Invoice invoice = invoiceRepository.findInvoiceByOrderItem(order);
        return entityToModel(invoice);
    }

    @Override
    public InvoiceDTO generateOrderInvoice(InvoiceDTO invoiceDTO) {
        Order order = orderRepository.findById(invoiceDTO.getOrderItem()).stream().findFirst().orElse(null);
        Invoice invoice = Invoice.builder()
                .total(invoiceDTO.getTotal())
                .tax(invoiceDTO.getTax())
                .orderItem(order)
                .number(invoiceDTO.getNumber())
                .build();
        invoiceRepository.save(invoice);
        return entityToModel(invoice);
    }

    @Override
    public InvoiceDTO generateSaleInvoice(InvoiceDTO invoiceDTo) {
        Sale sale = saleRepository.findById(invoiceDTo.getSaleItem()).stream().findFirst().orElse(null);
        Invoice invoice = Invoice.builder()
                .total(invoiceDTo.getTotal())
                .tax(invoiceDTo.getTax())
                .saleItem(sale)
                .number(invoiceDTo.getNumber())
                .build();
        invoiceRepository.save(invoice);
        return entityToModel(invoice);
    }

    private Invoice toEntity(InvoiceDTO invoiceDTO) {
        Order order = orderRepository.findById(invoiceDTO.getOrderItem()).stream().findFirst().orElse(null);
        Sale sale = saleRepository.findById(invoiceDTO.getSaleItem()).stream().findFirst().orElse(null);
        return Invoice.builder()
                .number(invoiceDTO.getNumber())
                .orderItem(order)
                .saleItem(sale)
                .tax(invoiceDTO.getTax())
                .total(invoiceDTO.getTotal())
                .build();
    }

    private InvoiceDTO entityToModel(Invoice invoice) {
        if (invoice.getSaleItem() != null) {
            return InvoiceDTO
                    .builder()
                    .number(invoice.getNumber())
                    .saleItem(invoice.getSaleItem().getId())
                    .tax(invoice.getTax())
                    .total(invoice.getTotal())
                    .build();
        } else {
            return InvoiceDTO
                    .builder()
                    .number(invoice.getNumber())
                    .orderItem(invoice.getOrderItem().getId())
                    .tax(invoice.getTax())
                    .total(invoice.getTotal())
                    .build();
        }
    }

}
