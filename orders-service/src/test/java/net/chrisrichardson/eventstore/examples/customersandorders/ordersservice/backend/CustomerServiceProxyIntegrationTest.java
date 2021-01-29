package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

//@QuarkusTest
public class CustomerServiceProxyIntegrationTest {

//  @Inject
//  CustomerServiceProxy customerServiceProxy;
//
//  @Inject
//  RestTemplate restTemplate;
//
//  @ConfigProperty(name = "customer.service.root.url")
//  String customerServiceRootUrl;
//
//  @Test
//  public void shouldVerifyExistingCustomer() {
//    ResponseEntity<CreateCustomerResponse> response = restTemplate.postForEntity(customerServiceRootUrl,
//            new CreateCustomerRequest("Fred", new Money(123)), CreateCustomerResponse.class);
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    customerServiceProxy.verifyCustomerCustomerId(response.getBody().getCustomerId());
//  }
//
//  @Test
//  public void shouldRejectNonExistentCustomer() {
//    Assertions.assertThrows(CustomerNotFoundException.class, () -> customerServiceProxy.verifyCustomerCustomerId("1223232-none"));
//  }

}
