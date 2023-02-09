package com.ims.repository;

import com.ims.entity.Invoice;
import com.ims.entity.Order;
import com.ims.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
        Invoice findInvoiceByOrderItem(Order order);
        Invoice findInvoiceBySaleItem(Sale sale);
}
