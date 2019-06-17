package dao;

import java.sql.*;

public final class JDBCUtilSingle {

    //数据连接的基本信息
    private static String url="jdbc:mysql://localhost:3306/childmissing";
    private static String name="root";
    private static String password="annerlogin";
    private Connection conn=null;
    //保证饿汉的单例模式
    private static JDBCUtilSingle ourInstance = new JDBCUtilSingle();
    private JDBCUtilSingle() { }
    public static JDBCUtilSingle getInstance() {
        return ourInstance;
    }
    //静态代码块注册数据库驱动，保证编译过程只执行一次
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //获取连接
    public Connection getConnection(){
        try {
            conn= DriverManager.getConnection(url,name,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    //关闭连接
    public void closeConnection(ResultSet rs,PreparedStatement statement,Connection connection){
        try{
            if (rs!=null) rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (statement!=null)statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (connection!=null)connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
