package org.zuoyu.entity;

/**
 * 地区JSON信息封装.
 *
 * @author zuoyu
 * @program amapdemo
 * @create 2018-08-24 21:02
 **/
public class Address {

  private String longitude;

  private String latitude;

  private String country;

  private String province;

  private String city;

  private String district;

  private String township;

  public Address() {
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getTownship() {
    return township;
  }

  public void setTownship(String township) {
    this.township = township;
  }

  @Override
  public String toString() {
    return "Address{" +
        "longitude='" + longitude + '\'' +
        ", latitude='" + latitude + '\'' +
        ", country='" + country + '\'' +
        ", province='" + province + '\'' +
        ", city='" + city + '\'' +
        ", district='" + district + '\'' +
        ", township='" + township + '\'' +
        '}';
  }
}
