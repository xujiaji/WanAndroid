package com.xujiaji.wanandroid.module.set;

import android.app.Activity;
import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.xujiaji.wanandroid.base.BaseViewModel;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.util.FileUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/9/1 9:11
 * description:
 */
@Singleton
public class SettingsViewModel extends BaseViewModel {

    public final ObservableField<String> cacheSize = new ObservableField<>();
    public final ObservableInt pluginNum = new ObservableInt();


    @Inject
    public SettingsViewModel(@NonNull Application application) {
        super(application);
    }

    public void cleanCache(View view) {
        view.setEnabled(false);
        new Thread() {
            @Override
            public void run() {
                FileUtil.deleteCache(view.getContext());
                ((Activity) view.getContext()).runOnUiThread(() -> {
                    view.setEnabled(true);
                    cacheSize.set(FileUtil.getCacheSizeStr(view.getContext()));
                    ToastHelper.success("已清理缓存");
                });
            }
        }.start();

    }

    public void cleanPlugin(View view) {
        for (PluginInfo info : RePlugin.getPluginInfoList()) {
            RePlugin.uninstall(info.getName());
        }
        pluginNum.set(0);
        ToastHelper.success("已清除，重启生效");
    }
}
