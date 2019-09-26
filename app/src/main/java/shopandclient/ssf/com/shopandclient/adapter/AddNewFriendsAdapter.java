package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.ConfrimParams;
import shopandclient.ssf.com.shopandclient.entity.FriendListBean;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.util.ActionSheetDialog;

import java.util.ArrayList;

public class AddNewFriendsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FriendListBean.DataBean.NewFriendBean> arrayList;
    private OnitemClick onitemClick;   //定义点击事件接口
    public AddNewFriendsAdapter(Context context, ArrayList<FriendListBean.DataBean.NewFriendBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    //定义设置点击事件监听的方法
    public void setOnitemClickLintener(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHodler hodler;
        if(convertView==null){
            hodler= new ViewHodler();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_conconat_new_friends,parent,false);
            hodler.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            hodler.rl_content=(RelativeLayout) convertView.findViewById(R.id.rl_content);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        hodler.tv_name.setText(arrayList.get(position).getFriendName());
        hodler.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(context)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("拒绝", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                       setState(arrayList.get(position).getFriendID(),2);
                                    }
                                })
                        .addSheetItem("同意", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        setState(arrayList.get(position).getFriendID(),1);
                                    }
                                }).show();
            }
        });
        return convertView;
    }
    class ViewHodler {
        private RelativeLayout rl_content;
        private TextView tv_name;
    }

    public void setState(int friendId,int type){
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.comfrimFriendState(new ConfrimParams(friendId,type));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    if(onitemClick!=null){
                        onitemClick.onItemClick();
                    }
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

}
