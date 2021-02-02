package net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon;

import io.quarkus.mongodb.panache.MongoEntity;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import org.bson.codecs.pojo.annotations.BsonId;

@MongoEntity(collection="Order")
public class OrderView {

  @BsonId
  private String id;

  private OrderState state;
  private Money orderTotal;

  public OrderView() {
  }

  public OrderView(String id, Money orderTotal) {
    this.id = id;
    this.orderTotal = orderTotal;
    this.state = OrderState.CREATED;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public OrderState getState() {
    return state;
  }

  public void setState(OrderState state) {
    this.state = state;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(Money orderTotal) {
    this.orderTotal = orderTotal;
  }
}
