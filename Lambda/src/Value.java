/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Value
 * Author:   jj
 * Date:     2018/7/2 14:21
 * Description: 自动类型推断
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

/**
 * 〈一句话功能简述〉<br>
 * 〈自动类型推断〉
 *
 * @author jj
 * @create 2018/7/2
 * @since 1.0.0
 */
public class Value< T > {
    public static< T > T defaultValue() {
        return null;
    }

    public T getOrDefault( T value, T defaultValue ) {
        return ( value != null ) ? value : defaultValue;
    }
    public static void main(String[] args){
        Value<String> value = new Value<String>();
        System.out.println(value.getOrDefault("22",Value.defaultValue()));
    }
}