package com.test.example.tradestore.repository;

import com.test.example.tradestore.dto.TradeDTO;
import java.util.Collection;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.stereotype.Component;

@Component
public class TradeRepository {

  private static MultiValuedMap<String, TradeDTO> tradeDTOMultiValuedMap = new ArrayListValuedHashMap<>();

  static {
    tradeDTOMultiValuedMap.put("110", TradeDTO.builder().tradeID("T1").version(1).build());
    tradeDTOMultiValuedMap.put("110", TradeDTO.builder().tradeID("T2").version(2).build());
  }

  public MultiValuedMap<String, TradeDTO> getAllTrades() {
    return tradeDTOMultiValuedMap;
  }

  public Collection<TradeDTO> getTradeByTradeId(String tradeId){
    Collection<TradeDTO> tradeDTOS = tradeDTOMultiValuedMap.get(tradeId);
    return tradeDTOS;
  }

  public boolean save(final TradeDTO tradeDTO) {
    return tradeDTOMultiValuedMap.put(tradeDTO.getTradeID().toString(), tradeDTO);
  }

  public boolean remove(String tradeID, TradeDTO existingRecord) {
    return tradeDTOMultiValuedMap.removeMapping(tradeID, existingRecord);
  }

  public void setExpiryFlagForExpiredTrade() {
    //TODO JPA or SQL  code to mark all trade which has expired
  }
}
