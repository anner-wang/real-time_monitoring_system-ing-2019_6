package appServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class MyWebsocketServer {
   public void run() throws InterruptedException {
       EventLoopGroup bossGroup=new NioEventLoopGroup();
       EventLoopGroup workerGroup=new NioEventLoopGroup();
       try{
           ServerBootstrap serverBootstrap=new ServerBootstrap();
           serverBootstrap.group(bossGroup,workerGroup)
                   .channel(NioServerSocketChannel.class)
                   .childHandler(new MyWebsocketInitialize());
           ChannelFuture channelFuture=serverBootstrap.bind(new InetSocketAddress(9988)).sync();
           channelFuture.channel().closeFuture().sync();
       }finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
       }
   }
}
