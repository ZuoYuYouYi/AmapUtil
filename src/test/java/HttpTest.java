import java.util.List;
import org.junit.Test;
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
    digui("河南");
  }

  public void digui(String s1) {
    List<String> stringList = AmapServerUtil.getNextLevelName(s1);

    for (String s : stringList) {
        if (AmapServerUtil.isNextLevel(s)){
          ss.append(s);
          digui(s);
        }
        System.out.println(ss);
        ss = null;
        break;
    }
  }

}
