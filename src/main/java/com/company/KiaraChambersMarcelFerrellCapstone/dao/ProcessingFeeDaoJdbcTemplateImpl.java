package com.company.KiaraChambersMarcelFerrellCapstone.dao;

import com.company.KiaraChambersMarcelFerrellCapstone.model.ProcessingFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {
    //prepared statements
    private static final String SELECT_PROCESS_FEE_SQL =
            "select * from processing_fee where product_type = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public BigDecimal getProcessingFee(String productType){

        ProcessingFee processingFee = jdbcTemplate.queryForObject(SELECT_PROCESS_FEE_SQL, this::mapRowToProcessingFee, productType);


        return processingFee.getFee();
    }


    //rowMapper
    private ProcessingFee mapRowToProcessingFee(ResultSet rs, int rowNum) throws SQLException {
        ProcessingFee processFee = new ProcessingFee();
        processFee.setProductType(rs.getString("product_type"));
        processFee.setFee(rs.getBigDecimal("fee"));
        return processFee;
    }

}