package boxServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyBoxServer {
    public void run() throws InterruptedException {
        //接收客户端连接
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        //完成连接处理
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            //启动服务端类
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyBoxServerInitializer());
            //绑定
            ChannelFuture channelFuture= serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }

    }
}
