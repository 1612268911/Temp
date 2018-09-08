package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MapTest
 * Author:   jj
 * Date:     2018/7/17 17:01
 * Description: map新特性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 〈一句话功能简述〉<br>
 * 〈map新特性〉
 *
 * @author jj
 * @create 2018/7/17
 * @since 1.0.0
 */
public class MapTest {
    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        for(int i = 0;i<10;i++){
            map.put(i+"","jj");
        }
        map.forEach((k,v)->System.out.println(k+"-"+v));

        String n = map.computeIfAbsent("1", a -> a+"oooo");
        System.out.println(n);

        boolean f1 = map.containsKey("1");
        boolean f2 = map.containsValue("jj");
        System.out.println(f1+"  "+f2);

        map.merge("1","jk",(old,newStr) -> old+newStr);
        System.out.println(map.get("1"));

        String defaultStr = map.getOrDefault("10","not found");
        System.out.println(defaultStr);

        map.remove("1","jjjk");
        System.out.println(map.get("1"));
    }
}