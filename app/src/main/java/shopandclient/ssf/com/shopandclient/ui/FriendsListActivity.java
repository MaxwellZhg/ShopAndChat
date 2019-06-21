package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.SortAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.Friend;
import shopandclient.ssf.com.shopandclient.entity.PinyinComparator;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.ScreenDipUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zhg on 2019/6/21.
 */
public class FriendsListActivity extends BaseActivity implements BaseBiz {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.lv_friends_list)
    ListView lvFriendsList;
    ArrayList<Friend> list = new ArrayList<>();
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private PopupWindow pop;
    private  boolean state=false;
    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_friends_list;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    protected void initView() {
        super.initView();
        initPop();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.order_detail));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        tvSave.setVisibility(View.INVISIBLE);
        ivScope.setImageResource(R.drawable.function);
        // 虚拟数据
        list.add(new Friend("李伟"));
        list.add(new Friend("张三"));
        list.add(new Friend("张三"));
        list.add(new Friend("步惊云"));
        list.add(new Friend("步惊云"));
        list.add(new Friend("张三"));
        list.add(new Friend("阿三"));
        list.add(new Friend("阿四"));
        list.add(new Friend("段誉"));
        list.add(new Friend("段正淳"));
        list.add(new Friend("张三丰"));
        list.add(new Friend("陈坤"));
        list.add(new Friend("林俊杰1"));
        list.add(new Friend("陈坤2"));
        list.add(new Friend("王二a"));
        list.add(new Friend("林俊杰a"));
        list.add(new Friend("张四"));
        list.add(new Friend("林俊杰"));
        list.add(new Friend("王二"));
        list.add(new Friend("王二b"));
        list.add(new Friend("赵四"));
        list.add(new Friend("杨坤"));
        list.add(new Friend("赵子龙"));
        list.add(new Friend("杨坤1"));
        list.add(new Friend("李伟1"));
        list.add(new Friend("宋江"));
        list.add(new Friend("宋江1"));
        list.add(new Friend("李伟3"));
        list.add(new Friend("#宋江1"));
        list.add(new Friend("#$$$李伟3"));
        Collections.sort(list, new PinyinComparator());
        Log.e("ttttttttt", list.toString());
        SortAdapter sa = new SortAdapter(this, list);
        lvFriendsList.setAdapter(sa);
        sa.notifyDataSetChanged();
    }

    private void initPop() {
        pop = new PopupWindow();
        View content = LayoutInflater.from(this).inflate(R.layout.item_add_friend_dailog, null);
        pop.setContentView(content);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT - ScreenDipUtil.dip2px(50));
        pop.setOutsideTouchable(false);
    }

    @OnClick({R.id.rl_btn_scope,R.id.rl_btn_back})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.rl_btn_scope:
                if(state==false){
                    state=true;
                    pop.showAsDropDown(rlAction, 0, 0);
                }else{
                    state=false;
                    pop.dismiss();
                }
                break;
            case R.id.rl_btn_back:
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state=false;
        if(pop.isShowing()) {
            pop.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        state=false;
        if(pop.isShowing()){
            pop.dismiss();
        }
    }
}
