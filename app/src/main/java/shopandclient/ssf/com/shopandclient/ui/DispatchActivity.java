package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CommentStarAdapter;
import shopandclient.ssf.com.shopandclient.adapter.ImagePickerAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.CommentStarBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/17.
 */
public class DispatchActivity extends BaseActivity implements BaseBiz, CommentStarAdapter.OnitemClick, ImagePickerAdapter.OnRecyclerViewItemClickListener,ImagePickerAdapter.OnDeteleCancelClick, Observer {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.rv_start)
    RecyclerView rvStart;
    ArrayList<CommentStarBean> arrayList = new ArrayList<>();
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    private CommentStarAdapter csa;
    private int maxImgCount = 5;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private TokenManager tokenManager;
    ; //当前选择的所有图片

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_dispatch_comment;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void initView() {
        super.initView();
        initImagePicker();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.dispath_comment));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        ivScope.setVisibility(View.INVISIBLE);
        tvSave.setText("发布");
        arrayList.add(new CommentStarBean(false));
        arrayList.add(new CommentStarBean(false));
        arrayList.add(new CommentStarBean(false));
        arrayList.add(new CommentStarBean(false));
        arrayList.add(new CommentStarBean(false));
        rvStart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        csa = new CommentStarAdapter(this, arrayList);
        csa.setOnitemClickLintener(this);
        rvStart.setAdapter(csa);
        adapter = new ImagePickerAdapter(maxImgCount, this, selImageList);
        adapter.setOnItemClickListener(this);
        adapter.setOnCancelClickListener(this);
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhoto.setHasFixedSize(true);
        rvPhoto.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        tokenManager = TokenManager.newInstance();
        tokenManager.registerObserver(this);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0) {
            arrayList.get(0).setCheck(true);
            arrayList.get(1).setCheck(false);
            arrayList.get(2).setCheck(false);
            arrayList.get(3).setCheck(false);
            arrayList.get(4).setCheck(false);
            tvComment.setText("非常差");
        } else if (position == 1) {
            arrayList.get(0).setCheck(true);
            arrayList.get(1).setCheck(true);
            arrayList.get(2).setCheck(false);
            arrayList.get(3).setCheck(false);
            arrayList.get(4).setCheck(false);
            tvComment.setText("差");
        } else if (position == 2) {
            arrayList.get(0).setCheck(true);
            arrayList.get(1).setCheck(true);
            arrayList.get(2).setCheck(true);
            arrayList.get(3).setCheck(false);
            arrayList.get(4).setCheck(false);
            tvComment.setText("一般");
        } else if (position == 3) {
            arrayList.get(0).setCheck(true);
            arrayList.get(1).setCheck(true);
            arrayList.get(2).setCheck(true);
            arrayList.get(3).setCheck(true);
            arrayList.get(4).setCheck(false);
            tvComment.setText("好");
        } else {
            arrayList.get(0).setCheck(true);
            arrayList.get(1).setCheck(true);
            arrayList.get(2).setCheck(true);
            arrayList.get(3).setCheck(true);
            arrayList.get(4).setCheck(true);
            tvComment.setText("非常好");
        }
        csa.notifyDataSetChanged();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setMultiMode(true);                      //多选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onCancelClick(View v, int position, int max) {
        selImageList.remove(position);
        Log.e("ttttttttt","-------------"+max);
      //  maxImgCount=maxImgCount-max;
    }

    @Override
    public void onItemClick(View view, int position, int size) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                if(size<6) {
                    List<String> names = new ArrayList<>();
                    names.add("拍照");
                    names.add("相册");
                    showDialog(new SelectDialog.SelectDialogListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0: // 直接调起相机
                                    //打开选择,本次允许选择的数量
                                    ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                    Intent intent = new Intent(DispatchActivity.this, ImageGridActivity.class);
                                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                    startActivityForResult(intent, REQUEST_CODE_SELECT);
                                    break;
                                case 1:
                                    //打开选择,本次允许选择的数量
                                    ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                    Intent intent1 = new Intent(DispatchActivity.this, ImageGridActivity.class);
                                    startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }, names);
                }
                break;
            default:
            /*    //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS,true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);*/
                break;
        }
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
