package org.zuoyu;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.zuoyu.config.AmapConfig;
import org.zuoyu.entity.Address;
import org.zuoyu.entity.Region;
import org.zuoyu.util.AmapServerUtil;
import org.zuoyu.util.HttpClientUtils;

/**
 * 测试.
 *
 * @author zuoyu
 * @program amapdemo
 * @create 2018-08-24 14:13
 **/
public class HttpTest {

  private StringBuilder ss = new StringBuilder();

  /**
   * 测试下一级名字
   */
  @Test
  public void TestOne() {
    List<String> stringList = AmapServerUtil.getNextLevelName("中国");
    stringList.forEach(s -> {
      System.out.println(s);
    });
  }

  /**
   * 测试指定区域包含的经纬度集合
   */
  @Test
  public void TestTwo() {
    Region region = AmapServerUtil.getRegion("焦作");
    List<String> stringList = AmapServerUtil.getPolylineList(region);
    stringList.forEach(s -> {
      System.out.println(s);
    });
//    System.out.println(stringList.size());
  }

  /**
   * 测试查询强度
   */
  @Test
  public void TestThree() {
//    Region region = AmapServerUtil.getRegion("北京");   /*默认*/
//    Region region = AmapServerUtil.getRegion("北京", 1);  /*一级*/
//    Region region = AmapServerUtil.getRegion("北京", 2);  /*二级*/
    Region region = AmapServerUtil.getRegion("北京", 3);  /*三级*/
    System.out.println(region.getDistricts());


  }

  @Test
  public void TestFour() {
    digui("河南");
  }

  public void digui(String s1) {

    List<String> stringList = AmapServerUtil.getNextLevelName(s1);
    stringList.forEach(s -> {
        if (!AmapServerUtil.isNextLevel(s)){
          return;
        }
        digui(s);
      System.out.println(s);
    });
  }

  /**
   * 测试根据坐标获取地址
   */
  @Test
  public void TestSix(){
    Address address = AmapServerUtil.getAddress("113.664", "34.7505");
    System.out.println(address);
  }

  @Test
  public void TestSeven(){
    Region region = AmapServerUtil.getRegion("二七区");
    List<String> stringList = AmapServerUtil.getPolylineList(region);
    stringList.forEach(s -> {
      Address address = AmapServerUtil.getAddress(s);
      System.out.println(address.toString());
    });
    System.out.println(stringList.size());
  }
}
