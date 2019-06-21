package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhg on 2019/5/27.
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_count;
    private int recLen = 5;//跳过倒计时提示5秒
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_count.setOnClickListener(this);
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒               */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
               luancherLogin();
            }
        }, 5000);//延迟5S后发送handler信息

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    tv_count.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.jump_over,recLen));
                    if (recLen < 0) {
                        timer.cancel();
                        tv_count.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };
    public void luancherLogin(){
        Intent intent =new Intent();
        intent.setClass(MyApplication.getInstance().mContext,MainActivity.class);
        startActivity(intent);
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        luancherLogin();
    }
}
