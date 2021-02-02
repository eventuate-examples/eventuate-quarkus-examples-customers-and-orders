package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.GetCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web.CustomerRestClientService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Singleton
public class CustomerServiceProxy implements CustomerService {

  private CustomerRestClientService customerRestClientService;

  @Inject
  public CustomerServiceProxy(@RestClient CustomerRestClientService customerRestClientService) {
    this.customerRestClientService = customerRestClientService;
  }

  @Override
  public void verifyCustomerCustomerId(String customerId) {
    GetCustomerResponse result = null;
    try {
      result = customerRestClientService.getCustomer(customerId);
    } catch (WebApplicationException e) {
      if (e.getResponse().getStatus() == NOT_FOUND.getStatusCode()) {
        throw new CustomerNotFoundException();
      }

      unrecognizedStatusCode(customerId, e.getResponse().getStatus());
    }
  }

  private void unrecognizedStatusCode(String customerId, int statusCode) {
    throw new RuntimeException(String.format("Unrecognized status code %s when fetching customerId %s",
            statusCode, customerId));
  }

}
