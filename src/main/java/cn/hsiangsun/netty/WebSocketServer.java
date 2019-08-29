package cn.hsiangsun.netty;

import cn.hsiangsun.websocket.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer {

    private static class SingleWebSocketServer{
        static final WebSocketServer instance = new WebSocketServer();
    }

    public static WebSocketServer getInstance(){
        return SingleWebSocketServer.instance;
    }

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap bootstrap;
    private ChannelFuture channel;

    public WebSocketServer(){
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());
    }

    public void start(){
        this.channel = bootstrap.bind(7777);
        System.err.println("Netty server has started.............");
    }

}
