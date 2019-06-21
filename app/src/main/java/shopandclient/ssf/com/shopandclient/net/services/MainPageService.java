package shopandclient.ssf.com.shopandclient.net.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import shopandclient.ssf.com.shopandclient.entity.HotSearchBean;
import shopandclient.ssf.com.shopandclient.entity.MainPageListBean;
import shopandclient.ssf.com.shopandclient.entity.MainPageTitleBean;


/**
 * Created by zhg on 2019/4/15.
 */
public interface MainPageService {
    //首页主题列表
    @GET("/api/s/topics/{type}/{pageNum}")
    Observable<MainPageListBean> mainPageList(@Path("type") int type, @Path("pageNum") int pageNum);

    //首页主题列表
    @GET("/api/s/topics/{type}/{pageNum}")
    Call<MainPageListBean> mainPageList1(@Path("type") int type, @Path("pageNum") int pageNum);

    //首页banner
    @GET("/api/s/topics/basic/{type}")
    Call<MainPageTitleBean> mainPageTitle(@Path("type") int type);

    //热门搜索
    @GET("/hotSearches")
    Call<HotSearchBean> getHotSearch();
}
