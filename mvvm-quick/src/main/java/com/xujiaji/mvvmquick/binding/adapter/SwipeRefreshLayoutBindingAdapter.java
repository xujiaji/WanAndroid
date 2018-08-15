/*
 *    Copyright 2018 XuJiaji
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xujiaji.mvvmquick.binding.adapter;

import android.databinding.BindingAdapter;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;

import com.xujiaji.mvvmquick.base.MQViewModel;

import java.lang.ref.WeakReference;


public class SwipeRefreshLayoutBindingAdapter {

    private static final int CODE = 1001;

    private static class Handler extends android.os.Handler {

        private WeakReference<SwipeRefreshLayout> wr;

        private Handler(SwipeRefreshLayout srl) {
            wr = new WeakReference<>(srl);
        }

        @Override
        public void handleMessage(Message msg) {
            if (wr.get() == null) return;
            if (msg.what == CODE) {
                if (wr.get().isRefreshing()) {
                    wr.get().setRefreshing(false);
                }
            }
        }
    }

    /**
     * Reloads the data when the pull-to-refresh is triggered.
     * <p>
     * Creates the {@code android:onRefresh} for a {@link SwipeRefreshLayout}.
     */
    @BindingAdapter("android:onRefresh")
    public static <T extends MQViewModel> void setSwipeRefreshLayoutOnRefreshListener(final SwipeRefreshLayout view,
                                                                                      final T viewModel) {
        final Handler handler = new Handler(view);

        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.removeMessages(CODE);
                viewModel.onListRefresh();
                handler.sendEmptyMessageDelayed(CODE, viewModel.timeout() * 1000);
            }
        });
    }

}
