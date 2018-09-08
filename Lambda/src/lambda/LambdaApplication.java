package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LambdaApplication
 * Author:   jj
 * Date:     2018/6/27 10:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/6/27
 * @since 1.0.0
 */
public class LambdaApplication {
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(111);
        list.add(222);
        list.add(333);
        list.add(2);
        list.add(8);
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
        Comparator<Integer> comparator = (o1,o2) -> {return o2-o1;};
        Collections.sort(list,comparator);
        list.forEach(item -> System.out.println(item));
    }
}