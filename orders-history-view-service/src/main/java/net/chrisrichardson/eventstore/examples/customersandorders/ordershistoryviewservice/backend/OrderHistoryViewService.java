package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OrderHistoryViewService {

  @Inject
  CustomerViewRepository customerViewRepository;

  @Inject
  OrderViewRepository orderViewRepository;

  public void createCustomer(String customerId, String customerName, Money creditLimit) {
    customerViewRepository.persist(new CustomerView(customerId, customerName, creditLimit));
  }

  public void addOrder(String customerId, String orderId, Money orderTotal) {
    customerViewRepository.addOrder(customerId, orderId, orderTotal);
    orderViewRepository.persist(new OrderView(orderId, orderTotal));
  }

  public void approveOrder(String customerId, String orderId) {
    customerViewRepository.setOrderState(customerId, orderId, OrderState.APPROVED);

    OrderView orderView = orderViewRepository.findById(orderId);
    orderView.setState(OrderState.APPROVED);
    orderViewRepository.persistOrUpdate(orderView);
  }

  public void rejectOrder(String customerId, String orderId) {
    customerViewRepository.setOrderState(customerId, orderId, OrderState.REJECTED);

    OrderView orderView = orderViewRepository.findById(orderId);
    orderView.setState(OrderState.REJECTED);
    orderViewRepository.persistOrUpdate(orderView);
  }
}
