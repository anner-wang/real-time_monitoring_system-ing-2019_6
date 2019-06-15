package appServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyWebsocketInitialize extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new ChunkedWriteHandler());
        //一般的http请求netty会自动的分段，然后走一遍流程，这个handler会聚合请求，很重要
        channelPipeline.addLast(new HttpObjectAggregator(8192));
        //处理websocket,处理连接和心跳检测，数据会传递给下一个handle处理
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义的handler
        channelPipeline.addLast(new MyWebsocketHandler());

    }
}
