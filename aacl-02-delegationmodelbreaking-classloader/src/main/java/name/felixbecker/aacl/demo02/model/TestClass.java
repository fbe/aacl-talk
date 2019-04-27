package name.felixbecker.aacl.demo02.model;

public class TestClass {

    private volatile static TestClass INSTANCE;

    static {
        System.out.println("TestClass initialized - classloader is " + TestClass.class.getClassLoader());
        INSTANCE = new TestClass();
    }

    public void test(){
        System.out.println("Hello, test!");
    }
}
