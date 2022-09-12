package JVMLearn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    public static void main(String[] args) {
        //创建自定义classloader对象。
//        HomeClassLoader diskLoader = new HomeClassLoader("/Users/runfeng/");
        HomeClassLoader homeLoader = new HomeClassLoader("/Users/runfeng/");
        try {
            //加载class文件
            Class c = homeLoader.loadClass("Test");

            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException |
                         IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

