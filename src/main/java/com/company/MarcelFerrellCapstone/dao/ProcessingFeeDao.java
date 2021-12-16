package com.company.MarcelFerrellCapstone.dao;

import java.math.BigDecimal;

public interface ProcessingFeeDao {

    BigDecimal getProcessingFee(String productType);

}