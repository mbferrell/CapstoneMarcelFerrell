package com.company.MarcelFerrellCapstone.serviceLayer;

import com.company.MarcelFerrellCapstone.dao.TShirtDao;
import com.company.MarcelFerrellCapstone.dao.TShirtDaoJdbcTemplateImpl;
import com.company.MarcelFerrellCapstone.model.TShirt;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TShirtServiceTest {

    TShirtService tShirtService;
    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception{
        setUpTShirtMock();

        tShirtService = new TShirtService(tShirtDao);
    }

    @Test
    public void saveFindTshirt(){
        TShirt shirt = new TShirt();
        shirt = new TShirt();
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(2);

        shirt = tShirtService.saveTshirt(shirt);

        TShirt fromTShirtService = tShirtService.getTshirt(shirt.getShirtId());

        assertEquals(shirt,fromTShirtService);
    }

    @Test
    public void findAllShirt(){
        List<TShirt> fromTshirtservice = tShirtService.getAllTshirts();

        assertEquals(1, fromTshirtservice.size());

    }

    @Test
    public void saveFindColor(){
        TShirt shirt = new TShirt();
        shirt = new TShirt();
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(2);

        shirt = tShirtService.saveTshirt(shirt);

        List<TShirt> tList = tShirtService.getbyColor(shirt.getColor());

        assertEquals(1,tList.size());
    }

    @Test
    public void saveFindSize(){
        TShirt shirt = new TShirt();
        shirt = new TShirt();
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(2);

        shirt = tShirtService.saveTshirt(shirt);

        List<TShirt> tList = tShirtService.getbySize(shirt.getSize());

        assertEquals(1,tList.size());
    }
    //helper methods
    private void setUpTShirtMock(){
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt shirt = new TShirt();
        shirt.settShirtId(3);
        shirt.setSize("small");
        shirt.setColor("black");
        shirt.setDescription("Marvel avengers long sleeve shirt ");
        shirt.setPrice(new BigDecimal("25.00"));
        shirt.setQuantity(2);

        TShirt shirt1 = new TShirt();
        shirt1.setSize("small");
        shirt1.setColor("black");
        shirt1.setDescription("Marvel avengers long sleeve shirt ");
        shirt1.setPrice(new BigDecimal("25.00"));
        shirt1.setQuantity(2);

        List<TShirt> tList = new ArrayList<>();
        tList.add(shirt);

        doReturn(shirt).when(tShirtDao).addTShirt(shirt1);
        doReturn(shirt).when(tShirtDao).getTShirt(3);
        doReturn(tList).when(tShirtDao).getAllTShirts();
        doReturn(tList).when(tShirtDao).getTShirtsBySize("small");
        doReturn(tList).when(tShirtDao).getTShirtsByColor("black");
    }

}