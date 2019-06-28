package shopandclient.ssf.com.shopandclient.util;

import java.util.Date;

/**
 * Created by zhg on 2019/6/25.
 */
public class NoceStrUtil {
    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static String getNonceStr(){
        String result="";
        for (int i=0;i<15;i++){
            int r= (int) Math.floor(Math.random()*62);
            result +=hexDigIts[r];
        }
        Date now=new Date();
        String day=now.getDate() + "" + now.getHours() + "" + now.getMinutes() + "" + now.getSeconds();
        return day+result;
    }
    public static String getSign(String token,String noneStr, long timestamp){
        String str = "nonce=" + noneStr + "&timestamp=" + timestamp + "&token=" + token;
        String sign = MD5Utils.MD5Encode(str,"UTF-8").toUpperCase();
        return sign;
    }
}
