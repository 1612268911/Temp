package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StreamTest
 * Author:   jj
 * Date:     2018/7/2 14:49
 * Description: stream测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈stream测试〉
 *
 * @author jj
 * @create 2018/7/2
 * @since 1.0.0
 */
public class StreamTest {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(10,60,30);
//        //并行不按顺序
//        Stream<Integer> stream = list.parallelStream();
        Stream<Integer> stream = list.stream();
        //条件过滤
//        stream = stream.filter(t -> t>100);
//        stream.forEach(item ->System.out.println(item));
        //分组   t -> t.toString().substring(0)作为key
//        Map<String,List<Integer>> map = stream.collect(Collectors.groupingBy(t -> t.toString().substring(0)));
//        //便利map
//        map.forEach((t,u)-> System.out.println("key="+t+"  value="+u));

        //将元素转成double类型
        //stream.mapToDouble(t->t).forEach(item->System.out.println(item));
//        //求和
//        int sum = stream.mapToInt(t -> t).sum();
//        System.out.println(sum);
          //将集合转成新的集合
          //stream.map(a->a+10).collect(Collectors.toList()).forEach(item -> System.out.println(item));
//
//        Collector c = Collectors.toList();
//        double a = stream.filter(t->t>=1000).mapToInt(t -> t+100).sum();
//        System.out.println(a);

//        //去重
//        stream.distinct().forEach(item -> System.out.println(item));

//        //t=10 j=60;t=t-j j=30;t=t-j-->Optional<Integer>
//        Optional<Integer> reduce = stream.reduce((t,j)->t-j);
//        reduce.ifPresent(t->System.out.println(t));

//        for(int i = 0;i<1000;i++){
//            Integer number =  list.parallelStream().findAny().get();
//            System.out.println(number);
//        }
    }
}