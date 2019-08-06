package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupParams {

    private ArrayList<ListBean> list;

    public ArrayList<ListBean> getList() {
        return list;
    }

    public CreateGroupParams(ArrayList<ListBean> list) {
        this.list = list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int userid;
        private String name;

        public ListBean(int userid, String name) {
            this.userid = userid;
            this.name = name;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
