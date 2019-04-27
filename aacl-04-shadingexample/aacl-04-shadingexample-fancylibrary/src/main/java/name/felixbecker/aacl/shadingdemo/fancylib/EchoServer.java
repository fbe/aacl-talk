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

    public void run() {

        try {
            final EventLoopGroup bossGroup = new EpollEventLoopGroup(1);
            final EventLoopGroup workerGroup = new EpollEventLoopGroup();
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
                                                       //p.addLast(new LoggingHandler(LogLevel.INFO));
                                                       p.addLast(serverHandler);
                                                   }
               });

                ChannelFuture f = b.bind(4711).sync();

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
