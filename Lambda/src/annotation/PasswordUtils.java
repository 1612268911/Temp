package annotation;

/**
 * @author lilin
 * @create 2018/9/8 10:46
 * description:
 */


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Collector;

/**
 * 使用注解：
 *
 */
public class PasswordUtils {
    @UseCases
    private String name;
    @UseCases(value = "jjjjj")
    public void getValue(String name){
        System.out.println(name);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        PasswordUtils p = new PasswordUtils();
        Field[] fields = p.getClass().getDeclaredFields();
        System.out.println("fields.length="+fields.length);
        if(fields!=null && fields.length>0){
            for(Field field:fields){
                UseCases useCases = field.getAnnotation(UseCases.class);
                String name = useCases.value();
                System.out.println("name="+name);
                p.name = name;
            }
        }
        Method[] methods = p.getClass().getDeclaredMethods();
        System.out.println("methods.length="+methods.length);
        if(methods!=null && methods.length>0){
            for(Method method:methods){
                UseCases useCases = method.getAnnotation(UseCases.class);
                if(useCases==null){
                    continue;
                }
                method.invoke(p,useCases.value());
            }
        }
        p.getValue("11111");
        System.out.println("PasswordUtils.name="+p.name);
    }
}