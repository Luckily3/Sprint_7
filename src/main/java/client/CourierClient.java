package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.Courier;

import static io.restassured.RestAssured.given;
import static models.CourierCreds.*;

public class CourierClient {

  @Step("Создание курьера")
  public ValidatableResponse requestCreateCourier(Courier courier) {
    return given()
            .header("Content-type", "application/json")
            .and()
            .body(courier)
            .when()
            .post("/api/v1/courier")
            .then();
  }

  @Step("Логин курьера в системе")
  public ValidatableResponse requestCourierLogin(Courier courier) {
    return given()
            .header("Content-type", "application/json")
            .and()
            .body(getCourierCreds(courier))
            .when()
            .post("/api/v1/courier/login")
            .then();
  }

  @Step("Логин курьера без пароля")
  public ValidatableResponse requestLoginWithoutPassword(Courier courier) {
    return given()
            .header("Content-type", "application/json")
            .and()
            .body(getCourierCredsWithoutPassword(courier))
            .when()
            .post("/api/v1/courier/login")
            .then();
  }

  @Step("Логин курьера без логина")
  public ValidatableResponse requestLoginWithoutLogin(Courier courier) {
    return given()
            .header("Content-type", "application/json")
            .and()
            .body(getCourierCredsWithoutLogin(courier))
            .when()
            .post("/api/v1/courier/login")
            .then();
  }

  @Step("Логин курьера c несуществующими логином и паролем")
  public ValidatableResponse requestLoginNonExistLoginPassword(Courier courier) {
    return given()
            .header("Content-type", "application/json")
            .and()
            .body(courier)
            .when()
            .post("/api/v1/courier/login")
            .then();
  }



  @Step("Удаление id курьера")
  public void courierDelete(String courierId) {
    given()
            .header("Content-type", "application/json")
            .delete("/api/v1/courier/" + courierId)
            .then();
  }


}
