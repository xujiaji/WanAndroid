package com.xujiaji.wanandroid.module.main;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.xujiaji.mvvmquick.util.VersionUtil;
import com.xujiaji.wanandroid.BuildConfig;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseViewModel;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.bean.VersionBean;
import com.xujiaji.wanandroid.util.FileUtil;
import com.xujiaji.wanandroid.util.UpdateAppHttpUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/31 10:42
 * description:
 */
@Singleton
public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Result<VersionBean>> getObservableVersionData() {
        return net.get().getUpdateVersion();
    }

    public void versionCheck(Activity activity, VersionBean versionBean) {
        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(activity)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(BuildConfig.UPDATE_VERSION_URL)
                .setThemeColor(ContextCompat.getColor(activity, R.color.colorAccent))
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(FileUtil.getDiskCacheDir(activity).getAbsolutePath())
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        updateAppBean
                                //（必须）是否更新Yes,No
                                .setUpdate(versionBean.getVersionCode() > VersionUtil.getVersionCode(activity) ? "Yes" : "No")
                                //（必须）新版本号，
                                .setNewVersion(versionBean.getVersionName())
                                //（必须）下载地址
                                .setApkFileUrl(versionBean.getApkUrl())
                                //测试下载路径是重定向路径
//                                    .setApkFileUrl("http://openbox.mobilem.360.cn/index/d/sid/3282847")
//                                    .setUpdateDefDialogTitle(String.format("AppUpdate 是否升级到%s版本？", newVersion))
                                .setUpdateLog(versionBean.getUpdateInfo())
                                //大小，不设置不显示大小，可以不设置
                                .setTargetSize(versionBean.getFileSize())
                                //是否强制更新，可以不设置
                                .setConstraint(versionBean.isConstraint());

                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {

                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {

                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {

                    }
                });
    }
}
