package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.inject.Singleton;

@Singleton
public class OrderHistoryViewMongoFactory {
  @Singleton
  public MongoTemplate mongoTemplate(MongoClient mongoClient) {
    return new MongoTemplate(mongoClient, "customers_and_orders");
  }
}
