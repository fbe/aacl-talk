package name.felixbecker.aacl.ownclassloader.mains;

import name.felixbecker.aacl.ownclassloader.classgenerator.SyntheticClassGenerator;
import name.felixbecker.aacl.ownclassloader.classloader.NonDelModelClassLoader;
import name.felixbecker.aacl.ownclassloader.classloader.SyntheticClassLoader;
import name.felixbecker.aacl.ownclassloader.mains.verifydemos.TestEntity;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class BasicsMain {
    public static void main(String[] args) throws Exception {

        var testEntity = new TestEntity();
        System.out.println(testEntity.getClass().getClassLoader());

        var testEntityLocation = testEntity.getClass().getProtectionDomain().getCodeSource().getLocation();


        var nonDelCl = new NonDelModelClassLoader(new URL[]{testEntityLocation}, BasicsMain.class.getClassLoader());

        var testEntity2 = nonDelCl.loadClass(testEntity.getClass().getCanonicalName());
        testEntity2.getConstructor().newInstance();
        System.out.println(testEntity2.getClassLoader());

    }

}
