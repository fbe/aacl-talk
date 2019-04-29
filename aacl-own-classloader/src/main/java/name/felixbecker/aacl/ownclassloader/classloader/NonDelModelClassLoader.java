package name.felixbecker.aacl.ownclassloader.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class NonDelModelClassLoader extends URLClassLoader {
    public NonDelModelClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        synchronized (getClassLoadingLock(name)){
            var clazz = findLoadedClass(name);

            if(clazz != null){
                return clazz;
            }

            try {
                clazz = findClass(name);
            } catch(ClassNotFoundException e)
            {}

            if(clazz == null) {
                return getParent().loadClass(name);
            } else {
                return clazz;
            }


        }

    }
}
