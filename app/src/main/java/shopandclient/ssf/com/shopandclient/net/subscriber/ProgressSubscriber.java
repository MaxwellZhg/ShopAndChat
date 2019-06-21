package shopandclient.ssf.com.shopandclient.net.subscriber;

import android.content.Context;
import android.widget.Toast;
import rx.Subscriber;
import shopandclient.ssf.com.shopandclient.entity.BaseBean;
import shopandclient.ssf.com.shopandclient.net.progress.ProgressCancelListener;
import shopandclient.ssf.com.shopandclient.net.progress.ProgressDialogHandler;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by zhg on 2019/4/15.
 */
public class ProgressSubscriber <T extends BaseBean> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
//    private final MyLoadingDialog dialog;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
//        TextBuilder textBuilder = new TextBuilder();
//        textBuilder.setText("AllSale");
//        dialog = new MyLoadingDialog(context);
//        dialog.setLoadingBuilder(textBuilder)
//                .setLoadingColor(Color.RED)//颜色
//                .setHintTextSize(16) // 设置字体大小 dp
//                .setHintTextColor(Color.GRAY)  // 设置字体颜色
//                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
//                .setDialogBackgroundColor(Color.WHITE); // 设置背景色，默认白色

    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
//        dialog.show();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
//        dialog.dismiss();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e);
        }

        dismissProgressDialog();
//        dialog.dismiss();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
//            if (TextUtils.isEmpty(t.getResCode())) {
////                throw new ResultCodeException("error_token");
//                ToastUtil.shortToast(context, "账号已过期，请重新登录");
//                context.startActivity(new Intent(context, LoginActivity.class));
//                SpConfig.getInstance().putString(Constants.SESSIONID_STRING, "");
//            }
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
