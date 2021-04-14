package com.test.example.tradestore.controller;

import com.test.example.tradestore.dto.TradeDTO;
import com.test.example.tradestore.service.TradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/trade")
public class TradeStoreController {

  @Autowired
  private TradeService tradeService;

  @PostMapping(value = "/store", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 201,
          message = "Successful operation, returns id to the newly created user request."),})
  @ResponseBody
  @ApiOperation(value = "Get treatments for the person")
  public ResponseEntity<TradeDTO> storeTrade( @RequestBody TradeDTO trade) throws  Exception{
    tradeService.storeTrade(trade);
    return new ResponseEntity<TradeDTO>(trade, HttpStatus.OK);
  }

}
