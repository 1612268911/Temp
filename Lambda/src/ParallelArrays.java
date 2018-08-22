/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ParallelArrays
 * Author:   jj
 * Date:     2018/7/2 20:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.IntUnaryOperator;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/2
 * @since 1.0.0
 */
public class ParallelArrays {
    public static void main(String[] args){
        Integer[] arrs = new Integer[20];
        Arrays.parallelSetAll(arrs, index -> (int)(Math.random()*10));
        for (int arr : arrs) {
            System.out.println(arr);
        }
//        Arrays.parallelSort(arrs);
//        Arrays.parallelSort(arrs,0,3);
//        Comparator<Integer> comparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        };
        Arrays.parallelSort(arrs,(o1,o2) -> o1-o2);

        System.out.println("************************");
        Arrays.stream(arrs).filter(t -> t >= 5).forEach(item ->System.out.println(item));
    }
}