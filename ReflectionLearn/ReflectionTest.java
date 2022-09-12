package ReflectionLearn;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ReflectionTest {
    @Test
    public void reflectClass() throws Exception {
        // 第一种获取Class对象的方式
        ReflectedObject ro = new ReflectedObject();
        Class roClass1 = ro.getClass();
        System.out.println(roClass1.getName());

        // 第二种获取Class对象的方式
        Class roClass2 = ReflectedObject.class;
        System.out.println("Two class object are same: " + (roClass1 == roClass2));

        // 第三种获取Class对象的方式
        try {
            Class roClass3 = Class.forName("ReflectionLearn.ReflectedObject");
            System.out.println("Two class object are same: " + (roClass3 == roClass2));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        roClass2.isInstance(ro);
    }

    @Test
    public void ifInstanceTest() throws Exception {
        ReflectedObject ro = new ReflectedObject();
        Class roClass = ro.getClass();
        System.out.println(roClass.isInstance(ro));
        System.out.println(ro instanceof ReflectedObject);
    }

    @Test
    public void reflectObject() throws Exception {
        Class roClass = Class.forName("ReflectionLearn.ReflectedObject");
        Object reflectedObject1 = roClass.newInstance();

        // 获取指定的Class对象的一种构造器的对象
        Constructor constructor = roClass.getConstructor(null);
        Object reflectedObject2 = constructor.newInstance();
    }

    @Test
    public void reflectConstructor() throws Exception {
        Class roClass = Class.forName("ReflectionLearn.ReflectedObject");

        // getConstructors()
        System.out.println("getConstructors():");
        Constructor[] constructors = roClass.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }
        System.out.println();
        // getDeclaredConstructors()
        System.out.println("getDeclaredConstructors():");
        Constructor[] declaredConstructors = roClass.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println(c);
        }

        // 获取公有的无参构造器
        System.out.println("reflect public non-parameter constructor");
        Constructor con = roClass.getConstructor(null);
        System.out.println("con = " + con);

        // 调用构造器
        Object obj = con.newInstance();

        // 获取私有构造器并调用
        System.out.println("reflect and call private constructor");
        con = roClass.getDeclaredConstructor(int.class);
        System.out.println(con);
        // 暴力反射，忽略访问修饰符
        con.setAccessible(true);
        obj = con.newInstance(2);
    }

    @Test
    public void reflectedFields() throws Exception {
        Class roClass = Class.forName("ReflectionLearn.ReflectedObject");

        // getFields()
        System.out.println("getFields():");
        Field[] fields = roClass.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        // getDeclaredFields()
        System.out.println("\ngetDeclaredFields():");
        fields = roClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        // 获取public成员变量
        System.out.println("\npublic field");
        Field f = roClass.getField("str");

        // 设置public成员变量的值
        System.out.println("\nset public field's value");
        Object obj = roClass.getConstructor().newInstance();
        f.set(obj, "A new string");
        System.out.println("public fields is set to :" + ((ReflectedObject) obj).str);

        // 获取private成员变量
        System.out.println("\nprivate field");
        f = roClass.getDeclaredField("d");

        // 设置private成员变量的值
        f.setAccessible(true); // 暴力反射，接触私有限定
        f.set(obj, new Date());
        System.out.println("private field is set to :" + (ReflectedObject) obj);
    }

    @Test
    public void reflectedMethods() throws Exception {
        Class roClass = Class.forName("ReflectionLearn.ReflectedObject");

        // getMethods()
        System.out.println("getMethods()");
        Method[] methods = roClass.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        // getDeclaredMethods()
        System.out.println("\ngetDeclaredMethods()");
        methods = roClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        // getMethod()
        System.out.println("\ngetMethod()");
        Method m = roClass.getMethod("publicMethod", String.class);
        Object obj = roClass.getConstructor().newInstance();
        m.invoke(obj, "string value");


        // getDeclaredMethod()
        System.out.println("\ngetDeclaredMethod");
        m = roClass.getDeclaredMethod("privateMethod", int.class);
        m.setAccessible(true); // 暴力反射，接触私有限定
        m.invoke(obj, 2);

        // get and invoke main method
        /* main方法为static静态方法，因此在调用时可以使用null作为对象
        * invoke的第二个参数的要求为一个String数组，但是这里我们提供的是一个 Object[]
        * 这是因为在jdk1.4时要求为数组，就算在jdk1.5修改为可变参数之后，也要考虑向下兼容的问题
        * 单同时直接使用 new String[]{"a", "b", "c"}会被jdk1.5之后的版本拆成三个对象，因此需要需要强制转换成Object[]
        */
        System.out.println("\nget and invoke main method");
        m = roClass.getMethod("main", String[].class);
        m.invoke(null, (Object) new String[]{"a", "b", "c"});

    }
    public static String getConfig(String key) throws IOException {
        Properties properties = new Properties();
        FileReader in = new FileReader("/Users/runfeng/IdeaProjects/JavaSec/src/ReflectionLearn/config.txt");
        properties.load(in);
        in.close();
        return properties.getProperty(key);
    }
    @Test
    public void loadConfig() throws Exception {
        Class roClass = Class.forName(getConfig("className"));
        Method m = roClass.getMethod(getConfig("methodName"), String.class);
        m.invoke(roClass.getConstructor().newInstance(), "String value");
    }

    @Test
    public void bypassGeneric() throws Exception {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");

        Class listClass = strList.getClass();
        Method m = listClass.getMethod("add", Object.class);
        m.invoke(strList, 100);

        for(Object obj : strList){
            System.out.println(obj);
        }

    }

    @Test
    public void reflectSingleton() throws Exception {
        Class rsClass = Class.forName("ReflectionLearn.ReflectedSingleton");
        Method[] methods = rsClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        Constructor con = rsClass.getDeclaredConstructor();
        con.setAccessible(true);

        Method m = rsClass.getDeclaredMethod("privateMethod", null);
        m.setAccessible(true);
        m.invoke(con.newInstance(null));
    }

    public static void main(String[] args) throws Exception{
        ReflectedObject ro = new ReflectedObject();

    }
}
