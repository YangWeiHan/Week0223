package com.example.test0223.mvp.contract;

import com.example.test0223.data.bean.BannerBean;
import com.example.test0223.data.bean.GoodsBean;
import com.example.test0223.data.bean.NewsBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

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
        public interface MainContract {
            //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
            interface View extends IView {
                void shopBannerData(BannerBean s);
                void shopGoodsData(GoodsBean goodsBean);
                void newsData(NewsBean newsBean);
            }

            //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
            interface Model extends IModel {
                Observable<ResponseBody> requestBannerInfo();
                Observable<ResponseBody> requestGoodsInfo();
                void setNewsData(OnCallBack onCallBack);
                interface OnCallBack{
                    void getData(NewsBean newsBean);
                }
    }
}
