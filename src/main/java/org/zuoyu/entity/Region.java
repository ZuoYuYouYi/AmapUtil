package org.zuoyu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用来封装JSON的实体类.
 *
 * @author zuoyu
 * @program amapdemo
 * @create 2018-08-24 14:41
 **/
public class Region implements Serializable {

  private String citycode;

  private String adcode;

  private String level;

  private String center;

  private String name;

  private String polyline;

  private List<Region> districts;

  public Region() {
  }

  public String getCitycode() {
    return citycode;
  }

  public void setCitycode(String citycode) {
    this.citycode = citycode;
  }

  public String getAdcode() {
    return adcode;
  }

  public void setAdcode(String adcode) {
    this.adcode = adcode;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getCenter() {
    return center;
  }

  public void setCenter(String center) {
    this.center = center;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Region> getDistricts() {
    return districts;
  }

  public void setDistricts(List<Region> districts) {
    this.districts = districts;
  }

  public String getPolyline() {
    return polyline;
  }

  public void setPolyline(String polyline) {
    this.polyline = polyline;
  }

  @Override
  public String toString() {
    return "Region{" +
        "citycode='" + citycode + '\'' +
        ", adcode='" + adcode + '\'' +
        ", level='" + level + '\'' +
        ", center='" + center + '\'' +
        ", name='" + name + '\'' +
        ", polyline='" + polyline + '\'' +
        ", districts=" + districts +
        '}';
  }
}
