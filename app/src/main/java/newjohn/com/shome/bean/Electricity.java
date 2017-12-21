package newjohn.com.shome.bean;

/**
 * Created by Administrator on 2017/9/29.
 */

public class Electricity {
    String  device;
    double  current;
    double voltage;
    double  power;
    double powerConsumption;
    String    state;

    public String getDevice() {
        return device;
    }

    public double getCurrent() {
        return current;
    }

    public double getVoltage() {
        return voltage;
    }

    public double getPower() {
        return power;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public String getState() {
        return state;
    }

    public Electricity(String device, double current, double voltage, double power, double powerConsumption, String state) {
        this.device = device;
        this.current = current;
        this.voltage = voltage;
        this.power = power;
        this.powerConsumption = powerConsumption;
        this.state = state;
    }
}
