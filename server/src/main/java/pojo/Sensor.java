package pojo;

public class Sensor {
    private double temp;
    private double air;

    public Sensor() {
    }

    public Sensor(double temp, double air) {
        this.temp = temp;
        this.air = air;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getAir() {
        return air;
    }

    public void setAir(double air) {
        this.air = air;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "temp=" + temp +
                ", air=" + air +
                '}';
    }
}
