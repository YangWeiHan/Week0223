package com.example.test0223.mvp.presenter;

import android.app.Application;

import com.example.test0223.data.bean.BannerBean;
import com.example.test0223.data.bean.GoodsBean;
import com.example.test0223.data.bean.NewsBean;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.ResponseBody;

import javax.inject.Inject;

import com.example.test0223.mvp.contract.MainContract;


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
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void requestBanerData(){
        Observable<ResponseBody> bannerInfo = mModel.requestBannerInfo();
        bannerInfo.observeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String resposneBannerData = responseBody.string().toString();
                        Gson gson = new Gson();
                        BannerBean bannerBean = gson.fromJson(resposneBannerData, BannerBean.class);
                        mRootView.shopBannerData(bannerBean);

                    }
                });
    }
    public void requestGoodsData(){
        Observable<ResponseBody> goodsInfo = mModel.requestGoodsInfo();
        goodsInfo.observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String responseGoodsData = responseBody.string().toString();
                        Gson gson = new Gson();
                        GoodsBean goodsBean = gson.fromJson(responseGoodsData, GoodsBean.class);
                        mRootView.shopGoodsData(goodsBean);
                    }
                });
    }
    public void requestNewsData(){
        mModel.setNewsData(new MainContract.Model.OnCallBack() {
            @Override
            public void getData(NewsBean newsBean) {
                mRootView.newsData(newsBean);
            }
        });
    }
}
