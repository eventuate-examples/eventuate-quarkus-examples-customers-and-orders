package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.web.orders;

import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.OrderViewRepository;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path(value = "/orders")
public class OrderViewController {

  @Inject
  OrderViewRepository orderViewRepository;

  @Path("/{orderId}")
  @GET
  public OrderView getOrder(@PathParam("orderId") String orderId) {

    OrderView ov = orderViewRepository.findOne(orderId);

    if (ov == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } else {
      return ov;
    }
  }
}
