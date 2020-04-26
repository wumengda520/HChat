package com.cn.wmd.controller.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-24 15:48
 * @since 1.0
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        // 获取从客户端传过来的消息
        String text = msg.text();
        System.out.println("接收到的消息" + text);

        // 将接受到的消息发送到所有客户端
        for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息:]" + LocalDateTime.now() + "消息为：" + text));

        }
    }

    // 当客户端连接服务器端后（打开连接）
    // 获取客户端的channel，并且放到channelGroup中进行管理
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
 // 当触发handlerRemoved,channelGroup 会自动移除对应客户端的channel

        clients.remove(ctx.channel());

        // asLongText-唯一的id
        // asShortText 短id（有可能重复）
        System.out.println("客户端连接断开 channel 对应的长id 为"+ctx.channel().id().asLongText());
        System.out.println("客户端连接断开 channel 对应的长id 为"+ctx.channel().id().asShortText());

    }
}