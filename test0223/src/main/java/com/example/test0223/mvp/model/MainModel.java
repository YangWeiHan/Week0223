package com.example.test0223.mvp.model;

import android.app.Application;

import com.example.test0223.data.apils.Apils;
import com.example.test0223.data.bean.NewsBean;
import com.example.test0223.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.test0223.mvp.contract.MainContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


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
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<ResponseBody> requestBannerInfo() {
        /*
        * 轮播图
        * */
        return mRepositoryManager.obtainRetrofitService(ApiService.class).requestBannerInfo();
    }

    @Override
    public Observable<ResponseBody> requestGoodsInfo() {
        return mRepositoryManager.obtainRetrofitService(ApiService.class).requestGoodsInfo();
    }

    @Override
    public void setNewsData(OnCallBack onCallBack) {
        OkGo.<String>get(Apils.IMAGE_URL)
                .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String responesNewsData = response.body().toString();
                            Gson gson  =new Gson();
                            NewsBean newsBean = gson.fromJson(responesNewsData, NewsBean.class);
                            onCallBack.getData(newsBean);
                        }
        });
    }


}