package com.lucasbarbosa.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class PurchaseApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PurchaseApiApplication.class, args);
  }
}
