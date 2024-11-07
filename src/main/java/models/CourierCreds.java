package models;

public class CourierCreds {
  private final String login;
  private final String password;


  public CourierCreds(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public static CourierCreds getCourierCreds(Courier courier) {
    return new CourierCreds(courier.getLogin(), courier.getPassword());
  }

  public static CourierCreds getCourierCredsWithoutPassword(Courier courier) {
    return new CourierCreds(courier.getLogin(), "");
  }

  public static CourierCreds getCourierCredsWithoutLogin(Courier courier) {
    return new CourierCreds("", courier.getPassword());
  }

}
