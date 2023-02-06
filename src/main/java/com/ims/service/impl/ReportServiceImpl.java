package com.ims.service.impl;

import com.ims.dto.SaleDTO;
import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.repository.OrderRepository;
import com.ims.repository.SaleRepository;
import com.ims.service.ReportService;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final OrderRepository orderRepository;

    public ReportServiceImpl(SaleRepository saleRepository, OrderRepository orderRepository) {
        this.saleRepository = saleRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Sale> getAllSales() {
        // TODO: further implementation of this method
        return saleRepository.findAll();
    }

    @Override
    public List<SaleDTO> listOfSalesByDay() {
        List<Sale> sales = saleRepository.findByDay();
        return sales.stream().map(sale -> new SaleDTO(sale)).collect(Collectors.toList());
    }

    @Override
    public List<SaleDTO> listOfSalesByMonth() {
        List<Sale> sales = saleRepository.findByMonth();
        return sales.stream().map(sale -> new SaleDTO(sale)).collect(Collectors.toList());
    }

    @Override
    public List<SaleDTO> listOfSalesByYear() {
        List<Sale> sales = saleRepository.findByYear();
        return  sales.stream().map(sale -> new SaleDTO(sale)).collect(Collectors.toList());
    }

    @Override
    public List<Order> listOfOrdersByDate(LocalDateTime startTime, LocalDateTime endTime) {
       List<Order> result = new ArrayList<>();
       List<Order> orders = orderRepository.findAll();
       for (Order order: orders){
            if(order.getDate().isAfter(startTime) && order.getDate().isBefore(endTime)){
                result.add(order);
            }
       }
       return result;
    }
    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public void generatePdf(List<SaleDTO> sales, String fileName) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Table table = new Table(5);

            Cell cell1 = new Cell();
            cell1.add(new Paragraph("Date"));
            cell1.setBackgroundColor(Color.GRAY);
            table.addCell(cell1);

            Cell cell2 = new Cell();
            cell2.add(new Paragraph("Name"));
            cell2.setBackgroundColor(Color.GRAY);
            table.addCell(cell2);

            Cell cell3 = new Cell();
            cell3.add(new Paragraph("Price"));
            cell3.setBackgroundColor(Color.GRAY);
            table.addCell(cell3);

            Cell cell4 = new Cell();
            cell4.add(new Paragraph("Sale Type"));
            cell4.setBackgroundColor(Color.GRAY);
            table.addCell(cell4);

            for (SaleDTO sale : sales) {
                table.addCell(sale.getDate().toString());
                table.addCell(sale.getName());
                table.addCell(String.valueOf(sale.getPrice()));
                table.addCell(sale.getSaleType());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
