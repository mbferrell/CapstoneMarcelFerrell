package com.company.MarcelFerrellCapstone.controllers;

import com.company.MarcelFerrellCapstone.serviceLayer.InvoiceService;
import com.company.MarcelFerrellCapstone.model.Invoice;
import com.company.MarcelFerrellCapstone.viewModel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Before;
import org.junit.Test;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import java.math.BigDecimal;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private InvoiceService invoiceService;


    @Before
    public void setUp(){


    }
    @Before
    public void before() {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    @Test
    public void shouldReturnAllInvoicesInCollection() throws Exception {
        String outputJson = mapper.writeValueAsString(invoiceService);
        mockMvc.perform(
                        get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void shouldReturnNewInvoiceOnPostRequest() throws Exception {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("Bill");
        invoice.setStreet("Pinetta Drive");
        invoice.setCity("Richmond");
        invoice.setState("VA");
        invoice.setZip("23164");
        invoice.setItemType("Games");
        invoice.setItemID(56);
        invoice.setQuantity(1);


        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        post("/invoice")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void shouldReturnInvoicesById() throws Exception {

        mockMvc.perform(
                        get("/invoice/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateReturn204StatusCode() throws Exception {
        Invoice invoice = new Invoice();
        invoice = new Invoice();
        invoice.setName("Ryan");
        invoice.setStreet("Willsbury Road");
        invoice.setCity("Williamsburg");
        invoice.setState("VA");
        invoice.setZipcode("23164");
        invoice.setItemType( "console");
        invoice.setItemId(3 );
        invoice.setQuantity(1);

        String inputJson = mapper.writeValueAsString(invoice);
        mockMvc.perform(
                        put("/invoice")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/invoice/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void shouldReturnByCustomerName() throws Exception {

        mockMvc.perform(
                        get("/invoice/name/Jake"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}