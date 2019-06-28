package shopandclient.ssf.com.shopandclient.net.services;

import retrofit2.Call;
import retrofit2.http.*;
import shopandclient.ssf.com.shopandclient.entity.*;

/**
 * Created by zhg on 2019/6/24.
 */
public interface ProductService {
    @GET("/api/product/GetProSeries ")
    Call<Prosirers> getProirers();

    @GET("/api/product/GetProCategory")
    Call<CategoryBean> getCategory(@Query("sid") int sid);

    @GET("/api/product/GetProductList")
    Call<ProductListBean> getProductList(@Query("page") int page,@Query("orderAsc") int orderAsc,@Query("categroyID") int categroyID,@Query("seriesID") int seriresId);

    @GET("/api/product/GetProductInfo")
    Call<ProductInfo> getProductInfo(@Query("id") int id);
    @GET("/api/product/GetSelectAttrValueOfPriceOrImg")
    Call<AttrSelectBean> getAttrSelectInfo(@Query("attrL1ID") int attrL1ID,@Query("attrL2ID") int attrL2ID);
    @GET("/api/product/GetCommentInfo")
    Call<CommentBean> getCommentInfo(@Query("id") int id,@Query("page") int page);
    @POST("/api/product/Comment")
    Call<PostComment> postCommentInfo(@Body CommentParams params);
    @POST("/api/product/SetGiveLike")
    Call<PostComment> setGiveLike(@Body PostLikeParams params);
    @POST("/api/product/ProductCollection")
    Call<PostComment> setCollect(@Body CollectParams params);
    @GET("/api/product/StoreInfo")
    Call<StoreInfoBean> getStoreInfo(@Query("id") int id,@Query("page") int page);
    @POST("/api/product/StoreCollection")
    Call<PostComment> setStoreCollect(@Body CollectParams params);
}
