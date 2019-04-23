package name.felixbecker.talks.classloader.duplicatedloading;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicateLoadingDemo {


    public static final SimpleDefiningClassLoader cl1 = new SimpleDefiningClassLoader();
    public static final SimpleDefiningClassLoader cl2 = new SimpleDefiningClassLoader();


    public static void main(String... args) throws Exception {

        String simpleAppPath =
        "/home/becker/classloader-vortrag/classloader-vortrag-javademos/simple-app-manually-compiled/name/felixbecker/SimpleApp.class";

        byte[] classBytes = Files.readAllBytes(Paths.get(simpleAppPath));

        Class<?> simpleAppInstanceClass1 = cl1.defineManually(classBytes);
        Class<?> simpleAppInstanceClass2 = cl2.defineManually(classBytes);
        System.out.println(simpleAppInstanceClass1.getClassLoader() == simpleAppInstanceClass2.getClassLoader());

        Object app1Instance = simpleAppInstanceClass1.newInstance();
        Thread.sleep(1000);
        Object app2Instance = simpleAppInstanceClass2.newInstance();

        Method greet1 = simpleAppInstanceClass1.getDeclaredMethod("greet", String.class);
        Method greet2 = simpleAppInstanceClass2.getDeclaredMethod("greet", String.class);

        System.out.println(simpleAppInstanceClass1.getCanonicalName());

        System.out.println(simpleAppInstanceClass2.getCanonicalName());

        //greet2.invoke(app1Instance, "Felix");
        //greet1.invoke(app2Instance, "Felix");


    }




}
