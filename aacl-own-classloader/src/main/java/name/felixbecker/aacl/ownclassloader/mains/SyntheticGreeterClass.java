package name.felixbecker.aacl.ownclassloader.mains;

import name.felixbecker.aacl.ownclassloader.classloader.SyntheticGreeterClassLoader;

public class SyntheticGreeterClass {
    public static void main(String... args) throws Exception {

        var cl = new SyntheticGreeterClassLoader();
        var clazz = cl.createSyntheticGreeterClass();
        clazz.getConstructor().newInstance();
    }
}
