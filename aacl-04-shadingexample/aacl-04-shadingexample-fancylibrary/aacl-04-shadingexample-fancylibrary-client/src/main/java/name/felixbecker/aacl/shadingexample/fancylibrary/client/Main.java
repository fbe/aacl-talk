package name.felixbecker.aacl.shadingexample.fancylibrary.client;

import name.felixbecker.aacl.shadingexample.fancylibrary.loader.FancyLibLoader;

public class Main {

    public static void main(String... args){
        final var es = new FancyLibLoader().createEchoServer();
        es.getEchos();
    }

}
