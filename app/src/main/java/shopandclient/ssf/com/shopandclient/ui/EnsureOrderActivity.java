package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AddressAdapter;
import shopandclient.ssf.com.shopandclient.adapter.EnsureOrderAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/18.
 */
public class EnsureOrderActivity extends BaseActivity implements BaseBiz,View.OnClickListener {
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
    @BindView(R.id.lv_ensure_order)
    ListView lvEnsureOrder;
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<LimmitBuyBean.DataBean.BuyProBean> list;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_total_num)
    TextView tvTotalNum;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;
    @BindView(R.id.rl_ensure)
    RelativeLayout rlEnsure;
    private OptionsPickerView pvOptions;
    private ArrayList<String> reason = new ArrayList<>();
    private TextView tv_track_way;
    private int type;
    private int account;
    private int attr1;
    private int attr2;
    private String str;
    private TextView tv_recivier;
    private TextView tv_reciver_phone;
    private TextView tv_reciver_address;
    ArrayList<AddressBean.DataBean> listaddress;
    private EnsureOrderAdapter eoa;
    private View tips;
    private View no_addrwss_tips;
    private TextView add_address;
    private LinearLayout ll_addresss;
    private int id;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_ensure_order;
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
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    protected void initView() {
        super.initView();
        getNoLinkData();
        initNoLinkOptionsPicker();
        Intent intent =getIntent();
        type = intent.getIntExtra("type",0);

        if(type==1) {
            id = intent.getIntExtra("id",0);
            account = intent.getIntExtra("account", 0);
            attr1 = intent.getIntExtra("attr1", 0);
            attr2 = intent.getIntExtra("attr2", 0);
        }else {
            str = intent.getStringExtra("str");
        }
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.nav_black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.ensure_order));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        tips = LayoutInflater.from(this).inflate(R.layout.item_ensure_order_header, null);
        no_addrwss_tips = LayoutInflater.from(this).inflate(R.layout.item_ensure_order_no_address, null);
        View footer=LayoutInflater.from(this).inflate(R.layout.item_ensure_order_footer,null);
        tv_recivier = (TextView) tips.findViewById(R.id.tv_recivier);
        tv_reciver_phone = (TextView) tips.findViewById(R.id.tv_reciver_phone);
        tv_reciver_address = (TextView) tips.findViewById(R.id.tv_reciver_address);
        ll_addresss = (LinearLayout) tips.findViewById(R.id.ll_addresss);
        tv_track_way = (TextView) footer.findViewById(R.id.tv_track_way);
        tv_track_way.setOnClickListener(this);
        add_address = (TextView)no_addrwss_tips.findViewById(R.id.add_address);
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("type",1);
                intent.putExtra("ensuretype",1);
                intent.setClass(EnsureOrderActivity.this,AddintoAddressActivity.class);
                startActivityForResult(intent,1);
            }
        });
        ll_addresss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("type",2);
                intent.setClass(EnsureOrderActivity.this,AddressActivity.class);
                startActivityForResult(intent,1);
            }
        });
        getAddressList();
        lvEnsureOrder.addFooterView(footer);
        if(type==1){
            postlimmitBuyInfo(id,account,attr1,attr2);
        }else{
            str=str.substring(0,str.length()-1);
            postlimmitCartBuyInfo(str);
        }
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

   private void initNoLinkOptionsPicker() {
       pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
           @Override
           public void onOptionsSelect(int options1, int options2, int options3, View v) {
               final String tx = reason.get(options1);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       tv_track_way.setText(tx);
                   }
               });
           }
       })
               .setTitleText("选择快递")
               .setContentTextSize(20)//设置滚轮文字大小
               .setDividerColor(Color.LTGRAY)//设置分割线的颜色
               .setSelectOptions(0)//默认选中项
               .setBgColor(Color.WHITE)
               .setTitleBgColor(Color.WHITE)
               .setTitleColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
               .setCancelColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
               .setSubmitColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips))
               .setTextColorCenter(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
               .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
               .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
               .setOutSideColor(0x00000000) //设置外部遮罩颜色
               .setOutSideCancelable(false)
               .build();
       pvOptions.setPicker(reason);//二级选择器
   }
    private void getNoLinkData() {
        reason.add("免邮");
        reason.add("EMS");
        reason.add("四通一达");
    }


    @Override
    public void onClick(View v) {
        pvOptions.show();
    }

    public void getAddressList() {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<AddressBean> call = service.getAddressList();
        call.enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if (response.body().getCode() == 200) {
                    listaddress = response.body().getData();
                    if(listaddress.size()>0){
                        lvEnsureOrder.addHeaderView(tips);
                        tv_recivier.setText(listaddress.get(0).getUserName());
                        tv_reciver_address.setText(listaddress.get(0).getAddress()+listaddress.get(0).getAddressInfo());
                        tv_reciver_phone.setText(listaddress.get(0).getPhone());
                    }else{
                        lvEnsureOrder.addHeaderView(no_addrwss_tips);
                    }
                }
            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                lvEnsureOrder.removeHeaderView(no_addrwss_tips);
                getAddressList();
            }
            if(resultCode==2){
                int pos = data.getIntExtra("pos",0);
                tv_recivier.setText(listaddress.get(pos).getUserName());
                tv_reciver_address.setText(listaddress.get(pos).getAddress()+listaddress.get(pos).getAddressInfo());
                tv_reciver_phone.setText(listaddress.get(pos).getPhone());
            }
        }
    }
    public void postlimmitBuyInfo(int proid,int account,int L1,int L2) {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<LimmitBuyBean> call = service.limmitBuy(new LimitBuyParams(proid,account,L1,L2));
        call.enqueue(new Callback<LimmitBuyBean>() {
            @Override
            public void onResponse(Call<LimmitBuyBean> call, Response<LimmitBuyBean> response) {
                    if(response.body().getCode()==200){
                        list=response.body().getData().getBuyPro();
                        eoa = new EnsureOrderAdapter(MyApplication.getInstance().mContext, list);
                        lvEnsureOrder.setAdapter(eoa);
                        tvTotalNum.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.total_clothes, list.get(0).getListPro().size()));
                        tvTotalPrice.setText("总计：" + response.body().getData().getAllTotal());
                    }
            }

            @Override
            public void onFailure(Call<LimmitBuyBean> call, Throwable t) {

            }
        });
    }
    public void postlimmitCartBuyInfo(String str) {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<LimmitBuyBean> call=service.limmitCartBuy(new LimitCartBuyParams(str));
        call.enqueue(new Callback<LimmitBuyBean>() {
            @Override
            public void onResponse(Call<LimmitBuyBean> call, Response<LimmitBuyBean> response) {
                   if(response.body().getCode()==200){
                       list=response.body().getData().getBuyPro();
                       eoa = new EnsureOrderAdapter(MyApplication.getInstance().mContext, list);
                       lvEnsureOrder.setAdapter(eoa);
                       int num=0;
                       for(int i=0;i<list.size();i++){
                           for(int j=0;j<list.get(i).getListPro().size();j++){
                               num+=list.get(i).getListPro().get(j).getAmount();
                           }
                       }
                       tvTotalNum.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.total_clothes, num));
                       tvTotalPrice.setText("总计：" + response.body().getData().getAllTotal());
                   }
            }

            @Override
            public void onFailure(Call<LimmitBuyBean> call, Throwable t) {

            }
        });
    }
}
