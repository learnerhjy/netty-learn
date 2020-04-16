package com.hjy.netty.other;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FixedLengthClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 如果没用Unpooled.copiedBuffer() 消息就发送不出去
        ctx.writeAndFlush(Unpooled.copiedBuffer("this is a message and its length is more than 20 bytes".getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
