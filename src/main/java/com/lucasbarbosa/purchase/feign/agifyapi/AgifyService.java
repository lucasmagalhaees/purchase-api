package com.lucasbarbosa.purchase.feign.agifyapi;

import static com.lucasbarbosa.purchase.driver.utils.PurchaseUtils.searchMapByParam;
import static com.lucasbarbosa.purchase.feign.IntegrationParamEnum.CUSTOMER_NAME;

import com.lucasbarbosa.purchase.feign.IntegrationClient;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Barbosa on 01/08/2021
 */
@Service
@Slf4j
public class AgifyService implements IntegrationClient<Integer, AgifyService> {

  private final AgifyClient agifyClient;

  public AgifyService(AgifyClient agifyClient) {
    this.agifyClient = agifyClient;
  }

  @Override
  public Optional<Integer> writeClientIntegration(Optional<Map<String, Object>> params) {
    String customerName = searchMapByParam(params, CUSTOMER_NAME);

    return Optional.ofNullable(agifyClient.findCustomerAge(customerName))
        .map(
            agifyVO -> {
              log.info("m=retrieveCustomerAge age={}", agifyVO);
              return agifyVO.getAge();
            })
        .or(
            () -> {
              log.warn("m=retrieveCustomerAge failed");
              return Optional.of(10);
            });
  }

  @Override
  public Class<AgifyService> identify() {
    return AgifyService.class;
  }

  @Override
  public Optional<Integer> value() {
    return Optional.of(10);
  }
}
