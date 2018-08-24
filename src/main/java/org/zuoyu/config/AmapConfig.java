package org.zuoyu.config;

public class AmapConfig {

  /**
   * 高德地图请求秘钥
   */
  public static final String KEY = "af84045d52d8529e64af6fa34b238686";
  /**
   * 返回值类型
   */
  public static final String OUTPUT = "JSON";
  /**
   * 根据地名获取高德经纬度Api
   */
  public static final String GET_LNG_LAT_URL = "http://restapi.amap.com/v3/geocode/geo";
  /**
   * 根据高德经纬度获取地名Api
   */
  public static final String GET_ADDRESS_URL = "https://restapi.amap.com/v3/geocode/regeo";
  /**
   * 获取行政区域
   */
  public static final String GET_REGION_URL = "https://restapi.amap.com/v3/config/district";
}
