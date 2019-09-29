package shopandclient.ssf.com.shopandclient.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.hyphenate.*;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.DemoHelper;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.UserInfo;
import shopandclient.ssf.com.shopandclient.im.Constant;
import shopandclient.ssf.com.shopandclient.im.db.InviteMessgeDao;
import shopandclient.ssf.com.shopandclient.im.db.UserDao;
import shopandclient.ssf.com.shopandclient.im.ui.ChatActivity;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.UserService;
import shopandclient.ssf.com.shopandclient.util.*;

import java.util.List;


/**
 * Created by zhg on 2019/5/27.
 */
public class MainActivity extends BaseActivity implements BaseBiz, Observer {


    @BindView(R.id.rl_chat)
    RelativeLayout rlChat;
    @BindView(R.id.iv_mykitchen)
    ImageView ivMykitchen;
    @BindView(R.id.iv_street)
    ImageView ivStreet;
    @BindView(R.id.tv_info_tips)
    TextView tvInfoTips;
    @BindView(R.id.rl_tv_info)
    RelativeLayout rlTvInfo;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_credit)
    ImageView ivCredit;
    @BindView(R.id.tv_credit)
    TextView tvCredit;
    @BindView(R.id.rl_credit_info)
    RelativeLayout rlCreditInfo;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.tv_member)
    TextView tvMember;
    @BindView(R.id.rl_level_info)
    RelativeLayout rlLevelInfo;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    private InviteMessgeDao inviteMessgeDao;
    private BroadcastReceiver internalDebugReceiver;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;
    // user logged into another device
    public boolean isConflict = false;
    // user account was removed
    private boolean isCurrentAccountRemoved = false;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tokenManager =  TokenManager.newInstance();
        tokenManager.registerObserver(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg), 0);
        //两个日历权限和一个数据读写权限
        String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.VIBRATE, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WAKE_LOCK, Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_BOOT_COMPLETED};
