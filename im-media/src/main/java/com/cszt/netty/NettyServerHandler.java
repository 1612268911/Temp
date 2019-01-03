package com.cszt.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lilin
 * @create 2018/12/24 9:54
 * description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final int MAX_CONN = 2;//指定最大连接数
    private int connectNum = 0;//当前连接数
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当有客户端连接时，handlerAdded会执行,就把该客户端的通道记录下来，加入队列
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        if (channels.size() > MAX_CONN) {
            ctx.writeAndFlush("连接人数已满..");
            ctx.channel().close();
            return;
        }
        Channel inComing = ctx.channel();//获得客户端通道
        //通知其他客户端有新人进入
        for (Channel channel : channels) {
            if (channel != inComing)
                channel.writeAndFlush("[欢迎: " + inComing.remoteAddress() + "] 进入聊天室！\n");
        }
        channels.add(inComing);//加入队列
        System.out.println("channels.size-->" + channels.size());
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outComing = ctx.channel();//获得客户端通道
        //通知其他客户端有人离开
        for (Channel channel : channels) {
            if (channel != outComing)
                channel.writeAndFlush("[再见: ]" + outComing.remoteAddress() + " 离开聊天室！\n");
        }
    }

    //获取当前时间
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /*
     * 重写channelActive()方法
     * 更新当前连接数
     * 控制连接客户端的个数，超过则关闭该channel
     * 更新contexts数组
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    /*
     * 重写channelInactive()方法
     * 更新当前连接数
     * 更新contexts数组
     * 控制台输出相关信息
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    /*
     * 重写channelRead()函数
     * 读取数据
     * 转发消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String in = (String) msg;
        System.out.println(getTime() + " 客户端" + ctx.channel().remoteAddress() + ":" + in);
        //当只有一方在线时，发送通知
        if (channels.size() < 2) {
            ctx.writeAndFlush("对方不在线");
            return;
        }
        //获取另一个channelhandlercontxt的下表
        Channel channel = channels.stream().filter(t->t!=ctx.channel()).findFirst().get();
        //给另一个客户端转发信息
        channel.writeAndFlush(in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        if (!ctx.channel().isActive()) {
            System.out.println(ctx.channel().remoteAddress() + "客户端异常");
        }
        cause.printStackTrace();
        ctx.close();
    }

}