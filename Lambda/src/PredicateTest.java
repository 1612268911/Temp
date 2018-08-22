/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PredicateTest
 * Author:   jj
 * Date:     2018/7/1 20:58
 * Description: Predicate类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 〈一句话功能简述〉<br>
 * 〈Predicate类〉
 *
 * @author jj
 * @create 2018/7/1
 * @since 1.0.0
 */
public class PredicateTest<T> implements Predicate<T>{

    @Override
    public boolean test(T t) {
        if(t instanceof Integer){
            Integer str = (Integer)t;
            if(str > 5){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(11,2,3,5,6,3,1,32,6,7);
        PredicateTest<Integer> predicateTest = new PredicateTest<>();
        PredicateTest.test(list,predicateTest);
        System.out.println("********************************");
        PredicateTest.test(list,t -> t>10);//lambda表达式
    }
    public static void test(List<Integer> list,Predicate<Integer> predicate){
        list.forEach(item -> {
            if(predicate.test(item)){
                System.out.println(item);
            }
        });
    }
}