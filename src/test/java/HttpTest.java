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

  /**
   * 测试下一级名字
   */
  @Test
  public void TestOne() {
    List<String> stringList = AmapServerUtil.getNextLevelName("金水区");
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

}
