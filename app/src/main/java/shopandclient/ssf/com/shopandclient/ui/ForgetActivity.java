package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

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

    @OnClick({R.id.rl_btn_back, R.id.tv_getcredit})
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
                break;
        }
    }
}
