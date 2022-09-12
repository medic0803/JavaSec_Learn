package JVMLearn;

public class HelloWorld {
    public static int i = 3;
    public HelloWorld() {
    }
    static{
        i = 4;
    }

    public static void main(String[] args) {

//        String testString = "java.test.className";
//        String output = testString.substring(testString.lastIndexOf(".") + 1)+".class";
//        System.out.println(output);
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        for (String path : System.getProperty("sun.boot.class.path").split(":")) {
//            System.out.println(path);
//        }
//        for (String path : System.getProperty("java.ext.dirs").split(":")) {
//            if(path.startsWith("/Users/Runfeng") || path.startsWith("/Users/runfeng")){
//                System.out.println("~" + path.substring(14));
//            }
//            else{
//                System.out.println(path);
//            }
//        }
        for (String path : System.getProperty("java.class.path").split(":")) {
            if(path.startsWith("/Users/Runfeng") || path.startsWith("/Users/runfeng")){
                System.out.println("~" + path.substring(14));
            }
            else{
                System.out.println(path);
            }
        }
    }

}







