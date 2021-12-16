package com.company.KiaraChambersMarcelFerrellCapstone.dao;

import com.company.KiaraChambersMarcelFerrellCapstone.model.Invoice;
import java.util.List;


public interface InvoiceDao {

    Invoice addInvoice (Invoice invoice);

    Invoice getInvoice (int invoiceId);

    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int invoiceId);

    List<Invoice> getInvoicesByCustomerName(String name);


}