package boxServer;

import appServer.MyWebsocketHandler;
import appServer.MyWebsocketServer;
import dao.SensorDao;
import dao.SensorDaoImpl;
import data.FileOperation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import pojo.Sensor;

import java.net.URI;
import java.util.Date;

public class MyBoxServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private FileOperation fileOperation=new FileOperation();
    private SensorDao sensorDao=new SensorDaoImpl();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //返回内容
       if (msg instanceof HttpRequest){
           HttpRequest httpRequest=(HttpRequest)msg;
           URI uri=new URI(httpRequest.uri());
           String[] data=uri.getQuery().split("&");
           //分解传感器数据格式
           double temp=Double.valueOf(data[0].split("=")[1]);
           double air=Double.valueOf(data[1].split("=")[1]);
           System.out.println(new Date()+"  接收到温度传感器数据:"+temp+"\t"+"二氧化碳传感器数据:"+air);
           //构造数据库库对象
           Sensor sensor=new Sensor(temp,air);
           if (data!=null){
               fileOperation.addData(data);
               //添加数据到数据库
               sensorDao.addOneSensor(sensor);
               StringBuffer sb=new StringBuffer();
               //发送数据到展示模块
               MyWebsocketHandler.push("温度传感器:"+temp+"\n湿度传感器:"+air+"\n");
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
