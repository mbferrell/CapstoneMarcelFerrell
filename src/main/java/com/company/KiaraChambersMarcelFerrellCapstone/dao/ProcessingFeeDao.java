package com.company.KiaraChambersMarcelFerrellCapstone.dao;

import java.math.BigDecimal;

public interface ProcessingFeeDao {

    BigDecimal getProcessingFee(String productType);

}