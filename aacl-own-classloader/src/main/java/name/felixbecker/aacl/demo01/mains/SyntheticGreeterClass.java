package name.felixbecker.aacl.demo01.mains;

import name.felixbecker.aacl.demo01.classloader.SyntheticClassLoader;

public class SyntheticGreeterClass {
    public static void main(String... args) throws Exception {
        new SyntheticClassLoader().createSyntheticGreeterClass().getConstructor().newInstance();
    }
}
