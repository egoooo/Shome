package newjohn.com.shome.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */

public class Result {
    public String stat;
    public List<Data> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
