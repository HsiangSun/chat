package cn.hsiangsun.test;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //get pipeline
        ChannelPipeline pipeline = socketChannel.pipeline();
        //http support
        pipeline.addLast("http",new HttpServerCodec());
        pipeline.addLast("myHandler",new CustomHandler());

    }
}
