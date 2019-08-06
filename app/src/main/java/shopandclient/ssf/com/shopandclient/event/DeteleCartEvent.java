package shopandclient.ssf.com.shopandclient.event;

public class DeteleCartEvent {
    private int pos;

    public DeteleCartEvent(int pos) {
        this.pos = pos;
    }

    public DeteleCartEvent() {
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "DeteleCartEvent{" +
                "pos=" + pos +
                '}';
    }
}
