package ReflectionLearn;

import java.util.Date;

public class ReflectedObject {
    // fields
    public String str;
    protected int i;
    char c;
    private Date d;

    @Override
    public String toString() {
        return "public: " + str + "\nprotected: " + i + "\ndefault: " + c + "\nprivate: " + d;
    }

    // Constructors
    //  无参构造器
    public ReflectedObject(){
        System.out.println("public 无参构造器");
    }

    // 单参构造器
    public ReflectedObject(String field){
        System.out.println("public 单参构造器：" + field);
    }

    // 多参构造器
    public ReflectedObject(String field, int num) {
        System.out.println("public 多参构造器：" + field + ";" + num);
    }
    // protected 构造器
    protected ReflectedObject(boolean b) {
        System.out.println("protected 单参构造器 b =" + b);
    }

    // private 构造器
    private ReflectedObject(int i) {
        System.out.println("private 单参构造器 i = " + i);
    }

    // default 构造器
    ReflectedObject(char c){
        System.out.println("default 单参构造器 c = " + c);
    }

    // methods

    // public 成员方法
    public void publicMethod(String str){
        System.out.println("public 成员方法，设置str = " + str);
    }

    // protected 成员方法
    protected void protectedMethod(){
        System.out.println("protected 成员方法");
    }


    // default 成员方法
    void defaultMethod(){
        System.out.println("default 成员方法");
    }

    // private 成员方法
    private String privateMethod(int i) {
        System.out.println("private 成员方法，设置 i = " + i);
        return "returnValue";
    }

    // main 方法

    public static void main(String[] args) {
        System.out.println("main方法");
    }
}
