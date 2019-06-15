import appServer.MyWebsocketServer;
import boxServer.MyBoxServer;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        //箱子的服务端
        MyBoxServer myBoxServer=new MyBoxServer();
        //APP的服务端
        MyWebsocketServer myWebsocketServer=new MyWebsocketServer();
        //启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myWebsocketServer.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myBoxServer.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
