package com.hjy.netty.serialize;

import com.hjy.netty.serialize.pojo.SubscribeReq;
import com.hjy.netty.serialize.pojo.SubscribeResp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq subscribeReq = (SubscribeReq)msg;
        if("hjy".equalsIgnoreCase(subscribeReq.getUserName())){
            System.out.println("server accepted subresquest:" + "\n" + subscribeReq.toString());
            ctx.writeAndFlush(resp(subscribeReq.getSubReqID()));
        }
    }

    SubscribeResp resp(int subReqID){
        SubscribeResp subscribeResp = new SubscribeResp();
        subscribeResp.setSubReqID(subReqID);
        subscribeResp.setRespCode(0);
        subscribeResp.setDesc("成功接收订单，将进行后续处理");
        return subscribeResp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
