import client.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.Courier;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static generators.CourierGenerator.randomCourier;
import static org.hamcrest.CoreMatchers.*;

public class CourierLoginTest {
  private Courier courier;
  private CourierClient courierClient;
  private String courierId;

  @Before
  public void setUp() {
    RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    courier = randomCourier();
    courierClient = new CourierClient();

    }

  @Test
  @DisplayName("Успешный логин курьера: api/v1/courier/login")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void courierLogin() {
    courierClient.requestCreateCourier(courier);

    ValidatableResponse loginResponse = courierClient.requestCourierLogin((courier));
    courierId = loginResponse.extract().path("id").toString();

    loginResponse.assertThat().statusCode(HttpStatus.SC_OK).body("id", notNullValue());
  }

  @Test
  @DisplayName("Логин курьера без пароля: api/v1/courier/login")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void courierLoginWithoutPassword() {
    ValidatableResponse loginResponse = courierClient.requestLoginWithoutPassword((courier));

    loginResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
            is("Недостаточно данных для входа"));
  }

  @Test
  @DisplayName("Логин курьера без логина: api/v1/courier/login")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void courierLoginWithoutLogin() {
    ValidatableResponse loginResponse = courierClient.requestLoginWithoutLogin((courier));

    loginResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
            is("Недостаточно данных для входа"));
  }

  @Test
  @DisplayName("Логин курьера c несуществующем логином/паролем: api/v1/courier/login")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void courierLoginNonExistLoginPassword() {
    ValidatableResponse loginResponse = courierClient.requestCourierLogin((courier));

    loginResponse.assertThat().statusCode(HttpStatus.SC_NOT_FOUND).body("message",
            is("Учетная запись не найдена"));
  }

  @After
  public void tearDown() {
    courierClient.courierDelete(courierId);
  }

}