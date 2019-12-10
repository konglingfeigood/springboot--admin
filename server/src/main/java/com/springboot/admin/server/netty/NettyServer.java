package com.springboot.admin.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author Gjing
 * <p>
 * 服务启动监听器
 **/
@Component
@Slf4j
public class NettyServer {

    public void start(InetSocketAddress socketAddress) {
        //1.定义server启动类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //2.new 一个主线程组(EventLoopGroup 就是一个线程池)
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //2.new 一个工作线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(200);


        //3.定义工作组
        bootstrap.group(bossGroup, workGroup);
        //4.设置通道channel
        bootstrap.channel(NioServerSocketChannel.class);

        //5.添加handler，管道中的处理器，通过ChannelInitializer来构造
        bootstrap.childHandler(new ServerChannelInitializer());

        //6.设置参数，TCP参数
        bootstrap.localAddress(socketAddress)
                //设置队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);//关闭延迟发送

        //绑定端口,开始接收进来的连接
        try {
            ChannelFuture future = bootstrap.bind(socketAddress).sync();
            log.info("服务器启动开始监听端口: {}", socketAddress.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭主线程组
            bossGroup.shutdownGracefully();
            //关闭工作线程组
            workGroup.shutdownGracefully();
        }
    }
}