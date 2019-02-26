package com.example.test0223.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.test0223.R;
import com.example.test0223.data.bean.NewsBean;

import java.util.List;

public class NewsAdapter extends BaseQuickAdapter<NewsBean.ResultsBean,BaseViewHolder> {
    public NewsAdapter(int layoutResId, @Nullable List<NewsBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.ResultsBean item) {
        ImageView pzsh_icon = helper.getView(R.id.new_icon);
        Glide.with(mContext).load(item.getUrl()).into(pzsh_icon);
    }
}
