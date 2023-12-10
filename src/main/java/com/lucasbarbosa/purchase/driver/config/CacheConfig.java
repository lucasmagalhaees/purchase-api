package com.lucasbarbosa.purchase.driver.config;

import static java.lang.Long.parseLong;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

  @Value("${feign.client.treasury.ttl}")
  private String ttl;

  @Override
  @Bean
  public CacheManager cacheManager() {
    return new GuavaCacheManager();
  }

  @Bean
  public CacheManager timeoutCacheManager() {
    GuavaCacheManager cacheManager = new GuavaCacheManager();
    CacheBuilder<Object, Object> cacheBuilder =
        CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(parseLong(ttl), TimeUnit.MINUTES);
    cacheManager.setCacheBuilder(cacheBuilder);
    return cacheManager;
  }
}
