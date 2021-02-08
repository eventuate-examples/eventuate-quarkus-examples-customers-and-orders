package net.chrisrichardson.eventstore.examples.customersandorders.e2etests;


import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/customers")
@RegisterRestClient(configKey="customer-view-api")
public interface CustomerViewRestClientService {

  @Path("/{customerId}")
  @GET
  CustomerView getCustomer(@PathParam("customerId") String customerId);
}
