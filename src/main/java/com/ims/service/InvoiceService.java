package com.ims.service;

import com.ims.dto.InvoiceDTO;

public interface InvoiceService {
    InvoiceDTO getInvoiceBySale(int saleId);
    InvoiceDTO getInvoiceByOrder(int orderId);

    InvoiceDTO generateOrderInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO generateSaleInvoice(InvoiceDTO invoiceDTO);
}
