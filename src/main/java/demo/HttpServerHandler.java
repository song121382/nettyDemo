package demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

//自定义业务handler
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpObject;
            System.out.println("请求方法：" + httpRequest.method().name());
            System.out.println("请求来自：" + channelHandlerContext.channel().remoteAddress());

            ByteBuf content = Unpooled.copiedBuffer("Hello Word!", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes()+"");

            channelHandlerContext.writeAndFlush(response);
            channelHandlerContext.close();
        }
    }
}
