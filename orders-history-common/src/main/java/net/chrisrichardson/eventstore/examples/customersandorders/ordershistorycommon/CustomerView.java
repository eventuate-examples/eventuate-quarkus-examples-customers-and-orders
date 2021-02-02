package net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon;

import io.quarkus.mongodb.panache.MongoEntity;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.HashMap;
import java.util.Map;

@MongoEntity(collection="Customer")
public class CustomerView {

  @BsonId
  private String id;

  private Map<String, OrderInfo> orders = new HashMap<>();
  private String name;
  private Money creditLimit;

  public CustomerView() {
  }

  public CustomerView(String id, String name, Money creditLimit) {
    this.id = id;
    this.name = name;
    this.creditLimit = creditLimit;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, OrderInfo> getOrders() {
    return orders;
  }

  public void setOrders(Map<String, OrderInfo> orders) {
    this.orders = orders;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(Money creditLimit) {
    this.creditLimit = creditLimit;
  }
}
