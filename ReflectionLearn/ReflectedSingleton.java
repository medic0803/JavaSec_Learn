package ReflectionLearn;

public class ReflectedSingleton {

    private static ReflectedSingleton rs;

    private ReflectedSingleton() {
        System.out.println("private constructor");
    }
    public static ReflectedSingleton getInstance() {
        if (rs == null) {
            rs = new ReflectedSingleton();
        }
        return rs;
    }

    private void privateMethod() {
        System.out.println("Function from Singleton");
    }
}
