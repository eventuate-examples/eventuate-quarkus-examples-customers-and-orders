package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import org.springframework.web.client.RestTemplate;

import javax.inject.Singleton;

@Singleton
public class CustomerServiceProxyConfiguration {
  @Singleton
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
