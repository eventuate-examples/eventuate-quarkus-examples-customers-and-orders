package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import com.jayway.restassured.RestAssured;
import io.quarkus.test.junit.QuarkusTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@QuarkusTest
public class CustomerServiceInProcessComponentTest {

  @ConfigProperty(name = "quarkus.http.test-port")
  Integer port;

  @Test
  public void shouldCreateOrder() {
    String postUrl = baseUrl("/customers");

    String customerId = RestAssured.given().
      body(new CreateCustomerRequest("John Doe", new Money(1234))).
            contentType("application/json").
    when().
           post(postUrl).
    then().
           statusCode(200).
    extract().
        path("customerId");

    assertNotNull(customerId);


    Integer creditLimit = RestAssured.given().
            when().
            get(postUrl + "/" + customerId).
            then().
            statusCode(200).
            extract().
            path("creditLimit.amount");

    assertEquals(new Integer(1234), creditLimit);
  }

  private String baseUrl(String path) {
    return "http://localhost:" + port + path;
  }
}
