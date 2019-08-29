package cn.hsiangsun.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocket00FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("http",new HttpServerCodec());
        //netty 对大数据流处理的类
        pipeline.addLast("ChunkedWriteHandler",new ChunkedWriteHandler());
        //对httpMessage 进行聚合 继承了 FullHttpResponse 和 FullHttpRequest
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //************************以上代码用于处理http协议******************************

        //服务器处理协议 用于指定给client的连接访问路由 /ws
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new ChatHandler());


    }
}
