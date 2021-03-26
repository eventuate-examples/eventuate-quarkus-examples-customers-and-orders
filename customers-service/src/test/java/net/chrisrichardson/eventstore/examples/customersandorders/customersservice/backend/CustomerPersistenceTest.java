package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.common.quarkus.jdbc.test.configuration.EmbeddedDatabaseProfile;
import io.eventuate.sync.AggregateRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
@TestProfile(EmbeddedDatabaseProfile.class)
public class CustomerPersistenceTest {

  @Inject
  AggregateRepository<Customer, CustomerCommand> aggregateRepository;

  @Test
  public void shouldCreateAndUpdateCustomer() {
    EntityWithIdAndVersion<Customer> cwm = aggregateRepository.save(new CreateCustomerCommand("Fred", new Money(1234)));

    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-1"));
    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-2"));
    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-3"));
  }
}
