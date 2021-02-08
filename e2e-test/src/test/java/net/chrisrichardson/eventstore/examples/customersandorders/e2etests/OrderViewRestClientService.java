package net.chrisrichardson.eventstore.examples.customersandorders.e2etests;


import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/orders")
@RegisterRestClient(configKey="order-view-api")
public interface OrderViewRestClientService {
  @Path("/{orderId}")
  @GET
  OrderView getOrder(@PathParam("orderId") String orderId);
}
