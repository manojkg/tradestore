package com.test.example.tradestore.service.impl;

import com.test.example.tradestore.dto.TradeDTO;
import com.test.example.tradestore.repository.TradeRepository;
import com.test.example.tradestore.service.TradeService;
import com.test.example.tradestore.util.DateUtil;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class TradeServiceImpl implements TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  @Override
  public boolean storeTrade(TradeDTO tradeDTO) {
    IsValidRequest(tradeDTO);
    Collection<TradeDTO> tradeDTOS = tradeRepository.getTradeByTradeId(tradeDTO.getTradeID().toString());
    if (!tradeDTOS.isEmpty()){
      Optional<TradeDTO> existingRecord = tradeDTOS.stream()
          .filter(t -> t.getVersion().equals(tradeDTO.getVersion())).findFirst();
      existingRecord.ifPresent(dto -> tradeRepository.remove(tradeDTO.getTradeID(), dto));
    }
    boolean save = tradeRepository.save(tradeDTO);
    log.info("after modification {}", tradeRepository.getAllTrades());
    return save;
  }

  @Override
  public void markExpiryFroTrade() {
   tradeRepository.setExpiryFlagForExpiredTrade();
  }

  private void IsValidRequest(TradeDTO tradeDTO) {

    if(DateUtil.getDate(tradeDTO.getMaturityDate(), "dd/MM/yyyy").isBefore(LocalDate.now())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maturity date should not be before today's date");
    }
    if(!isTradeVersionAllowed(tradeDTO)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid trade version");
    }
  }

  private boolean isTradeVersionAllowed(TradeDTO tradeDTO) {
    Collection<TradeDTO> tradeDTOS = tradeRepository
        .getTradeByTradeId(tradeDTO.getTradeID().toString());
    if(!CollectionUtils.isEmpty(tradeDTOS)){
      final TradeDTO latestTradeDTO = getLatestVersionForTrade(tradeDTOS);
      return tradeDTO.getVersion() >= latestTradeDTO.getVersion();
    }
    return true;
  }

  public TradeDTO getLatestVersionForTrade(Collection<TradeDTO> tradeDTOS)
  {
    return tradeDTOS.stream()
        .max(Comparator.comparing(TradeDTO::getVersion))
        .orElseThrow(NoSuchElementException::new);
  }
}
