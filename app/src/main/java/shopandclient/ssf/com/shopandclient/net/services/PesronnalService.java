package shopandclient.ssf.com.shopandclient.net.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import shopandclient.ssf.com.shopandclient.entity.*;

public interface PesronnalService {
    @GET("/api/PersonalCenter/GetHistoryBrowingList")
    Call<ScanListBean> getScanlist(@Query("typeid") int typeid, @Query("page") int page);
    @GET("/api/PersonalCenter/GetMyCollectProduct")
    Call<ScanListBean> getCollectionGoods(@Query("page") int page);
    @GET("/api/PersonalCenter/GetMyCollectStore")
    Call<ScanListBean> getCollectionStore(@Query("page") int page);
    @GET("/api/PersonalCenter/GetMyAddress")
    Call<AddressBean> getAddressList();
    @POST("/api/PersonalCenter/Add")
    Call<PostComment> postAddAddress(@Body AddAddressParams params);
    @GET("/api/PersonalCenter/GetAddressInfo")
    Call<AddressInfo> getAddressInfo(@Query("id") int id);
    @POST("/api/PersonalCenter/Update")
    Call<PostComment> postUpdateAddress(@Body UpdateParams params);
    @GET("/api/PersonalCenter/setDefultAddress")
    Call<PostComment> setDefaultAddress(@Query("id") int id);
    @GET("/api/PersonalCenter/Delete")
    Call<PostComment> deteleAddress(@Query("id") int id);
    @GET("/api/PersonalCenter/BuyCart")
    Call<ShopCartBean> getShopCartInfo();
    @GET("/api/PersonalCenter/DeleteSingle")
    Call<PostComment> deteleShopCart(@Query("id") int id);
    @GET("/api/PersonalCenter/GetProAttrValue")
    Call<ShopCartAttrs> getShopCartGoodsAttr(@Query("id") int id);
    @GET("/api/product/GetSelectAttrValueOfPriceOrImg")
    Call<ShopCartChooseAttrInfo> getChooseAttrInfo(@Query("attrL1ID") int attrL1ID,@Query("attrL2ID") int attrL2ID);
    @POST("/api/PersonalCenter/UpdateAmount")
    Call<PostComment> updateShopCartNum(@Body UpdateCartNumParams params);
    @POST("/api/PersonalCenter/UpdateAttrValue")
    Call<UpdateShopGoodsAttrInfo> updateShopCartAttr(@Body UpdateShopCartGoodsAttrParams params);
    @POST("/api/PersonalCenter/ConfirmOrder")
    Call<LimmitBuyBean> limmitBuy(@Body LimitBuyParams params);
    @POST("/api/PersonalCenter/ConfirmOrderFromCart")
    Call<LimmitBuyBean> limmitCartBuy(@Body LimitCartBuyParams params);
}
