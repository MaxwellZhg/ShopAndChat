package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
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
public class RegisterActivity extends BaseActivity implements BaseBiz, TextWatcher,View.OnClickListener {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.line_phone)
    ImageView linePhone;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    boolean input =false;
    private ImageView iv_open_eye;
    private EditText et_password;
    private EditText et_credit_num;
    private ImageView iv_credit_line;
    private TextView account_login;
    private TextView find_pass;
    private TextView get_credit_code;
    Timer timer;
    private int recLen = 60;//跳过倒计时提示5秒
    TimerTask task;
    private TextView cancle;
    private Button btn_register;
    private String mPhone;
    private String mPswd;
    private String mCode;
    private String mPswagain;

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,MyApplication.getInstance().mContext.getResources().getColor(R.color.white),0);
        etPhone.addTextChangedListener(this);
        cancle = (TextView) findViewById(R.id.cancle);
        cancle.setOnClickListener(this);
        et_password = (EditText)findViewById(R.id.et_password);
        et_credit_num = (EditText)findViewById(R.id.et_credit_num);
        iv_open_eye = (ImageView)findViewById(R.id.iv_open_eye);
        iv_open_eye.setOnClickListener(this);
        iv_credit_line = (ImageView) findViewById(R.id.iv_credit_line);
        account_login = (TextView)findViewById(R.id.account_login);
        account_login.setOnClickListener(this);
        find_pass = (TextView)findViewById(R.id.find_pass);
        find_pass.setOnClickListener(this);
        get_credit_code = (TextView) findViewById(R.id.get_credit_code);
        get_credit_code.setOnClickListener(this);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        et_credit_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    iv_credit_line.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg));
                    et_credit_num.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg));
                } else {
                    iv_credit_line.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.line_color));
                    et_credit_num.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                }
            }
        });
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_register;
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
            etPhone.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg));
            linePhone.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.theme_bg));
        } else {
            etPhone.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
            linePhone.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.line_color));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_open_eye:
            if (input) {
                input = false;
                iv_open_eye.setImageDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.close_num));
                et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                et_password.setSelection(et_password.getText().toString().length());
            } else {
                input = true;
                iv_open_eye.setImageDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.open_num));
                et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                et_password.setSelection(et_password.getText().toString().length());
            }
            break;
            case R.id.account_login:
                luancherLogin(LoginActivity.class);
                break;
            case R.id.find_pass:
                luancherLogin(ForgetActivity.class);
                break;
            case R.id.get_credit_code:
                if(recLen==60) {
                    get_credit_code.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.minutiues));
                    try {
                        startTask();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(recLen <0){
                    recLen=60;
                    get_credit_code.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.minutiues));
                    try {
                        startTask();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                getSendCode();
                break;
            case R.id.cancle:
                etPhone.setText("");
                break;
            case R.id.btn_register:
                mPhone = etPhone.getText().toString();
                mPswd = et_password.getText().toString();
                mCode = et_credit_num.getText().toString();
                mPswagain = etPasswordAgain.getText().toString();
                if (mPhone == null) {
                    ToastUtil.showToast(this, "请先填写账号，获取验证码");
                    return;
                }
                if (mPhone.equals("")) {
                    ToastUtil.showToast(this, "账号不能为空");
                    return;
                }
                if(mPhone.matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$")){

                }else{
                    ToastUtil.showToast(this, "输入的手机号格式不对");
                    return;
                }
                if (mPswd.equals("")) {
                    ToastUtil.showToast(this, "请输入密码");
                    return;
                }
                if (mPswd.matches("[0-9A-Za-z_]*")) {
                    //Log.e(TAG, " 账号是否通过？ --> " + mPswd.matches("[0-9A-Za-z_]*"));
                } else {
                    ToastUtil.showToast(this, "密码只能输入字母、数字、下划线");
                    return;
                }
                if (mPswd.length() < 6) {
                    ToastUtil.showToast(this, "密码至少为6位数");
                    return;
                }
                if (mPswagain.equals("")) {
                    ToastUtil.showToast(this, "请输入密码");
                    return;
                }
                if (mPswagain.matches("[0-9A-Za-z_]*")) {
                    //Log.e(TAG, " 账号是否通过？ --> " + mPswd.matches("[0-9A-Za-z_]*"));
                } else {
                    ToastUtil.showToast(this, "密码只能输入字母、数字、下划线");
                    return;
                }
                if (mPswagain.length() < 6) {
                    ToastUtil.showToast(this, "密码至少为6位数");
                    return;
                }
                if (mCode.equals("")) {
                    ToastUtil.showToast(this, "请输入验证码");
                    return;
                }
                addUser(mPhone,mPswd,mCode);
                break;
        }

    }

    public void startTask() throws InterruptedException {
        if (task==null) {
            timer = new Timer();
            task =  new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recLen--;
                            get_credit_code.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.credit_time,recLen));
                            if (recLen < 0) {
                                timer.cancel();
                                task=null;
                                timer=null;
                                get_credit_code.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.get_credit));//倒计时到0隐藏字体
                            }
                        }
                    });
                }
            };
        }
        timer.schedule(task, 1000, 1000);
    }

    public void luancherLogin(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(MyApplication.getInstance().mContext, clazz);
        startActivity(intent);
    }

    public void getSendCode(){
        UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
        Call<SendCodeBean> call = userService.getSendCode(etPhone.getText().toString().trim());
        call.enqueue(new Callback<SendCodeBean>() {
            @Override
            public void onResponse(Call<SendCodeBean> call, Response<SendCodeBean> response) {
                Log.e("ttttttt",response.body().toString());
                et_credit_num.setText(response.body().getData().getCode());
            }

            @Override
            public void onFailure(Call<SendCodeBean> call, Throwable t) {

            }
        });
    }

    public void addUser(String phone ,String psw,String code){
        UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);

        String mdPsw=MD5Utils.MD5Encode(psw,"utf-8");

        SpConfig.getInstance().putString("psw",psw);
        Call<AddUserResult> call =userService.addRegisteryUser(new AddUser(phone, SpConfig.getInstance().getString("mdpsw"),code));
        call.enqueue(new Callback<AddUserResult>() {
            @Override
            public void onResponse(Call<AddUserResult> call, Response<AddUserResult> response) {
                if(response.body().getCode()==200) {
                    ToastUtil.showToast(mContext, "注册成功");
                    luancherLogin(LoginActivity.class);
                }else{
                    ToastUtil.showToast(mContext, "用户已存在");
                }
            }

            @Override
            public void onFailure(Call<AddUserResult> call, Throwable t) {

            }
        });
    }

}
