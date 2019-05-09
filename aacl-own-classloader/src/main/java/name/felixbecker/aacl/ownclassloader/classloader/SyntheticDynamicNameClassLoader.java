package name.felixbecker.aacl.ownclassloader.classloader;

import name.felixbecker.aacl.ownclassloader.classgenerator.SyntheticClassGenerator;

public class SyntheticDynamicNameClassLoader extends ClassLoader {

    public Class<?> generateClass(String name, byte[] classBytes){
        return defineClass(name, classBytes, 0, classBytes.length);
    }
}
