package name.felixbecker.aacl.moduleisolation.moduleb;

import name.felixbecker.aacl.moduleisolation.modulea.api.ModuleAService;

import java.net.URL;
import java.net.URLClassLoader;

public class BClass {

    public static void main(String... args) throws Exception {
        ModuleAService aclass = new ModuleAService();
        aclass.greet("Felix");

        final ClassLoader cl = new URLClassLoader(new URL[] { new URL("file:///home/becker/git/aacl-talk/aacl-03-moduleisolation/module-a/target/module-a-1.0.0-SNAPSHOT.jar")}){
            void log(String msg){
                System.err.println("[LOG] " + msg);
            }
            @Override
            protected Class<?> loadClass(String name, boolean resolve)
                    throws ClassNotFoundException
            {
                log("Called loadCLass for name " + name);
                synchronized (getClassLoadingLock(name)) {
                    Class<?> c = findLoadedClass(name);

                    if (c == null) {
                    log("Trying to load class " + name);

                        try {
                            c = findClass(name);
                        } catch(ClassNotFoundException e){
                            c = getParent().loadClass(name);
                        }

                    }

                    if(c == null) {
                        throw new ClassNotFoundException("Class " + name + " not found!");
                    }

                    return c;
                }
            }
        };

        Class<?> clz = cl.loadClass("name.felixbecker.aacl.moduleisolation.modulea.AClass");
        System.out.println(clz);
        System.out.println("Classloader of clz is: " + clz.getClassLoader());
        final Object o = clz.getConstructor().newInstance();
        o.getClass().getDeclaredMethod("greet", String.class).invoke(o, "Felix Hehe");
    }

    // Conclusion: classes + resources can be initialized using class loaders - even from other modules (module info doesn't work here!")
    // But Module instances PROTECT the frameworks class instances (e.g. if using reflection!);
}
