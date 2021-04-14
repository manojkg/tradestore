package com.test.example.tradestore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.example.tradestore.dto.TradeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TradeStoreControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @DisplayName("should approve user for given email")
  @Test
  void shouldAddUserToActiveDirectory() throws Exception {
    TradeDTO trade =TradeDTO.builder().build();
    mockMvc
        .perform(post("/v1/trade/store").contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(trade)))
        .andExpect(handler().handlerType(TradeStoreController.class))
        .andExpect(handler().methodName("storeTrade")).andExpect(status().isOk());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}