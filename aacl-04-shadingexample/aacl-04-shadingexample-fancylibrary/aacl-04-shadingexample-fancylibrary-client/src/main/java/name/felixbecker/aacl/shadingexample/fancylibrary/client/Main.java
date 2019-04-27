package name.felixbecker.aacl.shadingexample.fancylibrary.client;

import name.felixbecker.aacl.shadingexample.fancylibrary.loader.FancyLibLoader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String... args) {
        System.setProperty("io.netty.native.deleteLibAfterLoading", "false");
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.DEBUG);

        final var es = new FancyLibLoader().createEchoServer();
        es.getEchos();

        new Thread(es).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        var nextEcho = es.getEchos().take();
                        System.out.print(nextEcho.getEchoDate() + ": " + nextEcho.getEchoValue());
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }).start();

    }


}
