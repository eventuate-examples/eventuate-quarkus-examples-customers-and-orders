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
    customerViewRepository.createCustomer(customerId, customerName, creditLimit);
  }

  public void addOrder(String customerId, String orderId, Money orderTotal) {
    customerViewRepository.addOrder(customerId, orderId, orderTotal);
    orderViewRepository.createOrder(orderId, orderTotal);
  }

  public void approveOrder(String customerId, String orderId) {
    customerViewRepository.setOrderState(customerId, orderId, OrderState.APPROVED);
    orderViewRepository.setOrderState(orderId, OrderState.APPROVED);
  }

  public void rejectOrder(String customerId, String orderId) {
    customerViewRepository.setOrderState(customerId, orderId, OrderState.REJECTED);
    orderViewRepository.setOrderState(orderId, OrderState.REJECTED);
  }
}
