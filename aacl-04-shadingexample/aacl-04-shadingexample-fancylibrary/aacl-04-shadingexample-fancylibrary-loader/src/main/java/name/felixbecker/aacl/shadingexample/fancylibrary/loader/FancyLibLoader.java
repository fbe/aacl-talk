package name.felixbecker.aacl.shadingexample.fancylibrary.loader;

import name.felixbecker.aacl.shadingexample.fancylibrary.api.EchoServer;

import java.lang.reflect.InvocationTargetException;

public class FancyLibLoader {

    public EchoServer createEchoServer(){
        try {
            EchoServer es = (EchoServer) new ClassLoader() {
            }.loadClass("name.felixbecker.aacl.shadingexample.fancylibrary.impl.EchoServerImpl").getConstructor().newInstance();

            return es;

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
