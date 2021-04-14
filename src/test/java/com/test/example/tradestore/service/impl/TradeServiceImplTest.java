package com.test.example.tradestore.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.test.example.tradestore.dto.TradeDTO;
import com.test.example.tradestore.repository.TradeRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  @InjectMocks
  private TradeServiceImpl tradeService;

  @Mock
  private TradeRepository tradeRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("should not allow trade which has less maturity date than today's date")
  void shouldNotAllowTradeWhichHasLessMaturityDate() {
    TradeDTO tradeDTO = DataGenerator.createTradeDto("T1", 1, "01/01/2021");
    Assertions.assertThrows(ResponseStatusException.class, () -> {
      tradeService.storeTrade(tradeDTO);
    });
  }

  @Test
  @DisplayName("should not allow trade which has less maturity date than today's date")
  void shouldNotAllowTradeWhichHasLowerTradeVersion() {
    TradeDTO tradeDTO = DataGenerator.createTradeDto("T1", 10, "01/01/2022");
    TradeDTO exitingTrade = DataGenerator.createTradeDto("T1", 12, "01/01/2022");

    Mockito.when(tradeRepository.getTradeByTradeId(anyString()))
        .thenReturn(Arrays.asList(exitingTrade));
    Assertions.assertThrows(ResponseStatusException.class, () -> {
      tradeService.storeTrade(tradeDTO);
    });
  }

  @Test
  @DisplayName("should not allow trade which has less maturity date than today's date")
  void shouldSaveCorrectTrade() {
    TradeDTO tradeDTO = DataGenerator.createTradeDto("T1", 10, "01/01/2022");
    TradeDTO exitingTrade = DataGenerator.createTradeDto("T1", 9, "01/01/2022");

    Mockito.when(tradeRepository.getTradeByTradeId(anyString()))
        .thenReturn(Arrays.asList(exitingTrade));
    Mockito.when(tradeRepository.save(any())).thenReturn(true);

    boolean b = tradeService.storeTrade(tradeDTO);
    assertTrue(b);
  }

}