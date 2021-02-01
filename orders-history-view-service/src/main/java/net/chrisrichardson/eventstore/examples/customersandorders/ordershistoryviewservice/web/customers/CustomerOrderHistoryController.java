package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.web.customers;

import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.CustomerViewRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path(value = "/customers")
public class CustomerOrderHistoryController {

  @Inject
  CustomerViewRepository customerViewRepository;

  @Path("/{customerId}")
  @GET
  public CustomerView getCustomer(@PathParam("customerId") String customerId) {
    CustomerView customer = customerViewRepository.findOne(customerId);
    System.out.println("Found customer=" + customer + " for " + customerId);
    if (customer == null)
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    else
      return customer;
  }
}
