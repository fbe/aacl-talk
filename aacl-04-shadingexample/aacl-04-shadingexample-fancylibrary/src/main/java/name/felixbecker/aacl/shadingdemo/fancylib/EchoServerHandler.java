package name.felixbecker.aacl.shadingdemo.fancylib;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    final ConcurrentLinkedQueue<String> echos = new ConcurrentLinkedQueue<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        echos.add(msg.toString());
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    public List<String> getEchos(){
        return new ArrayList<>(echos);
    }
}