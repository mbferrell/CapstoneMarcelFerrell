package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.domain.SalesTaxRate;

import java.math.BigDecimal;

public interface SalesTaxRateDao {
    BigDecimal getSalesTaxRate(String state);

    SalesTaxRate getSalesTaxRate2(String state);
}