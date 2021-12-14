package com.company.MarcelFerrellCapstone.dao;


import com.company.MarcelFerrellCapstone.domain.ProcessingFee;

import java.math.BigDecimal;

public interface ProcessingFeeDao {

    BigDecimal getProcessingFee(String productType);

}