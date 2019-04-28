package name.felixbecker.aacl.shadingexample.fancylibrary.loader;

import name.felixbecker.aacl.shadingexample.fancylibrary.api.EchoServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class FancyLibLoader {

    // get class loading lock
    public EchoServer createEchoServer(){
        try {
            EchoServer es = (EchoServer) new ClassLoader() {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {

                    synchronized(getClassLoadingLock(name)) {

                        // already loaded class
                        var alreadyLoaded = findLoadedClass(name);
                        if (alreadyLoaded != null) {
                            return alreadyLoaded;
                        }

                        // prefixed class
                        if (!name.startsWith("name.felixbecker.aacl.shadingexample.fancylibrary.api")) {
                            final var potentialResourceName = "fancylib-impl/" + name.replaceAll("\\.", "/") + ".class";
                            System.out.println("potential resource name is: " + potentialResourceName);
                            var potentialClassFileStream = FancyLibLoader.class.getClassLoader().getResourceAsStream(potentialResourceName);
                            System.out.println("Resource stream for " + name + " is " + potentialResourceName);
                            if (potentialClassFileStream != null) {
                                try {
                                    var bytes = potentialClassFileStream.readAllBytes();
                                    return defineClass(name, bytes, 0, bytes.length);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        System.out.println("Delegating " + name + " to FancyLibLoader classloader");
                        return FancyLibLoader.class.getClassLoader().loadClass(name);
                    }
                }

                @Override
                public URL getResource(String name) {
                    var potentialResourceName = "fancylib-impl/" + name;
                    var r = FancyLibLoader.class.getClassLoader().getResource(potentialResourceName);
                    System.out.println("Looked up " + potentialResourceName + ": " + r);
                    if(r != null) {
                        return r;
                    }
                    return FancyLibLoader.class.getClassLoader().getResource(name);
                }
            }.loadClass("name.felixbecker.aacl.shadingexample.fancylibrary.impl.EchoServerImpl")
                    .getConstructor().newInstance();

            return es;

        } catch(Exception e){
            throw new RuntimeException(e);
        }


    }
}
