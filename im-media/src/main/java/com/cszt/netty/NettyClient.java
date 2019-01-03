package com.cszt.netty;

/**
 * @author lilin
 * @create 2018/12/24 9:55
 * description:
 */

import com.cszt.javacv.ReceiveStream;
import com.cszt.javacv.SendStream;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyClient {
    private final String host = "127.0.0.1";
    private final int port = 8888;

    public void start() throws IOException, InterruptedException {
        Bootstrap b = new Bootstrap();
        EventLoopGroup g = new NioEventLoopGroup();
        //创建对象，后面调用closeChannel()主动关闭连接
        NettyClientHandler cHandler = new NettyClientHandler();

        b.group(g)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // TODO Auto-generated method stub
                        //添加编码器、译码器
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new StringEncoder());
                        sc.pipeline().addLast(cHandler);
                    }
                });
        final ChannelFuture f = b.connect(host, port).sync();
        f.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture arg0) throws Exception {
                // TODO Auto-generated method stub
                if (f.isSuccess()) {
                    System.out.println("连接服务器成功");

                } else {
                    System.out.println("连接服务器失败");
                    f.cause().printStackTrace();
                }
            }
        });
        Channel channel = f.channel();
        /*
         * 获取控制台输入
         * 当输入了“再见”或“bye”时，停止输入
         * 主动关闭连接
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        while (!(in.equals("再见") || in.equals("bye"))) {
            if ("video".equals(in)){
                String sendAddr = "rtmp://192.168.138.129/hls/send";
                String receiveAddr = "rtmp://192.168.138.129/hls/receive";
                new Thread(new SendStream(sendAddr)).start();
                new Thread(new ReceiveStream(receiveAddr)).start();
                channel.writeAndFlush(sendAddr+"#"+receiveAddr+"@video");
            }
            channel.writeAndFlush(in);
            in = br.readLine();
        }
        channel.writeAndFlush(in);
        cHandler.closeChannel(true);
        g.shutdownGracefully().sync();

    }

    public NettyClient() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] agrs) throws Exception {
        new NettyClient().start();
    }

}