package com.example.test0223.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.test0223.R;
import com.example.test0223.data.bean.GoodsBean;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX,BaseViewHolder> {
    public GoodsAdapter(int layoutResId, @Nullable List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.ResultBean.MlssBean.CommodityListBeanXX item) {
        helper.setText(R.id.mlss_tv_name,item.getCommodityName());
        helper.setText(R.id.mlss_tv_price,item.getPrice()+"");
        ImageView pzsh_icon = helper.getView(R.id.mlss_iv_icon);
        Glide.with(mContext).load(item.getMasterPic()).into(pzsh_icon);
    }
}
