/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Optional
 * Author:   jj
 * Date:     2018/7/1 20:31
 * Description: Optional类的使用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 〈一句话功能简述〉<br>
 * 〈Optional类的使用〉
 *
 * @author jj
 * @create 2018/7/1
 * @since 1.0.0
 */
public class OptionalTest{
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("5555");
        Optional<List<String>> optional = Optional.ofNullable(list);
        optional.ifPresent(new ConsumerIml<>());
        optional.ifPresent(t ->t.forEach(item -> System.out.println(item)));//lambda表达式
    }
}