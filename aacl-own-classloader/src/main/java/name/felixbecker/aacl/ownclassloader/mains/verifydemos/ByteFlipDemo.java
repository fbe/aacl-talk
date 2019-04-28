package name.felixbecker.aacl.ownclassloader.mains.verifydemos;

import name.felixbecker.aacl.ownclassloader.classloader.OwnDelegatingClassLoader;
import name.felixbecker.aacl.ownclassloader.classgenerator.BrokenClassGenerator;

public class ByteFlipDemo {

    public static void main(String... args) throws Exception {
        final OwnDelegatingClassLoader classLoader = new OwnDelegatingClassLoader();
        classLoader.loadBrokenClass("name.felixbecker.aacl", BrokenClassGenerator.DamageType.BYTE_FLIP);
    }
}
