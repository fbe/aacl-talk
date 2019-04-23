package name.felixbecker.talks.classloader.urlclassloading;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderDemo {

    public static void main(String... args) throws Exception {

        final URL urlToTarget =
                new URL("file:///home/becker/classloader-vortrag/classloader-vortrag-javademos/simple-app-manually-compiled/");

        final ClassLoader myURLClassLoader = new URLClassLoader(new URL[]{urlToTarget});

        Class<?> mySimpleAppClass = myURLClassLoader.loadClass("name.felixbecker.SimpleApp");
        Class<?> mySimpleAppClass2 = myURLClassLoader.loadClass("name.felixbecker.SimpleApp");
        System.out.println(mySimpleAppClass);

        Object simpleAppInstance = mySimpleAppClass.newInstance();

        Method greetMethod = mySimpleAppClass.getDeclaredMethod("greet", String.class);
        greetMethod.invoke(simpleAppInstance, "Felix");


    }

}
