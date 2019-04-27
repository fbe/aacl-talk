package name.felixbecker.aacl.shadingexample.fancylibrary.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import name.felixbecker.aacl.shadingexample.fancylibrary.api.Echo;
import name.felixbecker.aacl.shadingexample.fancylibrary.api.EchoServer;

import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;

public class EchoServerImpl implements EchoServer {
    private final ArrayBlockingQueue<Echo> echos = new ArrayBlockingQueue<>(100);

    public ArrayBlockingQueue<Echo> getEchos() {
        return echos;
    }

    public void run() {

        try {
            final var bossGroup = new EpollEventLoopGroup(1);
            final var workerGroup = new EpollEventLoopGroup();
            final EchoServerHandler serverHandler = new EchoServerHandler(echos);
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
                                p.addLast(new StringDecoder(Charset.defaultCharset()))
                                        .addLast(serverHandler);
                            }
                        });

                System.err.println("Binding to port 4711");
                ChannelFuture f = b.bind(4711).sync();

                f.channel().closeFuture().sync();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
