package shopandclient.ssf.com.shopandclient.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.PermissionsUtils;
import shopandclient.ssf.com.shopandclient.util.SpConfig;


/**
 * Created by zhg on 2019/5/27.
 */
public class MainActivity extends BaseActivity implements BaseBiz {


    @BindView(R.id.rl_chat)
    RelativeLayout rlChat;
    @BindView(R.id.iv_mykitchen)
    ImageView ivMykitchen;
    @BindView(R.id.iv_street)
    ImageView ivStreet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg), 0);
        //两个日历权限和一个数据读写权限
        String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.VIBRATE, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WAKE_LOCK,Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.READ_PHONE_STATE,Manifest.permission.RECEIVE_BOOT_COMPLETED  };
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

    @OnClick({R.id.rl_chat, R.id.rl_run_rabitts, R.id.rl_friends, R.id.rl_person_center, R.id.iv_mykitchen, R.id.iv_street})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_chat:
                chooseLuacher(InfomationActivity.class);
                break;
            case R.id.rl_run_rabitts:
                chooseLuacher(InfomationActivity.class);
                break;
            case R.id.rl_friends:
                chooseLuacher(FriendsListActivity.class);
                break;
            case R.id.rl_person_center:
                chooseLuacher(PersonCenterActivity.class);
                break;
            case R.id.iv_mykitchen:
                chooseLuacher(CategoryActivity.class);
                break;
            case R.id.iv_street:
                chooseLuacher(StreetActivity.class);
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
   public void chooseLuacher(Class clazz){
       if(SpConfig.getInstance().getBool("isLogin")){
           openActivity(clazz);
       }else{
           openActivity(LoginActivity.class);
       }
   }

    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            Log.e("tttttttttttt","权限通过，可以做其他事情!");
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

}
