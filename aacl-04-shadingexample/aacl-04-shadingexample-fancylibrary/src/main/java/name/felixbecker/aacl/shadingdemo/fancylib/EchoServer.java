package name.felixbecker.aacl.shadingdemo.fancylib;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.BasicConfigurator;

public class EchoServer implements Runnable {
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public void run() {

        try {
            EventLoopGroup bossGroup = new EpollEventLoopGroup(1);
            EventLoopGroup workerGroup = new EpollEventLoopGroup();
            final EchoServerHandler serverHandler = new EchoServerHandler();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(EpollServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 100)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline p = ch.pipeline();
                                p.addLast(serverHandler);
                            }
                        });

                ChannelFuture f = b.bind(PORT).sync();

                f.channel().closeFuture().sync();
            } finally {
                // Shut down all event loops to terminate all threads.
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer().run();
    }

}
