package com.company.MarcelFerrellCapstone.controllers;

import com.company.MarcelFerrellCapstone.serviceLayer.InvoiceService;
import com.company.MarcelFerrellCapstone.domain.Invoice;
import com.company.MarcelFerrellCapstone.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    //create Invoice ->post
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody InvoiceViewModel invoice) {

        return  invoiceService.saveInvoice(invoice);
    }

    //get Invoice by id
    @RequestMapping(value="/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id){
        try{
            return invoiceService.getInvoice(id);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ID Not Found");
        }

    }

    //get all Invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoice(){
        List<Invoice> allInvoice = invoiceService.getAllInvoices();

        if(allInvoice == null){
            throw new IllegalArgumentException("No Invoices Found");
        }
        return allInvoice;
    }

    //update invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@RequestBody Invoice invoice){
        invoiceService.updateInvoice(invoice);

    }

    //delete a Invoice
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id){
        try{
            invoiceService.deleteInvoice(id);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ID not found");
        }
    }

    //get by invoice by Customer name
    @RequestMapping(value="/invoice/name/{name}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerName(@PathVariable String name){

        try{
            return invoiceService.getInvoicesByCustomerName(name);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Name not found");
        }
    }


}