package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

/**
 * Created by zhg on 2019/5/27.
 */
public class LoginActivity extends BaseActivity implements BaseBiz, TextWatcher {


    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_pass_tips)
    TextView tvPassTips;

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
        StatusBarUtil.setColor(this,MyApplication.getInstance().mContext.getResources().getColor(R.color.white),0);
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
        }else{
            tvPassTips.setVisibility(View.INVISIBLE);
        }
    }
}
