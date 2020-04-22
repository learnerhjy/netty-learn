package com.hjy.netty.protobuf;

import com.hjy.netty.protobuf.pojo.SubscribeReqProto;
import com.hjy.netty.protobuf.pojo.SubscribeRespProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
        if(req.getUserName().equalsIgnoreCase("hjy")){
            System.out.println("server received:" + req.toString());
            ctx.writeAndFlush(buildResp(req));
        }


    }

    private SubscribeRespProto.SubscribeResp buildResp(SubscribeReqProto.SubscribeReq req){
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(req.getSubReqID());
        builder.setRespCode(0);
        builder.setDesc("this is a message from server");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
