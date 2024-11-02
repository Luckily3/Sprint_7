import models.OrderCreationData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTest {

    private final String colors;

    public OrderCreationTest(final String colors) {
      this.colors = colors;
    }

    @Before
    public void setUp() {
      RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
      return new Object[][]{
              {"BLACK"},
              {"GREY"},
              {"BLACK, GREY"},
              {""},
      };
    }

    @Step("Создание заказа /api/v1/orders")
    public Response requestCreateOrder(OrderCreationData orderCreationData) {
      return given()
              .header("Content-type", "application/json")
              .and()
              .body(orderCreationData)
              .when()
              .post("/api/v1/orders");
    }

    @Step("Сравнение body на наличие track")
    public void compareBodyCreateOrderField(Response response) {
      response.then().assertThat().body("track", notNullValue());
    }

    @Step("Сравнение полученного кода")
    public void compareStatusCode(Response response, int code) {
      response.then().statusCode(code);
    }

    @Test
    public void testCreateOrder() {
      OrderCreationData orderCreationData = new OrderCreationData(
              "Test",
              "Test",
              "Test",
              "Test",
              "89261894567",
              1,
              "10.11.2024",
              "test",
              colors.split(",")
      );
      Response response = requestCreateOrder(orderCreationData);
      compareStatusCode(response, 201);
      compareBodyCreateOrderField(response);
    }
}
