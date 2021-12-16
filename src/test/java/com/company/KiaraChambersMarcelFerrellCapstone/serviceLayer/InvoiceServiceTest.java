package com.company.KiaraChambersMarcelFerrellCapstone.serviceLayer;

import com.company.KiaraChambersMarcelFerrellCapstone.dao.*;
import com.company.KiaraChambersMarcelFerrellCapstone.model.*;
import com.company.KiaraChambersMarcelFerrellCapstone.viewModel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InvoiceServiceTest {

    InvoiceService invoiceService;
    GameDao gameDao;
    ConsoleDao consoleDao;
    InvoiceDao invoiceDao;
    TShirtDao tShirtDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;

    @Before
    public void setUp() throws Exception{
        setUpGameDao();
        setUpConsoleDao();
        setupTshirtDao();
        setUpInvoiceDao();
        setUpProcessingDao();
        setUpSalesTaxRateDao();

        invoiceService= new InvoiceService(consoleDao,gameDao,tShirtDao,salesTaxRateDao,processingFeeDao,invoiceDao);
    }
    @Test
    public void shouldCheckUpdateQuantity() {
        InvoiceViewModel viewModel = new InvoiceViewModel();
        viewModel.setName("Jake");
        viewModel.setStreet("Wood Pine Drive");
        viewModel.setCity("Newport News");
        viewModel.setState("VA");
        viewModel.setZip("23064");
        viewModel.setItemType("games");
        viewModel.setItemID(1);
        viewModel.setQuantity(2);

        boolean updateQuantity = invoiceService.checkUpdateQuantity(viewModel);

        assertEquals(true, updateQuantity);
    }

    @Test
    public void shouldCheckValidStateCode(){
        InvoiceViewModel viewModel = new InvoiceViewModel();
        viewModel.setName("Jake");
        viewModel.setStreet("Wood Pine Drive");
        viewModel.setCity("Newport News");
        viewModel.setState("VA");
        viewModel.setZip("23064");
        viewModel.setItemType("games");
        viewModel.setItemID(1);
        viewModel.setQuantity(2);

        boolean validState = invoiceService.isCorrectStateCode(viewModel);

        assertEquals(true, validState);
    }


    @Test
    public void saveInvoicefromViewModel(){

        //game test
        InvoiceViewModel viewModel = new InvoiceViewModel();
        viewModel.setName("Jake");
        viewModel.setStreet("Wood Pine Drive");
        viewModel.setCity("Newport News");
        viewModel.setState("VA");
        viewModel.setZip("23064");
        viewModel.setItemType("games");
        viewModel.setItemID(1);
        viewModel.setQuantity(2);

        //save Invoice is testing all the get methods that included
        //in the service layer(processing fee,subtotal,state tax,unit price,total)
        Invoice invoice = invoiceService.saveInvoice(viewModel);


        Invoice invoice1 = invoiceService.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice,invoice1);

        //console test
        InvoiceViewModel consoleviewModel = new InvoiceViewModel();
        consoleviewModel.setName("Blake");
        consoleviewModel.setStreet("Ghent");
        consoleviewModel.setCity("Norfolk");
        consoleviewModel.setState("VA");
        consoleviewModel.setZip("23504");
        consoleviewModel.setItemType("consoles");
        consoleviewModel.setQuantity(12);
        consoleviewModel.setItemID(23456);

        invoice = invoiceService.saveInvoice(consoleviewModel);

        invoice1 = invoiceService.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice,invoice1);

        //tshirt test
        InvoiceViewModel tshirtViewModel = new InvoiceViewModel();
        tshirtViewModel.setName("Jasmine");
        tshirtViewModel.setStreet("Ghent");
        tshirtViewModel.setCity("Norfolk");
        tshirtViewModel.setState("VA");
        tshirtViewModel.setZip("23504");
        tshirtViewModel.setItemType("t-shirts");
        tshirtViewModel.setQuantity(11);
        tshirtViewModel.setItemID(23456);

        invoice = invoiceService.saveInvoice(tshirtViewModel);

        invoice1 = invoiceService.getInvoice(invoice.getInvoiceId());


        assertEquals(invoice,invoice1);


    }

    @Test
    public void findAllInvoices(){
        List<Invoice> fromInvoiceService = invoiceService.getAllInvoices();
        assertEquals(1,fromInvoiceService.size());
    }

    @Test
    public void saveFindName(){
        InvoiceViewModel viewModel = new InvoiceViewModel();
        viewModel.setName("Jake");
        viewModel.setStreet("Wood Pine Drive");
        viewModel.setCity("Newport News");
        viewModel.setState("VA");
        viewModel.setZip("23064");
        viewModel.setItemType("games");
        viewModel.setItemID(1);
        viewModel.setQuantity(2);

        Invoice invoice = invoiceService.saveInvoice(viewModel);

        List<Invoice> iList = invoiceService.getInvoicesByCustomerName(invoice.getName());

        assertEquals(1, iList.size());
    }


    //helper methods
    private void setUpGameDao(){
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Black Ops");
        game.setEsrbRating("Rated-R");
        game.setDescription("shooting game");
        game.setPrice(new BigDecimal("10.00"));
        game.setStudio("Activision");
        game.setQuantity(3);

        Game game1 = new Game();
        game1.setTitle("Black Ops");
        game1.setEsrbRating("Rated-R");
        game1.setDescription("shooting game");
        game1.setPrice(new BigDecimal("10.00"));
        game1.setStudio("Activision");
        game1.setQuantity(3);

        List<Game> gList = new ArrayList<>();
        gList.add(game);

        doReturn(game).when(gameDao).getGame(1);

    }

    private void setUpConsoleDao(){
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console =new Console();
        console.setConsoleId(23456);
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Zen 2");
        console.setPrice(new BigDecimal("300.00"));
        console.setQuantity(50);

        Console console1 = new Console();
        console1.setModel("Playstation 5");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("500GB");
        console1.setProcessor("AMD Zen 2");
        console1.setPrice(new BigDecimal("300.00"));
        console1.setQuantity(50);

        List<Console> cList = new ArrayList<>();
        cList.add(console);

        doReturn(console).when(consoleDao).getConsole(console.getConsoleId());
    }

    private void setupTshirtDao(){
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt shirt = new TShirt();
        shirt.settShirtId(222);
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(60);

        TShirt shirt1 = new TShirt();
        shirt1.setSize("small");
        shirt1.setColor("black");
        shirt1.setDescription("Marvel avengers long sleeve shirt ");
        shirt1.setPrice(new BigDecimal("25.00"));
        shirt1.setQuantity(60);

        List<TShirt> tList = new ArrayList<>();
        tList.add(shirt);

        doReturn(shirt).when(tShirtDao).getTShirt(shirt.getShirtId());
    }

    private void setUpInvoiceDao(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        //game invoice mock
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(12);
        invoice.setName("Jake");
        invoice.setStreet("Wood Pine Drive");
        invoice.setCity("Newport News");
        invoice.setState("VA");
        invoice.setZipcode("23064");
        invoice.setItemType("games");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("10.00"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("20.00"));
        invoice.setTax(new BigDecimal("1.20"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("22.69"));

        Invoice invoice1 = new Invoice();
        invoice1.setName("Jake");
        invoice1.setStreet("Wood Pine Drive");
        invoice1.setCity("Newport News");
        invoice1.setState("VA");
        invoice1.setZipcode("23064");
        invoice1.setItemType("games");
        invoice1.setItemId(1);
        invoice1.setUnitPrice(new BigDecimal("10.00"));
        invoice1.setQuantity(2);
        invoice1.setSubtotal(new BigDecimal("20.00"));
        invoice1.setTax(new BigDecimal("1.20"));
        invoice1.setProcessingFee(new BigDecimal("1.49"));
        invoice1.setTotal(new BigDecimal("22.69"));

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(invoice.getInvoiceId());
        doReturn(iList).when(invoiceDao).getAllInvoices();
        doReturn(iList).when(invoiceDao).getInvoicesByCustomerName("Jake");


        //console mock
        Invoice console = new Invoice();
        console.setInvoiceId(10);
        console.setName("Blake");
        console.setStreet("Ghent");
        console.setCity("Norfolk");
        console.setState("VA");
        console.setZipcode("23504");
        console.setItemType("consoles");
        console.setQuantity(12);
        console.setItemId(23456);
        console.setTax(new BigDecimal("216.00"));
        console.setSubtotal(new BigDecimal("3600.00"));
        console.setProcessingFee(new BigDecimal("30.58"));
        console.setUnitPrice(new BigDecimal("300.00"));
        console.setTotal(new BigDecimal("3846.58"));

        Invoice console1 = new Invoice();
        console1.setName("Blake");
        console1.setStreet("Ghent");
        console1.setCity("Norfolk");
        console1.setState("VA");
        console1.setZipcode("23504");
        console1.setItemType("consoles");
        console1.setQuantity(12);
        console1.setItemId(23456);
        console1.setTax(new BigDecimal("216.00"));
        console1.setSubtotal(new BigDecimal("3600.00"));
        console1.setProcessingFee(new BigDecimal("30.58"));
        console1.setUnitPrice(new BigDecimal("300.00"));
        console1.setTotal(new BigDecimal("3846.58"));

        doReturn(console).when(invoiceDao).addInvoice(console1);
        doReturn(console).when(invoiceDao).getInvoice(console.getInvoiceId());


        //tshirt mock
        Invoice tShirt = new Invoice();
        tShirt.setInvoiceId(15);
        tShirt.setName("Jasmine");
        tShirt.setStreet("Ghent");
        tShirt.setCity("Norfolk");
        tShirt.setState("VA");
        tShirt.setZipcode("23504");
        tShirt.setItemType("t-shirt");
        tShirt.setQuantity(11);
        tShirt.setItemId(23456);
        tShirt.setTax(new BigDecimal("16.50"));
        tShirt.setSubtotal(new BigDecimal("275.00"));
        tShirt.setProcessingFee(new BigDecimal("17.57"));
        tShirt.setUnitPrice(new BigDecimal("25.00"));
        tShirt.setTotal(new BigDecimal("309.07"));

        Invoice tShirt1 = new Invoice();
        tShirt1.setName("Jasmine");
        tShirt1.setStreet("Ghent");
        tShirt1.setCity("Norfolk");
        tShirt1.setState("VA");
        tShirt1.setZipcode("23504");
        tShirt1.setItemType("t-shirts");
        tShirt1.setQuantity(11);
        tShirt1.setItemId(23456);
        tShirt1.setTax(new BigDecimal("16.50"));
        tShirt1.setSubtotal(new BigDecimal("275.00"));
        tShirt1.setProcessingFee(new BigDecimal("17.57"));
        tShirt1.setUnitPrice(new BigDecimal("25.00"));
        tShirt1.setTotal(new BigDecimal("309.07"));


        doReturn(tShirt).when(invoiceDao).addInvoice(tShirt1);
        doReturn(tShirt).when(invoiceDao).getInvoice(tShirt.getInvoiceId());
    }

    private void setUpProcessingDao(){

        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("games");
        gameProcessingFee.setFee(new BigDecimal("1.49"));

        ProcessingFee shirtProcessingFee = new ProcessingFee();
        shirtProcessingFee.setProductType("t-shirts");
        shirtProcessingFee.setFee(new BigDecimal("1.98"));

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("consoles");
        consoleProcessingFee.setFee(new BigDecimal("14.99"));

        doReturn(gameProcessingFee.getFee()).when(processingFeeDao).getProcessingFee("games");
        doReturn(shirtProcessingFee.getFee()).when(processingFeeDao).getProcessingFee("t-shirts");
        doReturn(consoleProcessingFee.getFee()).when(processingFeeDao).getProcessingFee("consoles");
    }

    private void setUpSalesTaxRateDao(){
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate taxRate = new SalesTaxRate();
        taxRate.setState("VA");
        taxRate.setRate(new BigDecimal("0.06"));

        doReturn(taxRate.getRate()).when(salesTaxRateDao).getSalesTaxRate("VA");
        doReturn(taxRate).when(salesTaxRateDao).getSalesTaxRate2("VA");

    }

}