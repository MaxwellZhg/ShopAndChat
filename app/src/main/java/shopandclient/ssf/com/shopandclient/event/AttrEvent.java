package shopandclient.ssf.com.shopandclient.event;

/**
 * Created by zhg on 2019/6/27.
 */
public class AttrEvent {
    private int type;
    private int pos;


    public AttrEvent(int type, int pos) {
        this.type = type;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
