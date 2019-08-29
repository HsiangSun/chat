package cn.hsiangsun.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MainServer {
    public static void main(String[] args) {

        //main thread :just accept request
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //work thread :
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup) //thread group
                    .channel(NioServerSocketChannel.class) //nio two-way channel
                    .childHandler(new ServerInitializer()); //child handler

            //start server and port is 7777 and method is syc
            ChannelFuture channel = bootstrap.bind(7777).sync();
            //listen close channel and set syc
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
