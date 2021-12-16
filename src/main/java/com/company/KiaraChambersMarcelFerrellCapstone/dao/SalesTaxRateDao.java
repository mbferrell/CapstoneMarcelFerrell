package com.company.KiaraChambersMarcelFerrellCapstone.dao;

import com.company.KiaraChambersMarcelFerrellCapstone.model.SalesTaxRate;

import java.math.BigDecimal;

public interface SalesTaxRateDao {
    BigDecimal getSalesTaxRate(String state);

    SalesTaxRate getSalesTaxRate2(String state);
}