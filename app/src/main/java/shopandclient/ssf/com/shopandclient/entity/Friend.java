package shopandclient.ssf.com.shopandclient.entity;

import shopandclient.ssf.com.shopandclient.util.PinyinUtils;

/**
 * Created by zhg on 2019/6/21.
 */
public class Friend {
    private String name;
    private String sortLetters;//显示数据拼音的首字母

    public Friend(String name) {
        this.name = name;
        String pinyin = PinyinUtils.getPingYin(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();
        setSortLetters(sortString);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                '}';
    }
}
