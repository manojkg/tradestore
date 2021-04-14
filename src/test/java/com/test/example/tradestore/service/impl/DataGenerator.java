package com.test.example.tradestore.service.impl;

import com.test.example.tradestore.dto.TradeDTO;

public class DataGenerator {

  public static TradeDTO createTradeDto(String tradeId, Integer version, String maturityDate) {
    return TradeDTO.builder()
        .tradeID(tradeId)
        .bookId("B1")
        .createdDate("04/11/2021")
        .counterPartId("c1")
        .expired("Y")
        .maturityDate(maturityDate)
        .version(version).build();
  }

}
