package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;

import javax.inject.Singleton;


@Singleton
public class OrderViewRepository implements PanacheMongoRepositoryBase<OrderView, String> {
  public void createOrder(String orderId, Money orderTotal) {
    mongoCollection().updateOne(Filters.eq("_id", orderId),
            Updates.set("orderTotal", orderTotal), new UpdateOptions().upsert(true));
  }

  public void setOrderState(String orderId, OrderState orderState) {
    mongoCollection().updateOne(Filters.eq("_id", orderId),
            Updates.set("state", orderState.name()), new UpdateOptions().upsert(true));
  }
}
