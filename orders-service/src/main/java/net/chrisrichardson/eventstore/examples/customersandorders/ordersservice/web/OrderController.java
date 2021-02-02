package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web;

import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.CustomerNotFoundException;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.Order;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.OrderService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        try {
            EntityWithIdAndVersion<Order> order =
                    orderService.createOrder(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal());
            return new CreateOrderResponse(order.getEntityId());
        } catch (CustomerNotFoundException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
