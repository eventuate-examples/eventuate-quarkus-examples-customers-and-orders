package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.GetCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web.CustomerRestClientService;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceProxyTest {

  private static final String CUSTOMER_ID = "customer_id";
  private CustomerRestClientService customerRestClientService;
  private CustomerServiceProxy proxy;

  @Before
  public void setUp() {
    customerRestClientService = mock(CustomerRestClientService.class);
    proxy = new CustomerServiceProxy(customerRestClientService);
  }

  @Test
  public void shouldFindCustomer() {
    when(customerRestClientService.getCustomer(CUSTOMER_ID)).thenReturn(new GetCustomerResponse(CUSTOMER_ID, null, null));
    proxy.verifyCustomerCustomerId(CUSTOMER_ID);
    verify(customerRestClientService).getCustomer(CUSTOMER_ID);
  }

  @Test(expected=CustomerNotFoundException.class)
  public void shouldNotFindCustomer() {
    when(customerRestClientService.getCustomer(CUSTOMER_ID))
            .thenThrow(new WebApplicationException(404));

    proxy.verifyCustomerCustomerId(CUSTOMER_ID);
  }
}
