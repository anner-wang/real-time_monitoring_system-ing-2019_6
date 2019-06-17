import appServer.MyWebsocketServer;
import boxServer.MyBoxServer;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        //箱子的服务端
        MyBoxServer myBoxServer=new MyBoxServer();
        //APP的服务端
        MyWebsocketServer myWebsocketServer=new MyWebsocketServer();
        //启动
        System.out.println("服务器启动成功....");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("监听端口8899....");
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
                    System.out.println("监听端口9988....");
                    myBoxServer.run();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
