package com.test.example.tradestore.service;

import com.test.example.tradestore.dto.TradeDTO;

public interface TradeService {

  boolean storeTrade(TradeDTO trade);

  void markExpiryFroTrade();

}
