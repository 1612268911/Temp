/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FunctionTest
 * Author:   jj
 * Date:     2018/6/29 20:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/6/29
 * @since 1.0.0
 */
public class FunctionTest {
    static String name = "老大";
    public static void testMethod(FunctionTest functionTest){
        System.out.println("静态方法。。。");
    }
    public static void main(String[] args){
//        Functional func = s -> Integer.parseInt(s);
        Functional func = Integer::parseInt;//类方法的引用

        String userName = new String("user");
        Functional functional = userName::compareTo;
        int a = functional.parse("user3");
        System.out.println("user->compareTo:"+a);

        Functional fun = name::indexOf;//对象方法的引用

//        FactoryPerson factory = (name,age)-> new Person(name,age);
        FactoryPerson factory = Person::new;//构造方法的引用

        FunctionTest functionTest = new FunctionTest();
        List<FunctionTest> list = Arrays.asList(functionTest);
        list.forEach(t->FunctionTest.testMethod(t));
        list.forEach(FunctionTest::testMethod);//静态方法的引用
    }
    static class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    interface FactoryPerson{
        Person create(String name,int age);
    }
}