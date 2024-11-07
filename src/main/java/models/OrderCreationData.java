package models;

public class OrderCreationData {
  private String firstName;
  private String lastName;
  private String address;
  private String metroStation;
  private String phone;
  private int rentTime;
  private String deliveryDate;
  private String comment;
  private String[] color;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getMetroStation() {
    return metroStation;
  }

  public void setMetroStation(final String metroStation) {
    this.metroStation = metroStation;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }

  public int getRentTime() {
    return rentTime;
  }

  public void setRentTime(final int rentTime) {
    this.rentTime = rentTime;
  }

  public String getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(final String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(final String comment) {
    this.comment = comment;
  }

  public String[] getColor() {
    return color;
  }

  public void setColor(final String[] color) {
    this.color = color;
  }

  public OrderCreationData(final String firstName, final String lastName, final String address, final String metroStation, final String phone, final int rentTime, final String deliveryDate, final String comment, final String[] color) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.metroStation = metroStation;
    this.phone = phone;
    this.rentTime = rentTime;
    this.deliveryDate = deliveryDate;
    this.comment = comment;
    this.color = color;
  }
}
