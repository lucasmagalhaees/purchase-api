package com.lucasbarbosa.purchase.driver.exception.custom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Lucas Barbosa on 27/06/2021
 */
@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

  private String first;
  private String second;
  private String third;

  public BusinessException(String first) {
    this.first = first;
  }

  public BusinessException(String first, String second) {
    this.first = first;
    this.second = second;
  }

  public BusinessException(String first, String second, String third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }
}
