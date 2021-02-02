package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.web;

import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.GetCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend.Customer;
import net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend.CustomerService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path(value = "/customers")
public class CustomerController {

  @Inject
  CustomerService customerService;

  @POST
  public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
    EntityWithIdAndVersion<Customer> ewidv = customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit());
    return new CreateCustomerResponse(ewidv.getEntityId());
  }

  @Path("/{customerId}")
  @GET
  public GetCustomerResponse getCustomer(@PathParam("customerId") String customerId) {
    EntityWithMetadata<Customer> customerWithMetadata;
    try {
      customerWithMetadata = customerService.findById(customerId);
    } catch (EntityNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    Customer customer = customerWithMetadata.getEntity();

    return new GetCustomerResponse(customerWithMetadata.getEntityIdAndVersion().getEntityId(),
            customer.getCreditLimit(), customer.availableCredit());
  }
}
