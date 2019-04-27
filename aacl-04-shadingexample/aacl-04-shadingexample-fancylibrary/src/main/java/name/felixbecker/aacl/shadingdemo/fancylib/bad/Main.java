package name.felixbecker.aacl.shadingdemo.fancylib.bad;

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
                        System.out.print(echoServer.getEchos().take());
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }).start();

    }

}
