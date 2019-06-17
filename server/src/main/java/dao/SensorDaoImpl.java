package dao;

import jdk.nashorn.internal.scripts.JD;
import pojo.Sensor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SensorDaoImpl implements SensorDao {
    //添加传感器数据
    @Override
    public void addOneSensor(Sensor sensor) {
        JDBCUtilSingle jdbcUtilSingle= JDBCUtilSingle.getInstance();
        Connection connection=jdbcUtilSingle.getConnection();
        String sql="insert into sensor (time,temp,air) values (?,?,?)";
        PreparedStatement preparedStatement=null;
        //添加数据
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDate(1,new Date(new java.util.Date().getTime()));
            preparedStatement.setDouble(2,sensor.getTemp());
            preparedStatement.setDouble(3,sensor.getAir());
            preparedStatement.execute();
            //System.out.println("传感器数据: "+sensor+"添加到数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtilSingle.closeConnection(null,preparedStatement,connection);
        }
    }

    @Override
    public List<Sensor> getAllSensor() {
        return null;
    }
}
