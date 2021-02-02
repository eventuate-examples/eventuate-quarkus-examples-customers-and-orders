package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;

import javax.inject.Singleton;


@Singleton
public class OrderViewRepository implements PanacheMongoRepositoryBase<OrderView, String> {
}
