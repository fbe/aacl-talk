package name.felixbecker.aacl.shadingdemo.fancylib;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String... args) throws Exception {
        System.setProperty("io.netty.native.deleteLibAfterLoading", "false");
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.DEBUG);
        new EchoServer();

    }

}
