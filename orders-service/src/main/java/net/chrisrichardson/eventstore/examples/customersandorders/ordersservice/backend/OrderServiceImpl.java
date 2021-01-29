package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OrderServiceImpl implements OrderService {

  @Inject
  AggregateRepository<Order, OrderCommand> orderRepository;

  @Inject
  CustomerService customerService;

  @Override
  public EntityWithIdAndVersion<Order> createOrder(String customerId, Money orderTotal) {
    customerService.verifyCustomerCustomerId(customerId);
    return orderRepository.save(new CreateOrderCommand(customerId, orderTotal));
  }
}
