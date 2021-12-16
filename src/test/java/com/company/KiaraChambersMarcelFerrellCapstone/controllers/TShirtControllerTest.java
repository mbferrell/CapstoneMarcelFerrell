package com.company.KiaraChambersMarcelFerrellCapstone.controllers;

import com.company.KiaraChambersMarcelFerrellCapstone.serviceLayer.TShirtService;

import com.company.KiaraChambersMarcelFerrellCapstone.model.TShirt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private TShirtService tShirtService;


    @Before
    public void setUp(){


    }
    @Before
    public void before() {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    @Test
    public void shouldReturnAllTShirtsInCollection() throws Exception {
        String outputJson = mapper.writeValueAsString(tShirtService);
        mockMvc.perform(
                        get("/TShirt"))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void shouldReturnNewTShirtOnPostRequest() throws Exception {
        TShirt shirt = new TShirt();
        shirt.setSize("X-Large");
        shirt.setColor("purple");
        shirt.setDescription("Summer short sleeve shirt with flower print");
        shirt.setPrice(new BigDecimal("10.00"));
        shirt.setQuantity(2);

        String inputJson = mapper.writeValueAsString(shirt);
        TShirt shirt2 = new TShirt();
        shirt2.setSize("small");
        shirt2.setColor("red");
        shirt2.setDescription("Spring short sleeve shirt with graphic images");
        shirt2.setPrice(new BigDecimal("15.00"));
        shirt2.setQuantity(1);
        String outputJson = mapper.writeValueAsString(shirt2);
        mockMvc.perform(
                        post("/TShirt/")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnTShirtsById() throws Exception {
        TShirt shirt = new TShirt();
        shirt.setSize("Large");
        shirt.setColor("white");
        shirt.setDescription("Plain ");
        shirt.setPrice(new BigDecimal("5.00"));
        shirt.setQuantity(5);
        shirt.settShirtId(2);

        mockMvc.perform(
                        get("/TShirt/2"))
                .andDo(print())
                .andExpect(status().isOk());


    }
    @Test
    public void shouldUpdateAndReturn204StatusCode() throws Exception {
        TShirt shirt = new TShirt();
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(2);
        String inputJson = mapper.writeValueAsString(shirt);
        mockMvc.perform(
                        put("/TShirt")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/TShirt/4"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void shouldReturnByColor() throws Exception {
        TShirt shirt = new TShirt();
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(2);

        mockMvc.perform(
                        get("/TShirt/color/red"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnBySize() throws Exception {
        TShirt shirt = new TShirt();
        shirt.setSize("X-Large");
        shirt.setColor("purple");
        shirt.setDescription("Summer short sleeve shirt with flower print");
        shirt.setPrice(new BigDecimal("10.00"));
        shirt.setQuantity(2);

        mockMvc.perform(
                        get("/TShirt/size/X-Large"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
