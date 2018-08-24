package org.zuoyu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zuoyu.config.AmapConfig;
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
   * 获取下一级区域的所有区域名字
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
}
