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


import static generators.CourierGenerator.*;
import static org.hamcrest.CoreMatchers.*;

public class CourierCreateTest {
  private Courier courier;
  private CourierClient courierClient;
  private String courierId;
  private Courier courierWithoutName;
  private Courier courierWithNullLogin;
  private Courier courierWithoutLogin;
  private Courier courierWithNullPassword;
  private Courier courierWithoutPassword;



  @Before
  public void setUp() {
    RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    courier = randomCourier();
    courierClient = new CourierClient();
    courierWithoutName = randomCourierWithoutName();
    courierWithoutLogin = randomCourierWithoutLogin();
    courierWithNullLogin = randomCourierWithNullLogin();
    courierWithoutPassword = randomCourierWithoutPassword();
    courierWithNullPassword = randomCourierWithNullPassword();
  }


  @Test
  @DisplayName("Создание курьера: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void createCourier() {
    ValidatableResponse createResponse = courierClient.requestCreateCourier(courier);
    createResponse.assertThat().statusCode(HttpStatus.SC_CREATED).body("ok", is(true));

    ValidatableResponse loginResponse = courierClient.requestCourierLogin((courier));
    courierId = loginResponse.extract().path("id").toString();

    loginResponse.assertThat().statusCode(HttpStatus.SC_OK).body("id", notNullValue());
  }

  @Test
  @DisplayName("Создание курьера без имени: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void createCourierWithoutName() {
    ValidatableResponse createResponse = courierClient.requestCreateCourier(courierWithoutName);
    createResponse.assertThat().statusCode(HttpStatus.SC_CREATED).body("ok", is(true));
  }

  @Test
  @DisplayName("Создание курьера с существующим логином: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void createExistsCourier() {
    courierClient.requestCreateCourier(courier);

    ValidatableResponse createResponse = courierClient.requestCreateCourier(courier);
    createResponse.assertThat().statusCode(HttpStatus.SC_CONFLICT).body("message",
            is("Этот логин уже используется. Попробуйте другой."));
  }

  @Test
  @DisplayName("Создание курьера без логина: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void requestWithoutLogin() {
    ValidatableResponse createResponse = courierClient.requestCreateCourier(courierWithoutLogin);
    createResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
            is("Недостаточно данных для создания учетной записи"));
  }

  @Test
  @DisplayName("Создание курьера с пустым логином: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void requestNullLogin() {
    ValidatableResponse createResponse = courierClient.requestCreateCourier(courierWithNullLogin);
    createResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
            is("Недостаточно данных для создания учетной записи"));
  }

  @Test
  @DisplayName("Создание курьера без пароля: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void requestWithoutPassword() {
    ValidatableResponse createResponse = courierClient.requestCreateCourier(courierWithoutPassword);
    createResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
            is("Недостаточно данных для создания учетной записи"));
  }

  @Test
  @DisplayName("Создание курьера с пустым паролем: api/v1/courier")
  @Description("Проверка ожидаемого результата: statusCode и body")
  public void requestNullPassword() {
    ValidatableResponse createResponse = courierClient.requestCreateCourier(courierWithNullPassword);
    createResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
            is("Недостаточно данных для создания учетной записи"));
  }

  @After
  public void tearDown() {
    courierClient.courierDelete(courierId);
  }
}
