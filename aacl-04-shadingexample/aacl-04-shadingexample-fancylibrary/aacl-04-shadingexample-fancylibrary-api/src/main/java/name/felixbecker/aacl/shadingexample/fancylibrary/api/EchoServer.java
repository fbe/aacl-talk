package name.felixbecker.aacl.shadingexample.fancylibrary.api;

import java.util.concurrent.ArrayBlockingQueue;

public interface EchoServer {
    ArrayBlockingQueue<Echo> getEchos();
}
