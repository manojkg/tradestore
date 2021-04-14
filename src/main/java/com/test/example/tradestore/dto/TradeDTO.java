package com.test.example.tradestore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ApiModel(description = "trade store")
public class TradeDTO {

  @JsonProperty("tradeId")
  private String tradeID;

  private Integer version;
  private String counterPartId;
  private String bookId;

  private String maturityDate;
  private String createdDate;
  private String expired;

}
