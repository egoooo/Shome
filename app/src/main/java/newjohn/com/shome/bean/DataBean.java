package newjohn.com.shome.bean;

/**
 * Created by Administrator on 2017/10/9.
 */

public class DataBean {
    public  String washroomQuality = "0.0";
    public  String parlorTemperature = "0.0";
    public  String parlorHumidity = "0.0";
    public  String parlorInfrared = "0.0";
    public  String kitchenMethane = "0.0";
    public  String kitchenSmoke = "0.0";
    public  String kitchenFlame = "0.0";
    public  String bedroomTemperature = "0.0";
    public  String bedroomHumidity = "0.0";
    public  String parlorMagnetic = "0.0";

    @Override
    public String toString() {
        return "DataBean{" +
                "washroomQuality='" + washroomQuality + '\'' +
                ", parlorTemperature='" + parlorTemperature + '\'' +
                ", parlorHumidity='" + parlorHumidity + '\'' +
                ", parlorInfrared='" + parlorInfrared + '\'' +
                ", kitchenMethane='" + kitchenMethane + '\'' +
                ", kitchenSmoke='" + kitchenSmoke + '\'' +
                ", kitchenFlame='" + kitchenFlame + '\'' +
                ", bedroomTemperature='" + bedroomTemperature + '\'' +
                ", bedroomHumidity='" + bedroomHumidity + '\'' +
                ", parlorMagnetic='" + parlorMagnetic + '\'' +
                '}';
    }

    public String getWashroomQuality() {
        return washroomQuality;
    }

    public String getParlorTemperature() {
        return parlorTemperature;
    }

    public String getParlorHumidity() {
        return parlorHumidity;
    }

    public String getParlorInfrared() {
        return parlorInfrared;
    }

    public String getKitchenMethane() {
        return kitchenMethane;
    }

    public String getKitchenSmoke() {
        return kitchenSmoke;
    }

    public String getKitchenFlame() {
        return kitchenFlame;
    }

    public String getBedroomTemperature() {
        return bedroomTemperature;
    }

    public String getBedroomHumidity() {
        return bedroomHumidity;
    }

    public String getParlorMagnetic() {
        return parlorMagnetic;
    }

    public void setWashroomQuality(String washroomQuality) {
        this.washroomQuality = washroomQuality;
    }

    public void setParlorTemperature(String parlorTemperature) {
        this.parlorTemperature = parlorTemperature;
    }

    public void setParlorHumidity(String parlorHumidity) {
        this.parlorHumidity = parlorHumidity;
    }

    public void setParlorInfrared(String parlorInfrared) {
        this.parlorInfrared = parlorInfrared;
    }

    public void setKitchenMethane(String kitchenMethane) {
        this.kitchenMethane = kitchenMethane;
    }

    public void setKitchenSmoke(String kitchenSmoke) {
        this.kitchenSmoke = kitchenSmoke;
    }

    public void setKitchenFlame(String kitchenFlame) {
        this.kitchenFlame = kitchenFlame;
    }

    public void setBedroomTemperature(String bedroomTemperature) {
        this.bedroomTemperature = bedroomTemperature;
    }

    public void setBedroomHumidity(String bedroomHumidity) {
        this.bedroomHumidity = bedroomHumidity;
    }

    public void setParlorMagnetic(String parlorMagnetic) {
        this.parlorMagnetic = parlorMagnetic;
    }
}
