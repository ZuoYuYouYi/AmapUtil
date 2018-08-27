
package org.zuoyu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;
import org.zuoyu.entity.Address;
import org.zuoyu.entity.Region;
import org.zuoyu.util.AmapServerUtil;

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
    List<String> stringList = new ArrayList<String>();
    stringList.add("113.664,34.7505");
    stringList.add("116.481488,39.990464");
    stringList.add("113.664,34.7505");
    stringList.add("113.664,34.7505");
    stringList.add("116.481488,39.990464");
    stringList.add("113.664,34.7505");
    stringList.add("113.664,34.7505");
    stringList.add("116.481488,39.990464");
    stringList.add("113.664,34.7505");
    stringList.add("113.664,34.7505");
    stringList.add("116.481488,39.990464");
    stringList.add("113.664,34.7505");
    stringList.add("113.664,34.7505");
    stringList.add("116.481488,39.990464");

    List<Address> addressList = AmapServerUtil.getAddressList(stringList);
    addressList.forEach(System.out::println);
  }


  /**
   * 测试根据坐标获取地址
   */
  @Test
  public void TestSix(){
    Address address = AmapServerUtil.getAddress("116.481488,39.990464");
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

  @Test
  public void TestEight(){
    Region region = AmapServerUtil.getRegion("河南");
    List<String> stringList = AmapServerUtil.getPolylineList(region);
    int x = 0;
    int y = 15;
    AtomicInteger z = new AtomicInteger();
    while (true) {
      System.out.println(x + "...." + y);
      List<String> strings = stringList.subList(x, y);
      List<Address> addressList = AmapServerUtil.getAddressList(strings);
      addressList.forEach(address -> {
        z.getAndIncrement();
        System.out.println("这是第" + z + "个" + address);
      });
      x += 15;
      y += 15;
    }
//    List<String> stringList1 = stringList.subList(40, 60);
//    stringList1.forEach(System.out::println);
//    System.out.println(stringList1.size());
//    111.021583,33.472713
  }
}
