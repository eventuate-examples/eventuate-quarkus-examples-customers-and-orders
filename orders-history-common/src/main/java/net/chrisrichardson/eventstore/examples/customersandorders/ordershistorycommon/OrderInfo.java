package net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;

public class OrderInfo {

  private OrderState state;
  private String orderId;
  private Money orderTotal;

  public OrderInfo() {
  }

  public OrderInfo(String orderId, Money orderTotal) {
    this.orderId = orderId;
    this.orderTotal = orderTotal;
    this.state = OrderState.CREATED;
  }

  public OrderState getState() {
    return state;
  }

  public void setState(OrderState state) {
    this.state = state;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(Money orderTotal) {
    this.orderTotal = orderTotal;
  }
}
