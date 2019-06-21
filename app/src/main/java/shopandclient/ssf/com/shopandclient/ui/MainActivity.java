package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;


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
                openActivity(FriendsListActivity.class);
                break;
            case R.id.rl_run_rabitts:
                openActivity(InfomationActivity.class);
                break;
            case R.id.rl_friends:
                openActivity(AddGroupChatActivity.class);
                break;
            case R.id.rl_person_center:
                openActivity(PersonCenterActivity.class);
                break;
            case R.id.iv_mykitchen:
                openActivity(CategoryActivity.class);
                break;
            case R.id.iv_street:
                openActivity(StreetActivity.class);
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

}
