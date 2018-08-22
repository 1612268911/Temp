/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ConsumerIml
 * Author:   jj
 * Date:     2018/7/1 20:47
 * Description: Consumer实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.List;
import java.util.function.Consumer;

/**
 * 〈一句话功能简述〉<br>
 * 〈Consumer实现类〉
 *
 * @author jj
 * @create 2018/7/1
 * @since 1.0.0
 */
public class ConsumerIml<T> implements Consumer<T> {
    @Override
    public void accept(T t) {
        if(t instanceof List){
            List<String> list = (List<String>)t;
            list.forEach(item -> System.out.println(item));
        }
    }
}