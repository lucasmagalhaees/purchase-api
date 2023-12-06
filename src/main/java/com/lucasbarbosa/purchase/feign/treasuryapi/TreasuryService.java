package com.lucasbarbosa.purchase.feign.treasuryapi;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Barbosa on 01/08/2021
 */
@Service
@Slf4j
public class TreasuryService {

  private final TreasuryClient treasuryClient;

  public TreasuryService(TreasuryClient treasuryClient) {
    this.treasuryClient = treasuryClient;
  }

  public List<TreasuryVO> getExchangeRates(String fields, String filter, String sort) {
    DataVO dataVO = treasuryClient.getExchangeRate(fields, filter, sort);
    log.info("m=TreasuryService#getExchangeRates exchangeRates: {} ", dataVO.getRates());
    return dataVO.getRates();
  }
}
