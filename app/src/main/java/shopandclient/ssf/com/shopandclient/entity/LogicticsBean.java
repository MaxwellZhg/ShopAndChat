package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/13.
 */
public class LogicticsBean {
    private String date;
    private String time;
    private String info;
    private int state;

    public LogicticsBean(String date, String time, String info, int state) {
        this.date = date;
        this.time = time;
        this.info = info;
        this.state = state;
    }

    public LogicticsBean() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LogicticsBean{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", info='" + info + '\'' +
                ", state=" + state +
                '}';
    }
}
