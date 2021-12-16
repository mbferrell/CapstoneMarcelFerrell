package com.company.MarcelFerrellCapstone.serviceLayer;

import com.company.MarcelFerrellCapstone.dao.*;
import com.company.MarcelFerrellCapstone.model.*;
import com.company.MarcelFerrellCapstone.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
public class InvoiceService {

    private final ConsoleDao consoleDao;
    private final GameDao gameDao;
    private final TShirtDao tShirtDao;
    private final SalesTaxRateDao salesTaxRateDao;
    private final ProcessingFeeDao processingFeeDao;
    private final InvoiceDao invoiceDao;

    @Autowired
    public InvoiceService(ConsoleDao consoleDao, GameDao gameDao, TShirtDao tShirtDao, SalesTaxRateDao salesTaxRateDao, ProcessingFeeDao processingFeeDao, InvoiceDao invoiceDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tShirtDao = tShirtDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.processingFeeDao = processingFeeDao;
        this.invoiceDao = invoiceDao;
    }


    //Invoice API
    @Transactional
    public Invoice saveInvoice(InvoiceViewModel viewModel){

        Invoice invoice = new Invoice();

        if(checkUpdateQuantity(viewModel)
                && viewModel.getQuantity() > 0
                && isCorrectStateCode(viewModel)){
            invoice.setName(viewModel.getName());
            invoice.setStreet(viewModel.getStreet());
            invoice.setCity(viewModel.getCity());
            invoice.setState(viewModel.getState());
            invoice.setZipcode(viewModel.getZip());
            invoice.setItemType(viewModel.getItemType());
            invoice.setItemId(viewModel.getItemID());
            invoice.setUnitPrice(getUnitPrice(viewModel));
            invoice.setQuantity(viewModel.getQuantity());
            invoice.setSubtotal(getSubTotal(viewModel));
            invoice.setTax(getStateTax(viewModel));
            invoice.setProcessingFee(getProcessingFee(viewModel));
            invoice.setTotal(calculatingTotal(viewModel));

            invoice = invoiceDao.addInvoice(invoice);

        }
        else{
            throw new IllegalArgumentException("Invalid Response");
        }


        return invoice;
    }

    public boolean isCorrectStateCode(InvoiceViewModel viewModel){
        SalesTaxRate state = salesTaxRateDao.getSalesTaxRate2(viewModel.getState());

        if(!viewModel.getState().equalsIgnoreCase(state.getState())){
            throw new IllegalArgumentException("Incorrect StateCode");
        }

        return true;
    }

    public BigDecimal calculatingTotal(InvoiceViewModel viewModel){

        //get the tax from the dao
        BigDecimal stateTax = getStateTax(viewModel);

        //getProcessingFee
        BigDecimal processingFee = getProcessingFee(viewModel);

        //get Subtotal
        BigDecimal subTotal = getSubTotal(viewModel);

        //calculating total = tax + processing fee + subtotal
        BigDecimal total = new BigDecimal("0.00");
        total = total.add(processingFee);
        total = total.add(stateTax);
        total = total.add(subTotal);

        return total;

    }

    //service to get unit price based off of invoice view model
    public BigDecimal getUnitPrice(InvoiceViewModel viewModel) {

        String item_type = viewModel.getItemType();
        BigDecimal unitPrice;

        if (item_type.equalsIgnoreCase("games")) {
            Game gameModel = gameDao.getGame(viewModel.getItemID());
            unitPrice = gameModel.getPrice();
        }
        else if (item_type.equalsIgnoreCase("consoles")) {
            Console consoleModel = consoleDao.getConsole(viewModel.getItemID());
            unitPrice = consoleModel.getPrice();
        }
        else if (item_type.equalsIgnoreCase("t-shirts")) {
            TShirt tShirtModel = tShirtDao.getTShirt(viewModel.getItemID());
            unitPrice = tShirtModel.getPrice();
        }
        else {
            throw new IllegalArgumentException("Invalid item_type");
        }
        return unitPrice;
    }

    //service to get state tax from invoice view model state
    public BigDecimal getStateTax (InvoiceViewModel viewModel){

        String state = viewModel.getState().toUpperCase();

        BigDecimal overallTax = salesTaxRateDao.getSalesTaxRate(state);

        overallTax = overallTax.multiply(getSubTotal(viewModel));
        overallTax = overallTax.setScale(2);

        return overallTax;
    }

    public BigDecimal getProcessingFee (InvoiceViewModel viewModel){
        //get the processing fee based on viewModel itemType ( need a function instead)
        BigDecimal processingfee = processingFeeDao.getProcessingFee(viewModel.getItemType());

        if (viewModel.getQuantity() > 10) {
            processingfee = processingfee.add(BigDecimal.valueOf(15.59), MathContext.DECIMAL32);
        }
        return processingfee;
    }

    //service to calculate the subTotal
    public BigDecimal getSubTotal (InvoiceViewModel viewModel){

        BigDecimal unitPrice = getUnitPrice(viewModel);

        BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(viewModel.getQuantity()));

        return subtotal;

    }

    public boolean checkUpdateQuantity(InvoiceViewModel viewModel){
        int quantity = viewModel.getQuantity();

        String item_type = viewModel.getItemType();

        if (item_type.equalsIgnoreCase("games")) {
            Game gameModel = gameDao.getGame(viewModel.getItemID());
            if(quantity > gameModel.getQuantity()){
                throw new IllegalArgumentException("Not enough in Game Inventory for" + gameModel.getGameId());
            }

            gameModel.setQuantity(gameModel.getQuantity() - quantity);
            gameDao.updateGame(gameModel);
        }
        else if (item_type.equalsIgnoreCase("consoles")) {
            Console consoleModel = consoleDao.getConsole(viewModel.getItemID());
            if(quantity > consoleModel.getQuantity()){
                throw new IllegalArgumentException("Not enough in Console Inventory " + consoleModel.getConsoleId());
            }

            consoleModel.setQuantity(consoleModel.getQuantity() - quantity);
            consoleDao.updateConsole(consoleModel);
        }
        else if (item_type.equalsIgnoreCase("t-shirts")) {
            TShirt tShirtModel = tShirtDao.getTShirt(viewModel.getItemID());
            if(quantity > tShirtModel.getQuantity()){
                throw new IllegalArgumentException("Not enough in TShirt Inventory " + tShirtModel.getShirtId());
            }
            tShirtModel.setQuantity(tShirtModel.getQuantity() - quantity);
            tShirtDao.updateTShirt(tShirtModel);
        }
        else {
            throw new IllegalArgumentException("Invalid item_type");
        }

        return true;
    }


    public Invoice getInvoice (int invoiceId){
        return invoiceDao.getInvoice(invoiceId);
    }

    public List<Invoice> getAllInvoices(){return invoiceDao.getAllInvoices();}

    public void updateInvoice(Invoice invoice){invoiceDao.updateInvoice(invoice);}

    public void deleteInvoice(int invoiceId){invoiceDao.deleteInvoice(invoiceId);}

    public List<Invoice> getInvoicesByCustomerName(String name){return invoiceDao.getInvoicesByCustomerName(name);}



}