package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.AddUser;
import shopandclient.ssf.com.shopandclient.entity.AddUserResult;
import shopandclient.ssf.com.shopandclient.entity.SendCodeBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.UserService;
import shopandclient.ssf.com.shopandclient.util.MD5Utils;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhg on 2019/5/29.
 */
public class ForgetActivity extends BaseActivity implements BaseBiz, TextWatcher {

    @BindView(R.id.et_credit_num)
    EditText etCreditNum;
    @BindView(R.id.line_credit)
    ImageView lineCredit;
    @BindView(R.id.tv_getcredit)
    TextView tvGetcredit;
    Timer timer;
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
    @BindView(R.id.rl_add_tips)
    TextView rlAddTips;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.cancle)
    TextView cancle;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private int recLen = 60;//跳过倒计时提示5秒
    TimerTask task;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_forget_pass;
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
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.white), 0);
        etCreditNum.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            lineCredit.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg));
            etCreditNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg));
        } else {
            lineCredit.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.line_color));
            etCreditNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
        }
    }

    public void startTask() throws InterruptedException {
        if (task == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recLen--;
                            tvGetcredit.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.credit_time, recLen));
                            if (recLen < 0) {
                                timer.cancel();
                                task = null;
                                timer = null;
                                tvGetcredit.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.get_credit));//倒计时到0隐藏字体
                            }
                        }
                    });
                }
            };
        }
        timer.schedule(task, 1000, 1000);
    }

    @OnClick({R.id.rl_btn_back, R.id.tv_getcredit,R.id.btn_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.tv_getcredit:
                if (recLen == 60) {
                    tvGetcredit.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.minutiues));
                    try {
                        startTask();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (recLen < 0) {
                    recLen = 60;
                    tvGetcredit.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.minutiues));
                    try {
                        startTask();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                getSendCode();
                break;
            case R.id.btn_ensure:
                updatePsw(etPhone.getText().toString().trim(),MD5Utils.getMd5Str(etPassword.getText().toString().trim()),etCreditNum.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        ivBack.setImageResource(R.drawable.back_btn);
        tvCenterTitle.setVisibility(View.INVISIBLE);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
    }

    public void getSendCode() {
        UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
        Call<SendCodeBean> call = userService.getSendCode(etPhone.getText().toString().trim());
        call.enqueue(new Callback<SendCodeBean>() {
            @Override
            public void onResponse(Call<SendCodeBean> call, Response<SendCodeBean> response) {
                if(response.body().getCode()==200&&response!=null) {
                    Log.e("ttttttt", response.body().toString());
                    etCreditNum.setText(response.body().getData().getCode());
                }
            }

            @Override
            public void onFailure(Call<SendCodeBean> call, Throwable t) {

            }
        });
    }

    public void updatePsw(String phone, String pass,String code){
        UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
        Call<AddUserResult> call=userService.updatePwd(new AddUser(phone,MD5Utils.getMd5Str(pass),code));
        call.enqueue(new Callback<AddUserResult>() {
            @Override
            public void onResponse(Call<AddUserResult> call, Response<AddUserResult> response) {
                if(response.body().getCode()==200) {
                    ToastUtil.showToast(mContext, "修改成功");
                    finish();
                }else{
                    ToastUtil.showToast(mContext, response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<AddUserResult> call, Throwable t) {

            }
        });
    }

}
