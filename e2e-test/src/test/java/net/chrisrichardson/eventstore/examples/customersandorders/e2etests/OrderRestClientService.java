package net.chrisrichardson.eventstore.examples.customersandorders.e2etests;


import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/orders")
@RegisterRestClient(configKey="order-api")
public interface OrderRestClientService {
  @POST
  CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
