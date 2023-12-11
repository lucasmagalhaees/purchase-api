package com.lucasbarbosa.purchase.feign.treasuryapi;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lucas Barbosa on 31/07/2021
 */
@FeignClient(name = "treasure", url = "${feign.client.treasury.url}")
public interface TreasuryClient {

  @Cacheable(value = "currencies", cacheManager = "timeoutCacheManager")
  @GetMapping("v1/accounting/od/rates_of_exchange")
  DataVO getExchangeRate(
      @RequestParam(name = "fields") String fields,
      @RequestParam(name = "filter") String filter,
      @RequestParam(name = "sort") String sort);
}
