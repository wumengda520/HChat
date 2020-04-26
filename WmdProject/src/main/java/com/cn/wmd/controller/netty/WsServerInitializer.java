package com.cn.wmd.controller.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Descripation: 通道初始化器
 * @Author: wmd
 * @Date: 2020-04-24 15:23
 * @since 1.0
 */
public class WsServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 用于支持http协议
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 添加对http请求和响应的聚合器 只要使用Netty进行http编程都需要使用
        // 对httpmessage进行聚合，聚合成FullHttpRequest和FullHttpResponse
        // 在Netty编程中都会使用到handler
        pipeline.addLast(new HttpObjectAggregator(1024*60));

        // --------------支持 web socket
        // websocket 服务器处理的协议，用于指定客户端链接的路由 ： /ws
        // 本handler会帮你处理一些握手动作：handshaking(close,ping,pong)ping+pong =心跳
        // 对于socket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 添加自定义的handler
        pipeline.addLast(new ChatHandler());
    }
}