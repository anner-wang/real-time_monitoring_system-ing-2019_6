package data;

import java.io.*;
import java.time.LocalDateTime;

public class FileOperation {

    //追加数据
    public void addData(String []data){
        String now= LocalDateTime.now().toString();
        //获取文件流
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("sensor.dat",true));
            bufferedWriter.write(now+"\t");
            for(String str:data)bufferedWriter.write(str+"\t");
            bufferedWriter.write("\n");
            bufferedWriter.flush();
            bufferedWriter.close();
//            System.out.print("传感器数据:");
//            for (String str:data) System.out.print(str+"\t");
//            System.out.println("添加到文件成功");
        }catch (IOException e){
            System.out.println("文件读取错误");
        }
    }
}
