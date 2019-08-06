package shopandclient.ssf.com.shopandclient.net;

import android.text.TextUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shopandclient.ssf.com.shopandclient.BuildConfig;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.util.NoceStrUtil;
import shopandclient.ssf.com.shopandclient.util.SpConfig;


import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhg on 2019/4/15.
 */
public class RetrofitHandle {
    private static final int DEFAULT_TIMEOUT = 10;
    public static String BASE_URL = "https://api.beautystudio.com.cn";
    public static String ShOP_BASE_URL="http://xd.xdgia.com";
    public Retrofit retrofit;

    //构造方法私有
    private RetrofitHandle() {
        initRetrofit();
    }

    //获取单例
    public static RetrofitHandle getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void initRetrofit() {
        //添加统一的log管理，打release包的时候记得将Level设置成Level.NONE
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        Interceptor tokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                String token = SpConfig.getInstance().getString(Constants.TOKEN);
                if (token == null || TextUtils.isEmpty(token)) {
                    return chain.proceed(originalRequest);
                }
                Request authorised = originalRequest.newBuilder()
                        .header("x-token",  token)//此处的token 是你保存在本地的
                        .build();


                Response response = chain.proceed(authorised);//执行此次请求

                return response;
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(tokenInterceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String noncestr = NoceStrUtil.getNonceStr();
                long timestamp = new Date().getTime();
                String sign= NoceStrUtil.getSign(SpConfig.getInstance().getString(Constants.TOKEN),noncestr,timestamp);
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("x-noncestr", noncestr)
                        .addHeader("x-timestamp", String.valueOf(timestamp))
                        .addHeader("x-sign", sign)
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .build();
                return chain.proceed(request);
            }
        });

        //手动创建一个OkHttpClient并设置超时时间
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ShOP_BASE_URL)
                .build();

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final RetrofitHandle INSTANCE = new RetrofitHandle();
    }
}
