package generators;

import models.Courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

  public static String randomString(int length) {
    return RandomStringUtils.randomAlphanumeric(length);
  }

  public static Courier randomCourier() {
    return new Courier()
            .withLogin(randomString(8))
            .withPassword(randomString(12))
            .withFirstName(randomString(20));
  }

  public static Courier randomCourierWithoutName() {
    return new Courier()
            .withLogin(randomString(8))
            .withPassword(randomString(12));
  }

  public static Courier randomCourierWithoutLogin() {
    return new Courier()
            .withPassword(randomString(12))
            .withFirstName(randomString(20));
  }

  public static Courier randomCourierWithNullLogin() {
    return new Courier()
            .withLogin("")
            .withPassword(randomString(12))
            .withFirstName(randomString(20));
  }

  public static Courier randomCourierWithoutPassword() {
    return new Courier()
            .withLogin(randomString(8))
            .withFirstName(randomString(20));
  }

  public static Courier randomCourierWithNullPassword() {
    return new Courier()
            .withLogin(randomString(8))
            .withPassword("")
            .withFirstName(randomString(20));
  }
}
