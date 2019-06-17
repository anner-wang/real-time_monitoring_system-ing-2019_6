package dao;

import pojo.Sensor;

import java.util.List;

/*
用户数据库处理接口
 */
public interface SensorDao {
    //添加单条数据
    public void addOneSensor(Sensor sensor);
    //返回所有的数据
    public List<Sensor> getAllSensor();
}
