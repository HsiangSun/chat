package cn.hsiangsun.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

//TextWebSocketFrame:是webSocket处理文本的对象 frame 是消息的载体
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //管理所有客户端的channel【固定写法】
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext context, TextWebSocketFrame msg) throws Exception {

        //get msg from client
        String text = msg.text();
        System.out.println("the msg is :"+ text);

        /*for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[server accept ]:"+ LocalDateTime.now()+", 消息为: "+context));
        }*/
        clients.writeAndFlush(new TextWebSocketFrame("[server accept ]:"+ LocalDateTime.now()+", 消息为: "+text));


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //client.remove(ctx.channel());自动移除
        System.out.println("client的长ID:"+ctx.channel().id().asLongText());
    }


}
