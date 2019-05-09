package name.felixbecker.aacl.ownclassloader.mains;

import name.felixbecker.aacl.ownclassloader.classgenerator.SyntheticClassGenerator;
import name.felixbecker.aacl.ownclassloader.classloader.LaterDelegatingURLClassLoader;
import name.felixbecker.aacl.ownclassloader.classloader.SyntheticDynamicNameClassLoader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class BasicsMain
{
    final static String testClassFQCN = "name.felixbecker.aacl.ownclassloader.model.TestClass";
    final static String driverClassFQCN = "java.sql.Driver";

    public static void main( String[] args ) throws Exception {
        var basicsMainUrl = BasicsMain.class.getProtectionDomain().getCodeSource().getLocation();

        var urlClassLoader = new URLClassLoader(new URL[]{basicsMainUrl}, ClassLoader.getSystemClassLoader());

        var stringClass = urlClassLoader.loadClass("java.lang.String");
        System.out.println(stringClass.getClassLoader());

        var testClass = urlClassLoader.loadClass(testClassFQCN);
        System.out.println(testClass.getClassLoader());

        var driverClass = urlClassLoader.loadClass(driverClassFQCN);
        System.out.println(driverClass.getClassLoader());

        Class.forName(testClassFQCN, false, ClassLoader.getSystemClassLoader());

        var class1Bytes = SyntheticClassGenerator.generateClass("jax.Fun");
        var class2Bytes = SyntheticClassGenerator.generateClass("jax.Fun2");

        var loader = new SyntheticDynamicNameClassLoader();
        var cl1 = loader.generateClass("jax.Fun", class1Bytes);
        var cl2 = loader.generateClass("jax.Fun2", class2Bytes);

        System.out.println(cl1);
        System.out.println(cl2);

       // loader.generateClass("java.lang.String", SyntheticClassGenerator.generateClass("java.lang.String"));


        var laterCL = new LaterDelegatingURLClassLoader(new URL[]{basicsMainUrl}, ClassLoader.getSystemClassLoader());
        var basicsMainClazz = laterCL.loadClass("name.felixbecker.aacl.ownclassloader.mains.BasicsMain");
        var basicsMainClazz2 = laterCL.loadClass("name.felixbecker.aacl.ownclassloader.mains.BasicsMain");

        System.out.println(basicsMainClazz.getClassLoader());
        System.out.println(basicsMainClazz2.getClassLoader());

        var obj = (BasicsMain) basicsMainClazz.getConstructor().newInstance();



    }
    
}
