package name.felixbecker.aacl.ownclassloader.mains;

import name.felixbecker.aacl.ownclassloader.classloader.OwnDelegatingClassLoader;

public class SimpleDelegatingClassLoader
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
        System.out.println( "Hello World!" );
        OwnDelegatingClassLoader classLoader = new OwnDelegatingClassLoader();
        OwnDelegatingClassLoader classLoader2 = new OwnDelegatingClassLoader();
        final Class<?> stringClass = classLoader.loadClass("java.lang.String");
        final Class<?> customClass = classLoader.loadClass("jax.Fun");
        final Class<?> customClass2 = classLoader2.loadClass("jax2.Fun");
        System.out.println(customClass);
        System.out.println(stringClass.getClassLoader());
        System.out.println(Class.forName("java.lang.String", true, classLoader).getClassLoader());
        System.out.println("Hello WOrld");
        // static initializer
    }
    
    // Demo own classloader which loads class from resource
    // Own Classloader which synthetically creates Class
}
