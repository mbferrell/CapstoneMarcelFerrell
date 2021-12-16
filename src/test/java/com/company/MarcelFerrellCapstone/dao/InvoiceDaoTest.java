package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    protected InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception{
        List<Invoice> iList = invoiceDao.getAllInvoices();
        for (Invoice i:iList){
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteInvoice(){

        Invoice invoice = new Invoice();
        invoice.setName("Dalonte");
        invoice.setStreet("700 Park Ave");
        invoice.setCity("Norfolk");
        invoice.setState("Virginia");
        invoice.setZipcode("23504");
        invoice.setItemType("game");
        invoice.setItemId(222);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("59.99"));
        invoice.setTax(new BigDecimal("0.06"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("65.08"));

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice2,invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice2);
    }
    @Test
    public void getAllInvoices(){

        Invoice invoice = new Invoice();
        invoice.setName("Dalonte");
        invoice.setStreet("700 Park Ave");
        invoice.setCity("Norfolk");
        invoice.setState("Virginia");
        invoice.setZipcode("23504");
        invoice.setItemType("game");
        invoice.setItemId(222);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("59.99"));
        invoice.setTax(new BigDecimal("0.06"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("65.08"));

        invoice = invoiceDao.addInvoice(invoice);
        invoice = new Invoice();
        invoice.setName("Nateyana");
        invoice.setStreet("007 Park Ave");
        invoice.setCity("Norfolk");
        invoice.setState("Virginia");
        invoice.setZipcode("23504");
        invoice.setItemType( "console");
        invoice.setItemId(123 );
        invoice.setUnitPrice(new BigDecimal("299.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("299.99"));
        invoice.setTax(new BigDecimal("0.06"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("332.98"));

        invoice = invoiceDao.addInvoice(invoice);
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        assertEquals(invoiceList.size(), 2);
    }
    @Test
    public void getInvoicesByCustomerName(){

        Invoice invoice = new Invoice();
        invoice.setName("Dalonte");
        invoice.setStreet("700 Park Ave");
        invoice.setCity("Norfolk");
        invoice.setState("Virginia");
        invoice.setZipcode("23504");
        invoice.setItemType("game");
        invoice.setItemId(222);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("59.99"));
        invoice.setTax(new BigDecimal("0.06"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("65.08"));

        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getInvoicesByCustomerName(invoice.getName());

        assertEquals(1,iList.size());
    }

    @Test
    public void updateInvoice(){

        Invoice invoice = new Invoice();
        invoice.setName("Dalonte");
        invoice.setStreet("700 Park Ave");
        invoice.setCity("Norfolk");
        invoice.setState("Virginia");
        invoice.setZipcode("23504");
        invoice.setItemType("game");
        invoice.setItemId(222);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("59.99"));
        invoice.setTax(new BigDecimal("0.06"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("65.08"));

        invoice = invoiceDao.addInvoice(invoice);

        invoice.setCity("Chesterfield");
        invoice.setStreet("Eastfair rd");
        invoice.setZipcode("23838");

        invoiceDao.updateInvoice(invoice);
        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice2,invoice);
    }
}
