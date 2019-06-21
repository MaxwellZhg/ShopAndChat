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
import shopandclient.ssf.com.shopandclient.util.SpConfig;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhg on 2019/4/15.
 */
public class RetrofitHandle {
    private static final int DEFAULT_TIMEOUT = 10;
    public static String BASE_URL = "https://api.beautystudio.com.cn";

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
                String token = SpConfig.getInstance().getString(Constants.SESSIONID_STRING);
                if (token == null || TextUtils.isEmpty(token)) {
                    return chain.proceed(originalRequest);
                }
                Request authorised = originalRequest.newBuilder()
                        .header("Authorization", "Bearer" + token)//此处的token 是你保存在本地的
                        .build();


                Response response = chain.proceed(authorised);//执行此次请求
                if (response.code() == 401) {
                    SpConfig.getInstance().removeData(Constants.SESSIONID_STRING);
                }
                return response;
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(tokenInterceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String language = SpConfig.getInstance().getString("language");
                String currency = SpConfig.getInstance().getString("currency");
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("allsale-language", language)
                        .addHeader("allsale-currency", currency)
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
                .baseUrl(BASE_URL)
//                .baseUrl(TextUtils.isEmpty(SpConfig.getInstance().getString(Constants.BASEURL_TEST)) ? BASE_URL : SpConfig.getInstance().getString(Constants.BASEURL_TEST))
                .build();

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final RetrofitHandle INSTANCE = new RetrofitHandle();
    }
}
