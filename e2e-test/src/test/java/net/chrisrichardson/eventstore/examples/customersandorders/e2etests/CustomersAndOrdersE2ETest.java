package net.chrisrichardson.eventstore.examples.customersandorders.e2etests;

import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.commontest.AbstractCustomerAndOrdersIntegrationTest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@QuarkusTest
public class CustomersAndOrdersE2ETest extends AbstractCustomerAndOrdersIntegrationTest {
  @RestClient
  CustomerRestClientService customerRestClientService;

  @RestClient
  OrderRestClientService orderRestClientService;

  @RestClient
  CustomerViewRestClientService customerViewRestClientService;

  @RestClient
  OrderViewRestClientService orderViewRestClientService;

  private CustomerView getCustomer(String customerId) {
    return customerViewRestClientService.getCustomer(customerId);
  }

  @Override
  protected CustomerView getCustomerView(String customerId) {
    return getCustomer(customerId);
  }

  @Override
  protected OrderView getOrderView(String orderId) {
    return orderViewRestClientService.getOrder(orderId);
  }

  @Override
  protected String createOrder(String customerId, Money orderTotal) {
    try {
      return orderRestClientService.createOrder(new CreateOrderRequest(customerId, orderTotal)).getOrderId();
    } catch (WebApplicationException e) {
      if (e.getResponse().getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
        throw new IntegrationTestCustomerNotFoundException(e);
      }
      throw e;
    }
  }

  @Override
  protected String createCustomer(Money creditLimit) {
    return customerRestClientService.createCustomer(new CreateCustomerRequest("Fred", creditLimit)).getCustomerId();
  }
}
