package net.chrisrichardson.eventstore.examples.customersandorders.views.orderhistory;

import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.CustomerViewRepository;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.OrderHistoryViewService;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.OrderViewRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderHistoryViewServiceTest {

  @Inject
  OrderHistoryViewService orderHistoryViewService;

  @Inject
  CustomerViewRepository customerViewRepository;

  @Inject
  OrderViewRepository orderViewRepository;

  @Test
  public void shouldCreateCustomerAndOrdersEtc() {
    String customerId = UUID.randomUUID().toString();
    Money creditLimit = new Money(2000);
    String customerName = "Fred";

    String orderId1 = UUID.randomUUID().toString();
    Money orderTotal1 = new Money(1234);
    String orderId2 = UUID.randomUUID().toString();
    Money orderTotal2 = new Money(3000);

    orderHistoryViewService.createCustomer(customerId, customerName, creditLimit);
    orderHistoryViewService.addOrder(customerId, orderId1, orderTotal1);
    orderHistoryViewService.approveOrder(customerId, orderId1);

    orderHistoryViewService.addOrder(customerId, orderId2, orderTotal2);
    orderHistoryViewService.rejectOrder(customerId, orderId2);

    CustomerView customerView = customerViewRepository.findById(customerId);


    assertEquals(2, customerView.getOrders().size());
    assertNotNull(customerView.getOrders().get(orderId1));
    assertNotNull(customerView.getOrders().get(orderId2));
    assertEquals(orderTotal1, customerView.getOrders().get(orderId1).getOrderTotal());
    assertEquals(OrderState.APPROVED, customerView.getOrders().get(orderId1).getState());

    assertNotNull(customerView.getOrders().get(orderId2));
    assertEquals(orderTotal2, customerView.getOrders().get(orderId2).getOrderTotal());
    assertEquals(OrderState.REJECTED, customerView.getOrders().get(orderId2).getState());

    OrderView orderView1 = orderViewRepository.findById(orderId1);
    assertEquals(orderTotal1, orderView1.getOrderTotal());
    assertEquals(OrderState.APPROVED, orderView1.getState());

    OrderView orderView2 = orderViewRepository.findById(orderId2);
    assertEquals(orderTotal2, orderView2.getOrderTotal());
    assertEquals(OrderState.REJECTED, orderView2.getState());
  }
}