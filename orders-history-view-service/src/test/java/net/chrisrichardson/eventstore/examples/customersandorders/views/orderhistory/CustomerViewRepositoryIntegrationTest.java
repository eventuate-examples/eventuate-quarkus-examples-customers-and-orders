package net.chrisrichardson.eventstore.examples.customersandorders.views.orderhistory;

import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.CustomerViewRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CustomerViewRepositoryIntegrationTest {

  @Inject
  CustomerViewRepository customerViewRepository;

  @Test
  public void shouldCreateAndFindCustomer() {

    String customerId = UUID.randomUUID().toString();
    Money creditLimit = new Money(2000);
    String customerName = "Fred";

    customerViewRepository.addCustomer(customerId, customerName, creditLimit);
    CustomerView customerView = customerViewRepository.findOne(customerId);

    assertEquals(customerId, customerView.getId());
    assertEquals(customerName, customerView.getName());
    assertEquals(0, customerView.getOrders().size());
    assertEquals(creditLimit, customerView.getCreditLimit());
  }
}
