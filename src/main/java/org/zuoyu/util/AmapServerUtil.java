package org.zuoyu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.zuoyu.exception.MyException;
import org.zuoyu.config.AmapConfig;
import org.zuoyu.entity.Address;
import org.zuoyu.entity.Region;

/**
 * 地图服务工具类.
 *
 * @author zuoyu
 * @program amapdemo
 * @create 2018-08-24 14:21
 **/
public class AmapServerUtil {

  /**
   * 获取地理区域信息(仅返回查询的地址信息，不查下级).
   * @param regionName - 地理的名字
   * @return region - 信息封装
   */
  public static Region getRegion (String regionName) {
    return getRegion(regionName, 0);
  }

  /**
   * 获取地理区域信息.
   * @param regionName - 地理的名字
   * @param level -  查询强度
   * 0：不返回下级行政区；
   *
   * 1：返回下一级行政区；
   *
   * 2：返回下两级行政区；
   *
   * 3：返回下三级行政区；
   *
   * @return region - 信息封装
   */
  public static Region getRegion (String regionName, int level) {
    Map<String, String> maps = new HashMap<String, String>();
    maps.put("keywords", regionName);
    maps.put("subdistrict", String.valueOf(level));
    maps.put("key", AmapConfig.KEY);
    maps.put("extensions", "all");
    String text = HttpClientUtils.doGet(AmapConfig.GET_REGION_URL, maps);
    JSONObject object = JSON.parseObject(text);
    JSONArray jsonArray = (JSONArray) object.get("districts");
    List<Region> regionList = jsonArray.toJavaList(Region.class);
    return regionList.get(0);
  }

  /**
   * 获取该地理区域所包含的经纬信息.
   * @param region - 该区域
   * @return polylineList - 经纬信息集合
   */
  public static List<String> getPolylineList(Region region) {
    String regionPolyline = region.getPolyline();
    if (regionPolyline == null || "".equals(regionPolyline)) {
      return null;
    }
    String[] polyline = regionPolyline.split(";");
    return Arrays.asList(polyline);
  }

  /**
   * 获取下一级区域的所有区域名字.
   * @param regionName - 区域名字
   * @return nameList - 名字集合
   */
  public static List<String> getNextLevelName(String regionName){
    Region region = getRegion(regionName, 1);
    List<Region> regionList = region.getDistricts();
    if (regionList.isEmpty()) {
      return null;
    }
    List<String> nameList = new ArrayList<String>();
    regionList.forEach(reg -> {
      nameList.add(reg.getName());
    });
    return nameList;
  }

  /**
   * 是否还有下级.
   * @param regionName - 区域名字
   * @return boolean
   */
  public static boolean isNextLevel(String regionName) {
    Region region = getRegion(regionName, 1);
    List<Region> regionList = region.getDistricts();
    return !regionList.isEmpty();
  }

  /**
   * 根据经纬度查询地址信息
   * @param location - 经纬度
   * @return address - 地址信息封装对象
   */
  public static Address getAddress(String location) {
    Map<String, String> maps = new HashMap<String, String>();
    maps.put("location", location);
    maps.put("output", AmapConfig.OUTPUT);
    maps.put("key", AmapConfig.KEY);
    String text = HttpClientUtils.doGet(AmapConfig.GET_ADDRESS_URL, maps);
    JSONObject object = JSON.parseObject(text);
    JSONObject jsonObject= (JSONObject) object.get("regeocode");
    JSONObject addressComponent = (JSONObject) jsonObject.get("addressComponent");
    Address address = addressComponent.toJavaObject(Address.class);
    String[] locations = location.split(",");
    address.setLongitude(locations[0]);
    address.setLatitude(locations[1]);
    return address;
  }

  /**
   * 根据经纬度查询地址信息
   * @param longitude - 经度
   * @param latitude - 维度
   * @return address - 地址信息封装对象
   */
  public static Address getAddress(String longitude, String latitude) {
    StringBuilder location = new StringBuilder();
    location.append(longitude).append(",").append(latitude);
    return getAddress(location.toString());
  }

  /**
   * 根据经纬度集合批量查询地址信息
   * @param locationList - 经纬度集合，最多20条
   * @return List<Address> - 地址信息集合
   */
  public static List<Address> getAddressList(List<String> locationList) {
    Map<String, String> maps = new HashMap<String, String>();
    if (locationList.size() > 20) {
      throw new MyException("经纬度个数不得超过20条！");
    }
    String locations = locationsFormat(locationList);
    maps.put("location", locations);
    maps.put("output", AmapConfig.OUTPUT);
    maps.put("key", AmapConfig.KEY);
    maps.put("batch", "true");
    String text = HttpClientUtils.doGet(AmapConfig.GET_ADDRESS_URL, maps);
    JSONObject object = JSON.parseObject(text);
    JSONArray jsonArray = (JSONArray) object.get("regeocodes");
    List<Address> addressList = new ArrayList<Address>();
//    jdk8新特性，原子操作类，线程安全
    AtomicInteger atomicInteger = new AtomicInteger();
    jsonArray.forEach(json -> {
      String jsonString = JSON.toJSONString(json);
      JSONObject jsonObject = JSON.parseObject(jsonString);
      JSONObject addressComponent = (JSONObject) jsonObject.get("addressComponent");
      Address address = addressComponent.toJavaObject(Address.class);
      String location = locationList.get(atomicInteger.get());
      String[] splits = location.split(",");
      address.setLongitude(splits[0]);
      address.setLatitude(splits[1]);
      addressList.add(address);
      atomicInteger.getAndIncrement();
    });
    return addressList;
  }

  /**
   * 将经纬度集合转化为可查询的字符串
   * @param locationList - 经纬度集合
   * @return locations - 可查询的格式
   */
  private static String locationsFormat(List<String> locationList) {
    StringBuilder locations = new StringBuilder();
    String s = "|";
    locationList.forEach(location -> {
      if (location.contains(s)){
        int x = location.indexOf(s);
        location = location.substring(0, x);
      }
      if (locations.length() > 0) {
        locations.append(s);
      }
      locations.append(location);
    });
    return locations.toString();
  }
}
