package com.hjy.netty.serialize;

import com.hjy.netty.serialize.pojo.SubscribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<10;i++){
            ctx.write(req(i));
        }
        ctx.flush();
    }

    SubscribeReq req(int subReqID){
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSubReqID(subReqID);
        subscribeReq.setAddress("some address");
        subscribeReq.setPhoneNumber("133XXXXXXXX");
        subscribeReq.setProductName("some book");
        subscribeReq.setUserName("hjy");
        return subscribeReq;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client received " + msg + "from server");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
