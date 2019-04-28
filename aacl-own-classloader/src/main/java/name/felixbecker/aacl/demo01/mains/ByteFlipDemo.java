package name.felixbecker.aacl.demo01.mains;

import name.felixbecker.aacl.demo01.classloader.OwnDelegatingClassLoader;
import name.felixbecker.aacl.demo01.classgenerator.BrokenClassGenerator;

public class ByteFlipDemo {

    public static void main(String... args) throws Exception {
        final OwnDelegatingClassLoader classLoader = new OwnDelegatingClassLoader();
        classLoader.loadBrokenClass("name.felixbecker.aacl", BrokenClassGenerator.DamageType.BYTE_FLIP);
    }
}
