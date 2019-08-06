package shopandclient.ssf.com.shopandclient.net.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import shopandclient.ssf.com.shopandclient.entity.*;

public interface ChatCenterService {
    @GET("/api/Chat/GetMyFriendList")
    Call<FriendListBean> getFriendList();
    @GET("/api/Chat/AddFriend")
    Call<PostComment> addFriend(@Query("id") int id);
    @POST("/api/Chat/GetSearchUser")
    Call<SearchFriend> serachFriend(@Body SearchUserParams params);
    @POST("/api/Chat/SetFriendState")
    Call<PostComment> comfrimFriendState(@Body ConfrimParams params);
    @GET("/api/Chat/DelFriend")
    Call<PostComment> deteleFriends(@Query("id") int id);
    @POST("/api/Chat/CreateChatGroup")
    Call<PostComment> createGroup(@Body CreateGroupParams params);
    @GET("/api/Chat/GetMyGroup")
    Call<MyGroupBean> getMyGroup();
    @GET("/api/Chat/GetGroupMemmberInfo")
    Call<GroupInfoBean> getGroupInfo(@Query("id") int id);
    @GET("/api/Chat/DelGroup")
    Call<PostComment> deteleGroup(@Query("groupid") int id);
    @POST("/api/Chat/AddFriendToGroupList")
    Call<PostComment> addFriendToGroupList(@Body AddIntpGroupParams params);
    @GET("/api/Chat/DelFriendFromGroupList")
    Call<PostComment> deteleSingleFormGroup(@Query("userid")int userid,@Query("groupid") int  groupid);
    @POST("/api/Chat/UpdateGroupName")
    Call<PostComment> updateGroupName(@Body UpdateGroupNameParams params);
    @GET("/api/Chat/UpdateGroupAdmin")
    Call<PostComment> updateGroupAdmin(@Query("userid")int userid,@Query("groupid") int  groupid);
    @POST("/api/Chat/SetForbidChat")
    Call<PostComment> forbinChatMember(@Body ForbinChatGroupMemberParams params);
    @GET("/api/Chat/OutGroup")
    Call<PostComment> outGroupMember(@Query("groupid") int  groupid);
    @POST("/api/Chat/UpdateUserInfo")
    Call<PostComment> updateUserInfo(@Body UpdateUserInfoParams params);
    @POST("/api/Chat/GetLocalUser")
    Call<LocalUserBean> getLocalUser(@Body LocalUserParams params);
}
