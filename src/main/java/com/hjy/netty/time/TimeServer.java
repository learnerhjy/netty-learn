package com.hjy.netty.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 时间服务器
 */
public class TimeServer{

    public void bind(int port) throws Exception{
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            // 启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ServerChildChannelHandler());

            // 绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            // 等待监听端口关闭
            future.channel().closeFuture().sync();
        }finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        int port = 8080;
        if(args!=null&&args.length>0){
            try{
                port = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                // default
            }
        }
        new TimeServer().bind(port);
    }


}
