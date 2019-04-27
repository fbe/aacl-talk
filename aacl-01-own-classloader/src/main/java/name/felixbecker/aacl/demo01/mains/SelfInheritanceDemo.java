package name.felixbecker.aacl.demo01.mains;

import name.felixbecker.aacl.demo01.classgenerator.BrokenClassGenerator;
import name.felixbecker.aacl.demo01.classloader.OwnDelegatingClassLoader;

public class SelfInheritanceDemo {

    public static void main(String... args) throws Exception {
        final OwnDelegatingClassLoader classLoader = new OwnDelegatingClassLoader();
        classLoader.loadBrokenClass("name.felixbecker.aacl", BrokenClassGenerator.DamageType.SELF_INHERITANCE);
    }
}
