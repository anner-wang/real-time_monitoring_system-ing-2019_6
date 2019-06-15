package boxServer;

import appServer.MyWebsocketHandler;
import data.FileOperation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class MyBoxServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private FileOperation fileOperation=new FileOperation();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //返回内容
       if (msg instanceof HttpRequest){
           HttpRequest httpRequest=(HttpRequest)msg;
           URI uri=new URI(httpRequest.uri());
           String[] data=uri.getQuery().split("&");
           if (data!=null){
               fileOperation.addData(data);
               StringBuffer sb=new StringBuffer();
               for(String s:data)sb.append(s+"\t");
               MyWebsocketHandler.push(sb.toString());
           }
           ctx.close();
       }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
