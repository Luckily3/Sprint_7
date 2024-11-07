import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest {

  @Before
  public void setUp() {
    RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
  }

  @Step("Send POST request to /api/v1/orders")
  public Response requestListOrder(){
    return given()
            .header("Content-type", "application/json")
            .and()
            .get("/api/v1/orders");
  }

  @Step("Сравнения body на наличие orders")
  public void compareBodyOrderField( Response response){
    response.then().assertThat().body("orders", notNullValue());
  }

  @Test
  public void getListOrdersTest() {
    Response response = requestListOrder();
    compareBodyOrderField(response);
  }
}
