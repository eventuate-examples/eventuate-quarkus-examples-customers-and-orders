package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web;


import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.GetCustomerResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/customers")
@RegisterRestClient(configKey="customer-api")
public interface CustomerRestClientService {

  @Path("/{customerId}")
  @GET
  GetCustomerResponse getCustomer(@PathParam("customerId") String customerId);

  @POST
  CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
}
