package demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


//编写Initializer添加handler
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //处理http服务的关键handler
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //自定义的handler
        pipeline.addLast("testHttpServerHandler", new HttpServerHandler());
    }
}
