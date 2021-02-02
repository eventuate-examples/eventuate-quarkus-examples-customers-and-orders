package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;

import javax.inject.Singleton;


@Singleton
public class CustomerViewRepository implements PanacheMongoRepositoryBase<CustomerView, String> {

  public void addOrder(String customerId, String orderId, Money orderTotal) {
    BasicDBObject orderDocument = new BasicDBObject();
    orderDocument.put("orderId", orderId);
    orderDocument.put("orderTotal", orderTotal);

    mongoCollection().findOneAndUpdate(Filters.eq("_id", customerId),
            Updates.set("orders." + orderId, orderDocument));
  }

  public void setOrderState(String customerId, String orderId, OrderState orderState) {
    mongoCollection().findOneAndUpdate(Filters.eq("_id", customerId),
            Updates.set("orders." + orderId + ".state", orderState.name()));
  }
}
