package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProxyTest
 * Author:   jj
 * Date:     2018/7/24 15:55
 * Description: 代理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 〈一句话功能简述〉<br>
 * 〈代理〉
 *
 * @author jj
 * @create 2018/7/24
 * @since 1.0.0
 */
public class ProxyTest implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy="+proxy.getClass());
        System.out.println("returnType="+method.getReturnType().getName());
        if(args!=null){
            for(Object obj:args){
                System.out.println("参数："+obj);
            }
        }
        Lambda lambda = new LambdaImpl();//应该把实现类传入
        return method.invoke(lambda,args);
    }
    public static void main(String[] args){
        Object obj = Proxy.newProxyInstance(Lambda.class.getClassLoader(),new Class[]{Lambda.class},new ProxyTest());
        String result = ((Lambda)obj).test();
        System.out.println(result);
    }
//    Lambda lambda;
//    public ProxyTest(Lambda lambda){
//        this.lambda = lambda;
//    }
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//        return method.invoke(lambda,args);
//    }
//    public static void main(String[] args){
//        Lambda lambda = new LambdaImpl();
//        InvocationHandler proxyTest = new ProxyTest(lambda);
//        Object object = Proxy.newProxyInstance(lambda.getClass().getClassLoader(),lambda.getClass().getInterfaces(),proxyTest);
//        String result = ((Lambda)object).test();
//        System.out.println(result);
//    }


}