package shopandclient.ssf.com.shopandclient.weiget.bananer;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import shopandclient.ssf.com.shopandclient.weiget.bananer.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }
}
