package name.felixbecker.aacl.ownclassloader.mains.verifydemos;

import name.felixbecker.aacl.ownclassloader.classgenerator.BrokenClassGenerator;
import name.felixbecker.aacl.ownclassloader.classloader.OwnDelegatingClassLoader;

public class SelfInheritanceDemo {

    public static void main(String... args) throws Exception {
        final OwnDelegatingClassLoader classLoader = new OwnDelegatingClassLoader();
        classLoader.loadBrokenClass("name.felixbecker.aacl", BrokenClassGenerator.DamageType.SELF_INHERITANCE);
    }
}
