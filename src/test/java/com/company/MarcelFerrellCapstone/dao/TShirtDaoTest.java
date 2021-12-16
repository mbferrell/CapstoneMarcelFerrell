package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoTest {

    @Autowired
    public TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception{
        List<TShirt> allTShirts = tShirtDao.getAllTShirts();
        for(TShirt a : allTShirts){
            tShirtDao.deleteTShirt(a.getShirtId());
        }
    }

    @Test
    public void addGetDeleteTShirt(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("Xl");
        tShirt.setColor("Black");
        tShirt.setDescription("Graphic Tee");
        tShirt.setPrice(new BigDecimal("49.99"));
        tShirt.setQuantity(1);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.getShirtId());

        assertEquals(tShirt,tShirt1);

        tShirtDao.deleteTShirt(tShirt.getShirtId());

        tShirt1 = tShirtDao.getTShirt(tShirt.getShirtId());

        assertNull(tShirt1);
    }

    @Test
    public void getAllTShirts(){

        TShirt tShirt = new TShirt();
        tShirt.setSize("Xl");
        tShirt.setColor("Black");
        tShirt.setDescription("Graphic Tee");
        tShirt.setPrice(new BigDecimal("49.99"));
        tShirt.setQuantity(1);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Flannel");
        tShirt2.setPrice(new BigDecimal("89.99"));
        tShirt2.setQuantity(1);

        tShirt2 = tShirtDao.addTShirt(tShirt2);

        List<TShirt> tShirts = tShirtDao.getAllTShirts();

        assertEquals(tShirts.size(), 2);

    }

    @Test
    public void updateTShirt(){

        TShirt tShirt = new TShirt();
        tShirt.setSize("Xl");
        tShirt.setColor("Black");
        tShirt.setDescription("Graphic Tee");
        tShirt.setPrice(new BigDecimal("49.99"));
        tShirt.setQuantity(1);

        tShirt = tShirtDao.addTShirt(tShirt);

        tShirt.setDescription("Discounted Graphic");
        tShirt.setPrice(new BigDecimal("39.99"));

        tShirtDao.updateTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.getShirtId());

        assertEquals(tShirt1, tShirt);
    }

    @Test
    public void getTShirtsByColor(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("Xl");
        tShirt.setColor("Black");
        tShirt.setDescription("Graphic Tee");
        tShirt.setPrice(new BigDecimal("49.99"));
        tShirt.setQuantity(1);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Flannel");
        tShirt2.setPrice(new BigDecimal("89.99"));
        tShirt2.setQuantity(1);

        tShirt2 = tShirtDao.addTShirt(tShirt2);

        TShirt tShirt3 = new TShirt();
        tShirt3.setSize("Xl");
        tShirt3.setColor("Blue");
        tShirt3.setDescription("Graphic Tee");
        tShirt3.setPrice(new BigDecimal("49.99"));
        tShirt3.setQuantity(1);

        tShirt3 = tShirtDao.addTShirt((tShirt3));

        List<TShirt> tShirts = tShirtDao.getTShirtsByColor("Blue");

        assertEquals(tShirts.size(), 2);

    }

    @Test
    public void getTShirtsBySize(){

        TShirt tShirt = new TShirt();
        tShirt.setSize("Xl");
        tShirt.setColor("Black");
        tShirt.setDescription("Graphic Tee");
        tShirt.setPrice(new BigDecimal("49.99"));
        tShirt.setQuantity(1);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Flannel");
        tShirt2.setPrice(new BigDecimal("89.99"));
        tShirt2.setQuantity(1);

        tShirt2 = tShirtDao.addTShirt(tShirt2);

        TShirt tShirt3 = new TShirt();
        tShirt3.setSize("Xl");
        tShirt3.setColor("Blue");
        tShirt3.setDescription("Graphic Tee");
        tShirt3.setPrice(new BigDecimal("49.99"));
        tShirt3.setQuantity(1);

        tShirt3 = tShirtDao.addTShirt((tShirt3));

        List<TShirt> tShirts = tShirtDao.getTShirtsBySize("Xl");

        assertEquals(tShirts.size(), 2);

    }
}