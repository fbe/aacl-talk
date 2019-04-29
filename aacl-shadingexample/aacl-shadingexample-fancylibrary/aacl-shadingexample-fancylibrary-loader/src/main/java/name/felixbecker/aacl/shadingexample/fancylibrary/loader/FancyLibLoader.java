package name.felixbecker.aacl.shadingexample.fancylibrary.loader;

import name.felixbecker.aacl.shadingexample.fancylibrary.api.EchoServer;

import java.net.URL;

public class FancyLibLoader {

    public EchoServer createEchoServer(){
        try {
            var loader = new FancyClassLoader(new URL[]{}, FancyLibLoader.class.getClassLoader());
            Class<?> implClazz = loader.loadClass("name.felixbecker.aacl.shadingexample.fancylibrary.impl.EchoServerImpl");
            return (EchoServer) implClazz.getConstructor().newInstance();
        } catch(Exception e){
            throw new RuntimeException(e);
        }


    };
}
