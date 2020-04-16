package com.hjy.netty.time;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class ServerChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 如果先加入StringDecoder，LineBasedFrameDecoder就发挥不了作用了
        // LineBasedFrameDecoder对ByteBuf中的字节进行处理，以换行符为结束标志
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // StringDecoder将对象解码成字符串
        socketChannel.pipeline().addLast(new StringDecoder());
        socketChannel.pipeline().addLast(new TimeServerHandler());
    }
}
