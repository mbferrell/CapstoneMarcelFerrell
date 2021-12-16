package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.model.SalesTaxRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxRateDaoTest {

    @Autowired
    protected SalesTaxRateDao salesTaxRateDao;

    @Test
    public void getSalesTaxRateDao( ){
        String state = "VA";
        BigDecimal expectedRate = salesTaxRateDao.getSalesTaxRate(state);
        assertEquals(expectedRate, new BigDecimal("0.06"));
    }

    @Test
    public void getSalesTaxRateDao2( ){
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("VA");
        salesTaxRate.setRate(new BigDecimal("0.06"));

        SalesTaxRate salesTaxRate1 = salesTaxRateDao.getSalesTaxRate2(salesTaxRate.getState());

        assertEquals(salesTaxRate1,salesTaxRate);
    }
}