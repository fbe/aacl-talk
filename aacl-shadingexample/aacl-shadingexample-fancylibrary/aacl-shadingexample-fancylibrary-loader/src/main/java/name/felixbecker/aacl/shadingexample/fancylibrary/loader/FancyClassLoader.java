package name.felixbecker.aacl.shadingexample.fancylibrary.loader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class FancyClassLoader extends URLClassLoader {
    public FancyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public URL getResource(String name) {
        var potentialResourceName = "fancylib-impl/"+name;
        var potentialResource = getParent().getResource(potentialResourceName);
        if(potentialResource != null){
            return potentialResource;
        }
        return super.getResource(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){

            var prefix = "fancylib-impl/";
            var clazz = findLoadedClass(name);
            if(clazz != null){
                return clazz;
            }

            var fileName = "fancylib-impl/"+ name.replaceAll("\\.", "/") + ".class";

            var resourceStream = getParent().getResourceAsStream(fileName);
            if(resourceStream != null){
                byte[] bytes = new byte[0];
                try {
                    bytes = resourceStream.readAllBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, bytes, 0, bytes.length);
            } else {
                return getParent().loadClass(name);
            }
        }


    }
}
