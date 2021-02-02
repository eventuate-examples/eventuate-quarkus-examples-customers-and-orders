package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;

import javax.inject.Singleton;

@Singleton
public class OrderBackendConfiguration {

  @Singleton
  public OrderWorkflow orderWorkflow() {
    return new OrderWorkflow();
  }

  @Singleton
  public AggregateRepository<Order, OrderCommand> orderRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(Order.class, eventStore);
  }

}


