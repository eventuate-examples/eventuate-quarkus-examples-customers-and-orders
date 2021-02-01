package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web.CustomerRestClientService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class CustomerServiceProxyIntegrationTest {

  @Inject
  CustomerServiceProxy customerServiceProxy;

  @Inject
  @RestClient
  CustomerRestClientService customerRestClientService;

  @Test
  public void shouldVerifyExistingCustomer() {
    CreateCustomerResponse response = customerRestClientService.createCustomer(
            new CreateCustomerRequest("Fred", new Money(123)));

    customerServiceProxy.verifyCustomerCustomerId(response.getCustomerId());
  }

  @Test
  public void shouldRejectNonExistentCustomer() {
    Assertions.assertThrows(CustomerNotFoundException.class,
            () -> customerServiceProxy.verifyCustomerCustomerId("1223232-none"));
  }

}
