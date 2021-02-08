package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;

import javax.inject.Singleton;


@Singleton
public class CustomerViewRepository implements PanacheMongoRepositoryBase<CustomerView, String> {

  public void createCustomer(String customerId, String customerName, Money creditLimit) {
    mongoCollection().updateOne(Filters.eq("_id", customerId),
            Updates.combine(Updates.set("name", customerName), Updates.set("creditLimit", creditLimit)), new UpdateOptions().upsert(true));
  }

  public void addOrder(String customerId, String orderId, Money orderTotal) {
    mongoCollection().updateOne(Filters.eq("_id", customerId),
            Updates.set("orders." + orderId + ".orderTotal", orderTotal), new UpdateOptions().upsert(true));
  }

  public void setOrderState(String customerId, String orderId, OrderState orderState) {
    mongoCollection().updateOne(Filters.eq("_id", customerId),
            Updates.set("orders." + orderId + ".state", orderState.name()), new UpdateOptions().upsert(true));
  }
}
