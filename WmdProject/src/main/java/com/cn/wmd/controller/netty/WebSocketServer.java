package com.cn.wmd.controller.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-24 15:17
 * @since 1.0
 */
public class WebSocketServer {

    public static void main(String[] args) {
        // 初始化主线程池（boss线程池）
        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        // 初始化主线程池（worker线程池）
        NioEventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            // 创建服务器启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(mainGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WsServerInitializer());

            // 绑定端口号启动服务器，并等待服务器启动
            // channelFuture 是Netty的回调消息
            ChannelFuture future = serverBootstrap.bind(9090).sync();
            // 等待服务器socket关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 优雅关闭boss线程持和worker线程池
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}