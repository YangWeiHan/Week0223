package com.example.test0223.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.example.test0223.mvp.contract.MainContract;
import com.example.test0223.mvp.model.MainModel;


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
@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);
}