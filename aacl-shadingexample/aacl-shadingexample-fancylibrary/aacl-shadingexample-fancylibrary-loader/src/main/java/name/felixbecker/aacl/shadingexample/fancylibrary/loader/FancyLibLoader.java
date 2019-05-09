package name.felixbecker.aacl.shadingexample.fancylibrary.loader;

import name.felixbecker.aacl.shadingexample.fancylibrary.api.EchoServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class FancyLibLoader {

    private final static String prefix = "fancylib-impl/";

    public EchoServer createEchoServer(){

        var cl = new ClassLoader(ClassLoader.getSystemClassLoader()){

            @Override
            public URL getResource(String name) {
                var potentialResourceName = prefix + name;
                var resource = getParent().getResource(potentialResourceName);
                if(resource != null){
                    return resource;
                }

                return super.getResource(name);
            }

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                var alreadyLoaded = findLoadedClass(name);
                if(alreadyLoaded != null){
                    return alreadyLoaded;
                }

                var potentialClassFile = prefix + name.replaceAll("\\.", "/") + ".class";
                var potentialClassStream = getResourceAsStream(potentialClassFile);

                if(potentialClassStream != null){
                    try {
                        var bytes = potentialClassStream.readAllBytes();
                        return defineClass(name, bytes, 0, bytes.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return super.loadClass(name);
            }
        };


        try {
            return (EchoServer) cl.loadClass("name.felixbecker.aacl.shadingexample.fancylibrary.impl.EchoServerImpl")
                    .getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
