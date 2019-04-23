package name.felixbecker.talks.classloader;

public class FirstSteps {

    public static void main(String... args) throws Exception {

        Class.forName("name.felixbecker.talks.classloader.GreeterClass");
        /*Class<?> greeterClass = FirstSteps.class.getClassLoader().
                loadClass("name.felixbecker.talks.classloader.GreeterClass");
        System.out.println(greeterClass);
        greeterClass.newInstance();*/

        ClassLoadingUtil.printClassLoaderHierachy(FirstSteps.class.getClassLoader());
    }

}
