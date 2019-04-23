package name.felixbecker.aacl.demo01;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class App
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
        System.out.println( "Hello World!" );
        OwnDelegatingClassLoader classLoader = new OwnDelegatingClassLoader();
        final Class<?> stringClass = classLoader.loadClass("java.lang.String");
        final Class<?> customClass = classLoader.loadClass("jax.Fun");


        System.out.println(customClass);

        System.out.println(stringClass.getClassLoader());
    }
    
    // Demo own classloader which loads class from resource
    // Own Classloader which synthetically creates Class
}
