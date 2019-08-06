package shopandclient.ssf.com.shopandclient.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import org.json.JSONArray;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.util.GetJsonDataUtil;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/6.
 */
public class AddintoAddressActivity extends BaseActivity implements BaseBiz,View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private static boolean isLoaded = false;
    private TextView tv_address_select;
    private RelativeLayout rlAction;
    private ImageView ivBack;
    private TextView tvCenterTitle;
    private ImageView ivScope;
    private TextView tv_save;
    private RelativeLayout rl_btn_back;
    private EditText et_host;
    private EditText et_phone;
    private EditText et_detail_address;
    private Switch btn_switch;
    private String name;
    private String mPhone;
    private String detailAress;
    private String address;
    private int defual=0;
    private int type;
    private Intent intent;
    private int id;
    private int ensuretype;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }
    private void initOptionPicker() {//条件选择器初始化


    }

    @Override
    protected void initView() {
        super.initView();
        initJsonData();
        intent = getIntent();
        type = intent.getIntExtra("type",0);
        id = intent.getIntExtra("id",0);
        ensuretype = intent.getIntExtra("ensuretype",0);
        rlAction = (RelativeLayout) findViewById(R.id.rl_action);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvCenterTitle = (TextView)findViewById(R.id.tv_center_title);
        tv_address_select=(TextView)findViewById(R.id.tv_address_select);
        ivScope = (ImageView) findViewById(R.id.iv_scope);
        tv_save = (TextView) findViewById(R.id.tv_save);
        rl_btn_back = (RelativeLayout) findViewById(R.id.rl_btn_back);
        et_host = (EditText) findViewById(R.id.et_host);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_detail_address = (EditText) findViewById(R.id.et_detail_address);
        btn_switch = (Switch) findViewById(R.id.btn_switch);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.add_reciviver_address));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        ivScope.setVisibility(View.INVISIBLE);
        tv_save.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.save_address));
        tv_address_select.setOnClickListener(this);
        rl_btn_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        btn_switch.setOnCheckedChangeListener(this);
         if(type==2){
             getAddressInfo(id);
         }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.tv_address_select:
                showPickerView();
                break;
            case R.id.tv_save:
                name = et_host.getText().toString();
                mPhone = et_phone.getText().toString();
                detailAress = et_detail_address.getText().toString();
                address = tv_address_select.getText().toString();
                if (name == null) {
                    ToastUtil.showToast(this, "请先填写收货人姓名");
                    return;
                }
                if (name.equals("")) {
                    ToastUtil.showToast(this, "请先填写收货人姓名");
                    return;
                }
                if(mPhone.matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$")){

                }else{
                    ToastUtil.showToast(this, "输入的手机号格式不对");
                    return;
                }
                if (address == null) {
                    ToastUtil.showToast(this, "请先填写地址");
                    return;
                }
                if (address.equals("")) {
                    ToastUtil.showToast(this, "请先填写地址");
                    return;
                }
                if (detailAress == null) {
                    ToastUtil.showToast(this, "请先填写详细地址");
                    return;
                }
                if (detailAress.equals("")) {
                    ToastUtil.showToast(this, "请先填写详细地址");
                    return;
                }
                if(type==1) {
                    addAddress(name, address, detailAress, mPhone, defual);
                }else{
                   updateAddress(id,name, address, detailAress, mPhone, defual);
                }
                break;
        }


    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        Toast.makeText(AddintoAddressActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    //Toast.makeText(AddintoAddressActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(AddintoAddressActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                final String tx = opt1tx + opt2tx + opt3tx;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_address_select.setText(tx);
                    }
                });
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            defual=1;
            if(type==2){
                setDefaultAddress(id);
            }
        }else{
            defual=0;
            if(type==2){
                setDefaultAddress(id);
            }
        }
    }

    public void addAddress(final String username, final String address, final String addressinfo, final String phone, int type){
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call = service.postAddAddress(new AddAddressParams(username,address,addressinfo,phone,type));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    ToastUtil.showToast(MyApplication.getInstance().mContext,response.body().getResult());
                    if(ensuretype==0) {
                        openActivity(AddressActivity.class);
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra("name",username);
                        intent.putExtra("address",address);
                        intent.putExtra("addressinfo",addressinfo);
                        intent.putExtra("phone",phone);
                        setResult(RESULT_OK,intent);
                    }
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void getAddressInfo(int id){
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<AddressInfo> call=service.getAddressInfo(id);
        call.enqueue(new Callback<AddressInfo>() {
            @Override
            public void onResponse(Call<AddressInfo> call, Response<AddressInfo> response) {
                if(response.body().getCode()==200){
                    et_host.setText(response.body().getData().getUserName());
                    et_phone.setText(response.body().getData().getPhone());
                    tv_address_select.setText(response.body().getData().getAddress());
                    et_detail_address.setText(response.body().getData().getAddressInfo());
                    if(response.body().getData().getIsDefault()==1){
                        btn_switch.setChecked(true);
                        defual=1;
                    }else{
                        btn_switch.setChecked(false);
                        defual=0;
                    }
                }
            }

            @Override
            public void onFailure(Call<AddressInfo> call, Throwable t) {

            }
        });
    }

    public void updateAddress(int id,String username,String address,String addressinfo,String phone,int type){
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call=service.postUpdateAddress(new UpdateParams(id,username,address,addressinfo,phone,type));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    ToastUtil.showToast(MyApplication.getInstance().mContext,response.body().getResult());
                    openActivity(AddressActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void setDefaultAddress(int id){
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call=service.setDefaultAddress(id);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    ToastUtil.showToast(MyApplication.getInstance().mContext,response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

}
