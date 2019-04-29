package name.felixbecker.aacl.shadingdemo.notsofancylibrary.client;

import name.felixbecker.aacl.shadingdemo.fancylib.bad.EchoServer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String... args) throws Exception {
        System.setProperty("io.netty.native.deleteLibAfterLoading", "false");
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.DEBUG);

        var echoServer = new EchoServer();

        new Thread(echoServer).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        var nextEcho = echoServer.getEchos().take();
                        System.out.print(nextEcho.getEchoDate() + ": " + nextEcho.getEchoValue());
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }).start();

    }

}
