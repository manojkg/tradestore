package com.test.example.tradestore.scheduler;

import com.test.example.tradestore.service.TradeService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TradeMaturityDateScheduler {

  @Autowired
  private TradeService tradeService;

  @Scheduled(cron = "*/5 * * * * ?")
  public void cronJobSch() {
    //every 5 second
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now = new Date();
    String strDate = sdf.format(now);
    log.info("expiry flag job:: {}" , strDate);
    tradeService.markExpiryFroTrade();
  }
}
