import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.zuoyu.config.AmapConfig;
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

  /**
   * 测试下一级名字
   */
  @Test
  public void TestOne(){
    List<String> stringList = AmapServerUtil.getNextLevelName("郑州");
    stringList.forEach(s -> {
      System.out.println(s);
    });
  }

  /**
   * 测试指定区域包含的经纬度集合
   */
  @Test
  public void TestTwo(){
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
  public void TestThree(){
//    Region region = AmapServerUtil.getRegion("北京");   /*默认*/
//    Region region = AmapServerUtil.getRegion("北京", 1);  /*一级*/
//    Region region = AmapServerUtil.getRegion("北京", 2);  /*二级*/
    Region region = AmapServerUtil.getRegion("北京", 3);  /*三级*/
    System.out.println(region.getDistricts());


  }

}
