package com.cszt.netty;

import com.cszt.javacv.ReceiveStream;
import com.cszt.javacv.SendStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lilin
 * @create 2018/12/24 9:56
 * description:
 */

public class NettyClientHandler extends SimpleChannelInboundHandler<String>{
    //保存当前ChannelHandlerContext，在后面的closeChannel()中使用
    private ChannelHandlerContext chc = null;

    //获取当前时间
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    //主动关闭连接
    public void closeChannel(boolean readyToClose) throws InterruptedException {
        if (readyToClose) {
            System.out.println("即将关闭连接");
            chc.channel().closeFuture();
            chc.channel().close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        //保存当前ChannelHandlerContext
        chc = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String in) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(getTime() + " " + in);
        if(in.endsWith("@video")){
            String rAddr = in.substring(0,in.indexOf("#"));
            String sAddr = in.substring(in.indexOf("#")+1,in.indexOf("@"));
            System.out.println("rAddr:"+rAddr);
            System.out.println("sAddr:"+sAddr);
            new Thread(new SendStream(sAddr)).start();
            new Thread(new ReceiveStream(rAddr)).start();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(getTime() + " 断开连接");
        ctx.channel().closeFuture();
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        cause.printStackTrace();
        System.out.println("有异常");
        ctx.channel().close();
    }
}