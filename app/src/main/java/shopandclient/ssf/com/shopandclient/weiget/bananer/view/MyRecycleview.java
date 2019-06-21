package shopandclient.ssf.com.shopandclient.weiget.bananer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by zhg on 2019/6/11.
 */
public class MyRecycleview extends RecyclerView {
    public MyRecycleview(Context context) {
        super(context);
    }

    public MyRecycleview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return true;
    }
}
