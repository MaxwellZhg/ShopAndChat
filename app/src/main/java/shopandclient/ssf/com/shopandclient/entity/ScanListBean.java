package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class ScanListBean {

    private int Code;
    private String Result;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int totalNum;
        private String title;
        private ArrayList<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String ProName;
            private String Img;
            private double Uprice;
            private double discount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProName() {
                return ProName;
            }

            public void setProName(String ProName) {
                this.ProName = ProName;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public double getUprice() {
                return Uprice;
            }

            public void setUprice(double Uprice) {
                this.Uprice = Uprice;
            }

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
                this.discount = discount;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id=" + id +
                        ", ProName='" + ProName + '\'' +
                        ", Img='" + Img + '\'' +
                        ", Uprice=" + Uprice +
                        ", discount=" + discount +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "totalNum=" + totalNum +
                    ", title='" + title + '\'' +
                    ", list=" + list +
                    '}';
        }
    }
}
