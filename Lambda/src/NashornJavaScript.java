/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NashornJavaScript
 * Author:   jj
 * Date:     2018/7/2 21:00
 * Description: javascript引擎
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import sun.applet.Main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 〈一句话功能简述〉<br>
 * 〈javascript引擎〉
 *
 * @author jj
 * @create 2018/7/2
 * @since 1.0.0
 */
public class NashornJavaScript {
    public static void main(String[] args){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        try{
            System.out.println( "Result:  " + engine.eval( "function func(x) { return x-1; }; func(8);" ) );
        }catch(Exception e){
            System.out.println("脚本执行错误...");
        }
    }
}