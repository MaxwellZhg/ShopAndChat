package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.DemoHelper;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.User;
import shopandclient.ssf.com.shopandclient.entity.UserLoginBean;
import shopandclient.ssf.com.shopandclient.im.db.DemoDBManager;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.UserService;
import shopandclient.ssf.com.shopandclient.util.*;

/**
 * Created by zhg on 2019/5/27.
 */
public class LoginActivity extends BaseActivity implements BaseBiz, TextWatcher,View.OnClickListener, Observer {

  public static final String TAG=LoginActivity.class.getSimpleName();
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_pass_tips)
    TextView tvPassTips;
    @BindView(R.id.rl_logo)
    RelativeLayout rlLogo;
    @BindView(R.id.rl_add_tips)
    TextView rlAddTips;
    @BindView(R.id.et_phone)
    EditText etPhone;
    Button btnLogin;
    private String mPhone;
    private String mPswd;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    public void luancherLogin(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(MyApplication.getInstance().mContext, clazz);
        startActivity(intent);
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
        ButterKnife.bind(this);
        etPassword.addTextChangedListener(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.white), 0);
    }


    @OnClick({R.id.tv_register, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                luancherLogin(RegisterActivity.class);
                break;
            case R.id.tv_forget:
                luancherLogin(ForgetActivity.class);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() < 6) {
            tvPassTips.setVisibility(View.VISIBLE);
        } else {
            tvPassTips.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        if (DemoHelper.getInstance().getCurrentUsernName() != null) {
            etPhone.setText(DemoHelper.getInstance().getCurrentUsernName());
        }
    }


    @Override
    public void onClick(View v) {
        mPhone = etPhone.getText().toString();
        mPswd = etPassword.getText().toString();
        switch (v.getId()){
            case R.id.btn_login:
                if (!EaseCommonUtils.isNetWorkConnected(this)) {
                    Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
                    return;
                }
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
                // After logout，the DemoDB may still be accessed due to async callback, so the DemoDB will be re-opened again.
                // close it before login to make sure DemoDB not overlap
                DemoDBManager.getInstance().closeDB();

                // reset current user name before login
                DemoHelper.getInstance().setCurrentUserName(mPhone);
                UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
                Call<UserLoginBean> call = userService.postUserLogin(new User(etPhone.getText().toString().trim(),MD5Utils.getMd5Str(mPswd),2));
                call.enqueue(new Callback<UserLoginBean>() {
                    @Override
                    public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                        if(response.body().getCode()==200) {
                            SpConfig.getInstance().putBool(Constants.ISLOGIN, true);
                            SpConfig.getInstance().putString(Constants.TOKEN,response.body().getData().getUserToken());
                            SpConfig.getInstance().putString(Constants.USERNAME,response.body().getData().getUserName());
                            SpConfig.getInstance().putInt(Constants.USERID,response.body().getData().getUserID());
                             loginIm(response.body().getData().getGuidNO(),mPswd);
                        }else{
                            ToastUtil.showToast(mContext,response.body().getResult().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLoginBean> call, Throwable t) {

                    }
                });
                break;
        }
    }

    public void loginIm(String name,String  psw){
        EMClient.getInstance().login(name, psw, new EMCallBack() {

            @Override
            public void onSuccess() {
                Log.d(TAG, "login: onSuccess");


                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                // update current user's display name for APNs
                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
                        MyApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }
                // get user's info (this should be get from App's server or 3rd party service)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                Intent intent = new Intent();
                intent.putExtra("username",SpConfig.getInstance().getString(Constants.USERNAME));
                setResult(RESULT_OK,intent);
                tokenManager =  TokenManager.newInstance();
                tokenManager.registerObserver(LoginActivity.this);
                tokenManager.TimeSchecher();
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d(TAG, "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                Log.d(TAG, "login: onError: " + code);
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void registerIm(String name,String pass){
        new Thread(new Runnable() {
            public void run() {
                try {
                    // call method in SDK
                    EMClient.getInstance().createAccount(name, pass);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            DemoHelper.getInstance().setCurrentUserName(name);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                            luancherLogin(LoginActivity.class);
                            finish();
                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            int errorCode=e.getErrorCode();
                            if(errorCode== EMError.NETWORK_ERROR){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ALREADY_EXIST){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_AUTHENTICATION_FAILED){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ILLEGAL_ARGUMENT){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name),Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.EXCEED_SERVICE_LIMIT){
                                Toast.makeText(LoginActivity.this, getResources().getString(R.string.register_exceed_service_limit), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
