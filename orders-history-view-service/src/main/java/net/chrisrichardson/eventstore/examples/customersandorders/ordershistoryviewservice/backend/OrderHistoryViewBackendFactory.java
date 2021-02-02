package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import javax.inject.Singleton;

@Singleton
public class OrderHistoryViewBackendFactory {

  @Singleton
  public OrderHistoryViewWorkflow orderHistoryViewWorkflow(OrderHistoryViewService service) {
    return new OrderHistoryViewWorkflow(service);
  }
}