//        PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(this, permissions, permissionsResult);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @OnClick({R.id.rl_chat, R.id.rl_run_rabitts, R.id.rl_friends, R.id.rl_person_center, R.id.iv_mykitchen, R.id.iv_street, R.id.rl_credit_info, R.id.rl_level_info,R.id.iv_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_chat:
                chooseLuacher(InfomationActivity.class);
                break;
            case R.id.rl_run_rabitts:
                ToastUtil.showToast(this, "正在开发，敬请关注");
                break;
            case R.id.rl_friends:
                chooseLuacher(FriendsListActivity.class);
                break;
            case R.id.rl_person_center:
                chooseLuacher(PersonCenterActivity.class);
                break;
            case R.id.iv_mykitchen:
                chooseLuacher(StreetActivity.class);
                break;
            case R.id.iv_street:
                chooseLuacher(CategoryActivity.class);
                break;
            case R.id.rl_credit_info:
                ivCredit.setImageResource(R.drawable.icon_creditrating_green);
                tvCredit.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.credit_level));
                ivVip.setImageResource(R.drawable.icon_membership_gray);
                tvMember.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.member_ship));
                break;
            case R.id.rl_level_info:
                ivCredit.setImageResource(R.drawable.icon_creditrating_gray);
                tvCredit.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.member_ship));
                ivVip.setImageResource(R.drawable.icon_membership_green);
                tvMember.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.credit_level));
                break;
            case R.id.iv_img:
                chooseLuacher(PersonCenterActivity.class);
                break;
        }
    }

 /*   @OnClick({R.id.rl_chat, R.id.iv_mykitchen, R.id.iv_street})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_chat:
                openActivity(LoginActivity.class);
                break;
            case R.id.iv_mykitchen:
                openActivity(CategoryActivity.class);
                break;
            case R.id.iv_street:
                openActivity(DispatchActivity.class);
                break;
        }
    }*/

   /* @OnClick(R.id.rl_chat)
    public void onViewClicked() {
        openActivity(LoginActivity.class);
    }*/

    @Override
    protected void initView() {
        super.initView();
        if (SpConfig.getInstance().getString(Constants.USERNAME) != null && !SpConfig.getInstance().getString(Constants.USERNAME).equals("")) {
            getUserInfo(SpConfig.getInstance().getInt(Constants.USERID));
        }
        inviteMessgeDao = new InviteMessgeDao(this);
        UserDao userDao = new UserDao(this);
        //register broadcast receiver to receive the change of group from DemoHelper
        registerBroadcastReceiver();

        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
        EMClient.getInstance().addClientListener(clientListener);
        EMClient.getInstance().addMultiDeviceListener(new MyMultiDeviceListener());
        //debug purpose only
        registerInternalDebugReceiver();
    }

    public void chooseLuacher(Class clazz) {
        if (SpConfig.getInstance().getBool("isLogin")) {
            if(clazz!=PersonCenterActivity.class) {
                openActivity(clazz);
            }else{
                Intent intent = new Intent(MainActivity.this, PersonCenterActivity.class);
                startActivityForResult(intent, 1);
            }
        } else {
            luanchLogin();
        }
    }

    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            Log.e("tttttttttttt", "权限通过，可以做其他事情!");
        }

        @Override
        public void forbitPermissons() {
//            finish();
            Toast.makeText(MainActivity.this, "权限不通过!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                updateUnreadLabel();
                String action = intent.getAction();
           /*     if(action.equals(Constant.ACTION_GROUP_CHANAGED)){
                    if (EaseCommonUtils.getTopActivity(MainActivity.this).equals(GroupsActivity.class.getName())) {
                        GroupsActivity.instance.onResume();
                    }
                }*/
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }

    public class MyContactListener implements EMContactListener {
        @Override
        public void onContactAdded(String username) {
        }

        @Override
        public void onContactDeleted(final String username) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.toChatUsername != null &&
                            username.equals(ChatActivity.activityInstance.toChatUsername)) {
                        String st10 = getResources().getString(R.string.have_you_removed);
                        Toast.makeText(MainActivity.this, ChatActivity.activityInstance.getToChatUsername() + st10, Toast.LENGTH_LONG)
                                .show();
                        ChatActivity.activityInstance.finish();
                    }
                }
            });
            //updateUnreadAddressLable();
        }

        @Override
        public void onContactInvited(String username, String reason) {
        }

        @Override
        public void onFriendRequestAccepted(String username) {
        }

        @Override
        public void onFriendRequestDeclined(String username) {
        }
    }

    public class MyMultiDeviceListener implements EMMultiDeviceListener {

        @Override
        public void onContactEvent(int event, String target, String ext) {

        }

        @Override
        public void onGroupEvent(int event, String target, final List<String> username) {
            switch (event) {
                case EMMultiDeviceListener.GROUP_LEAVE:
                    ChatActivity.activityInstance.finish();
                    break;
                default:
                    break;
            }
        }
    }

    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
                DemoHelper.getInstance().getNotifier().vibrateAndPlayTone(message);
            }
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            refreshUIWithMessage();
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            refreshUIWithMessage();
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                updateUnreadLabel();
            }
        });
    }

    /**
     * update unread message count
     */
    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
        if (count > 0) {
            tvInfoTips.setText(String.valueOf(count));
            rlTvInfo.setVisibility(View.VISIBLE);
        } else {
            rlTvInfo.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * get unread message count
     *
     * @return
     */
    public int getUnreadMsgCountTotal() {
        return EMClient.getInstance().chatManager().getUnreadMessageCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isConflict && !isCurrentAccountRemoved) {
            updateUnreadLabel();
            // updateUnreadAddressLable();
        }

        // unregister this event listener when this activity enters the
        // background
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.pushActivity(this);

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    protected void onPause() {
        super.onPause();

        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        EMClient.getInstance().removeClientListener(clientListener);
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.popActivity(this);
    }

    EMClientListener clientListener = new EMClientListener() {
        @Override
        public void onMigrate2x(boolean success) {
            Toast.makeText(MainActivity.this, "onUpgradeFrom 2.x to 3.x " + (success ? "success" : "fail"), Toast.LENGTH_LONG).show();
            if (success) {
                refreshUIWithMessage();
            }
        }
    };

    /**
     * debug purpose only, you can ignore this
     */
    private void registerInternalDebugReceiver() {
        internalDebugReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                DemoHelper.getInstance().logout(false, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                finish();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        });
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                    }

                    @Override
                    public void onError(int code, String message) {
                    }
                });
            }
        };
        IntentFilter filter = new IntentFilter(getPackageName() + ".em_internal_debug");
        registerReceiver(internalDebugReceiver, filter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String textData = data.getStringExtra("username");
                tvUserName.setText(textData);
            }
        }
    }

    public void luanchLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent, 1);
    }

    public void getUserInfo(int id) {
        UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
        Call<UserInfo> call = userService.getUserInfo(id);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body().getCode() == 200 && response != null) {
                    Log.e("ttttttt", response.body().toString());
                    tvUserName.setText(response.body().getData().getUserName());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internalDebugReceiver);
    }
}
