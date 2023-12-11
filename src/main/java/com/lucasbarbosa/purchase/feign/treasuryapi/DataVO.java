package com.lucasbarbosa.purchase.feign.treasuryapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataVO {

  @JsonProperty("data")
  private List<TreasuryVO> rates;
}
