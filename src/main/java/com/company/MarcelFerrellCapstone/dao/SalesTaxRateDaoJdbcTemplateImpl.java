package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.domain.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao{
    //prepared statements

    private static final String SELECT_SALES_TAX_RATE_SQL =
            "select * from sales_tax_rate where state = ?";



    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public BigDecimal getSalesTaxRate(String state) {
        SalesTaxRate sales= jdbcTemplate.queryForObject(SELECT_SALES_TAX_RATE_SQL, this::mapRowToSalesTaxRate, state);

        return sales.getRate();

    }

    @Override
    public SalesTaxRate getSalesTaxRate2(String state){
        SalesTaxRate sale = jdbcTemplate.queryForObject(SELECT_SALES_TAX_RATE_SQL, this::mapRowToSalesTaxRate, state);

        return sale;
    }


    //rowMapper
    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));
        return salesTaxRate;
    }

}