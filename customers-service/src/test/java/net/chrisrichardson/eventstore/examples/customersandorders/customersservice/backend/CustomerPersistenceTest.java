package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.sync.AggregateRepository;
import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

//@QuarkusTest
public class CustomerPersistenceTest {

//  @Inject
  AggregateRepository<Customer, CustomerCommand> aggregateRepository;

//  @Test
  public void shouldCreateAndUpdateCustomer() {
    EntityWithIdAndVersion<Customer> cwm = aggregateRepository.save(new CreateCustomerCommand("Fred", new Money(1234)));

    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-1"));
    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-2"));
    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-3"));
  }
}
