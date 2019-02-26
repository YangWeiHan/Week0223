package com.example.test0223.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test0223.R;
import com.example.test0223.data.adapter.GoodsAdapter;
import com.example.test0223.data.adapter.NewsAdapter;
import com.example.test0223.data.bean.BannerBean;
import com.example.test0223.data.bean.GoodsBean;
import com.example.test0223.data.bean.NewsBean;
import com.example.test0223.di.component.DaggerMainComponent;
import com.example.test0223.mvp.contract.MainContract;
import com.example.test0223.mvp.presenter.MainPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/23/2019 11:39
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.btn_menu)
    Button btnMenu;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.drawerLayout_menu)
    DrawerLayout drawerLayoutMenu;
    List<String> list_banner = new ArrayList<String>();
    @BindView(R.id.fly_banner)
    FlyBanner flyBanner;
    @BindView(R.id.nine_recyclerview)
    RecyclerView nineRecyclerview;
    @BindView(R.id.goods_recyclerview)
    RecyclerView goodsRecyclerview;
    @BindView(R.id.btn_shopcar)
    TextView btnShopcar;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
        //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.requestBanerData();
        mPresenter.requestGoodsData();
        mPresenter.requestNewsData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void shopBannerData(BannerBean bannerBean) {

        List<BannerBean.ResultBean> resultBeans = bannerBean.getResult();
        Toast.makeText(this, resultBeans.get(0).getTitle(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < resultBeans.size(); i++) {
            String imageUrl = resultBeans.get(i).getImageUrl();
            list_banner.add(imageUrl);
        }
        flyBanner.setImagesUrl(list_banner);
    }

    @Override
    public void shopGoodsData(GoodsBean goodsBean) {
        List<GoodsBean.ResultBean.MlssBean> mlss = goodsBean.getResult().getMlss();
        List<GoodsBean.ResultBean.PzshBean> pzsh = goodsBean.getResult().getPzsh();
        List<GoodsBean.ResultBean.RxxpBean> rxxp = goodsBean.getResult().getRxxp();
        if (mlss != null) {
            if (mlss.size() <= 0) {
                return;
            } else {
                for (int i = 0; i < mlss.size(); i++) {
                    String name = mlss.get(i).getName();
                    Log.d("MainActivity", name);
                    //得到数据源
                    List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = mlss.get(i).getCommodityList();
                    //布局
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    goodsRecyclerview.setLayoutManager(layoutManager);
                    GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.googs_item, commodityList);
                    goodsRecyclerview.setAdapter(goodsAdapter);
                }
            }

        }

    }

    @Override
    public void newsData(NewsBean newsBean) {
        List<NewsBean.ResultsBean> newsBeanResults = newsBean.getResults();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 5, GridLayoutManager.VERTICAL, false);
        nineRecyclerview.setLayoutManager(gridLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(R.layout.news_item, newsBeanResults);
        nineRecyclerview.setAdapter(newsAdapter);
    }



    @OnClick({R.id.btn_menu, R.id.btn_shopcar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_menu:
                drawerLayoutMenu.openDrawer(Gravity.LEFT);
                break;
            case R.id.btn_shopcar:

                break;
        }
    }
}
