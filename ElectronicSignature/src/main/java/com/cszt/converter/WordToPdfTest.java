/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WordToPdf
 * Author:   jj
 * Date:     2018/8/9 21:41
 * Description: word转pdf转换器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.converter;

import java.io.*;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;


/**
 * 〈一句话功能简述〉<br>
 * 〈word转pdf转换器〉
 *
 * 注意dll文件配置
 *
 * @author jj
 * @create 2018/8/9
 * @since 1.0.0
 */
public class WordToPdfTest {
    /**
     * @Author lilin
     * @Description word转pdf转换器 doc或docx都支持
     * @Date: 2018/8/30 17:48
     * @param:
     * @return:
     */
    public static void conver(String source,String desc) {
        ActiveXComponent app = null;
        System.out.println("开始转换...");
        // 开始时间
        long start = System.currentTimeMillis();
        try {
            // 打开word
            app = new ActiveXComponent("Word.Application");
            // 设置word不可见,很多博客下面这里都写了这一句话，其实是没有必要的，因为默认就是不可见的，如果设置可见就是会打开一个word文档，对于转化为pdf明显是没有必要的
            //app.setProperty("Visible", false);
            // 获得word中所有打开的文档
            Dispatch documents = app.getProperty("Documents").toDispatch();
            System.out.println("打开文件: " + source);
            // 打开文档
            Dispatch document = Dispatch.call(documents, "Open", source, false, true).toDispatch();
            // 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
            File target = new File(desc);
            if (target.exists()) {
                target.delete();
            }
            System.out.println("另存为: " + desc);
            // 另存为，将文档报错为pdf，其中word保存为pdf的格式宏的值是17
            Dispatch.call(document, "SaveAs", desc, 17);
            // 关闭文档
            Dispatch.call(document, "Close", false);
            // 结束时间
            long end = System.currentTimeMillis();
            System.out.println("转换成功，用时：" + (end - start) + "ms");
        }catch(Exception e) {
            System.out.println("转换失败"+e.getMessage());
        }finally {
            // 关闭office
            app.invoke("Quit", 0);
        }
    }
}
