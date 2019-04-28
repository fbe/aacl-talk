package name.felixbecker.aacl.shadingdemo.fancylib.bad;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    private final ArrayBlockingQueue<String> echos;

    public EchoServerHandler(ArrayBlockingQueue<String> echos) {
        this.echos = echos;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            echos.put(msg.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var buf = Unpooled.wrappedBuffer(((String) msg).getBytes());
        var f = ctx.writeAndFlush(buf);
        if (!f.isSuccess()) {
            f.cause().printStackTrace();
        }
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

    public List<String> getEchos() {
        return new ArrayList<>(echos);
    }
}