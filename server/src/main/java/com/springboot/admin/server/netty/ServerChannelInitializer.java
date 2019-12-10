package com.springboot.admin.server.netty;

import io.netty.channel.*;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.channels.SocketChannel;

/**
 * @author Gjing
 *
 * netty服务初始化器
 **/
public class ServerChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel socketChannel) throws Exception {
        //添加编解码
        socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast(new NettyServerHandler());//事件处理
        System.out.println("success to initHandler!");
    }
}