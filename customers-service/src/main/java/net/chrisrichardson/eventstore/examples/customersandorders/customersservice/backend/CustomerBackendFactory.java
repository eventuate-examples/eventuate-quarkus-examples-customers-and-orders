package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;

import javax.inject.Singleton;

@Singleton
public class CustomerBackendFactory {

  @Singleton
  public CustomerWorkflow customerWorkflow() {
    return new CustomerWorkflow();
  }

  @Singleton
  public AggregateRepository<Customer, CustomerCommand> customerRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(Customer.class, eventStore);
  }
}


