package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PatternTest
 * Author:   jj
 * Date:     2018/7/5 20:44
 * Description: 正则
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈正则〉
 *
 * @author jj
 * @create 2018/7/5
 * @since 1.0.0
 */
public class PatternTest {
    /**
     *  + -> 一个到无穷多个
     *  * -> 零个到无穷多个
     *  ？->  零个或一个
     * @param args
     */
    public static void main(String[] args){

        String regx = "\\d+(\\.\\d{1,4})?";
        String str = "0.76";
        boolean f = str.matches(regx);
        System.out.println(f);
        System.out.println("**********************");

        str = "10.2368686986859686";
        Pattern p = Pattern.compile("[\\d]*[\\.][\\d]{2}"); // 小数保留两位小数
        Matcher m = p.matcher(str);

        // 查找相应的字符串
        while (m.find()) {
            String tmp = m.group();//匹配的字符串
            if (!"".equals(tmp)) {
                System.out.println(tmp);
            }
        }

        // 判断是否匹配
        System.out.println(m.matches());

        str = "jjkjkii5j6h7uh";
        p = Pattern.compile("\\d");
        m = p.matcher(str);
        //用jk替换所有的数字
        String a = m.replaceAll("jk");

        System.out.println("a="+a);
        // 查找相应的字符串
        while (m.find()) {
            String tmp = m.group();
            if (!"".equals(tmp)) {
                System.out.println(tmp);
            }
        }
    }
}