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

package com.xujiaji.mvvmquick.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xujiaji.mvvmquick.R;

import java.lang.reflect.Method;
import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/7/4 22:23
 * description:
 */
public abstract class MQQuickAdapter<T, B extends ViewDataBinding>
        extends BaseQuickAdapter<T, MQViewHolder<B>>
        implements BaseQuickAdapter.RequestLoadMoreListener {

    private boolean loaded = false;

    public MQQuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    public MQQuickAdapter(@Nullable List<T> data) {
        super(data);
    }

    public MQQuickAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        if (layoutResId != mLayoutResId) return super.getItemView(layoutResId, parent);

        B binding = DataBindingUtil.inflate(
                mLayoutInflater,
                layoutResId,
                parent,
                false);
        onBinding(binding);
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    /**
     * 已初始化Binding
     */
    protected abstract void onBinding(B binding);

    /**
     * 绑定RecyclerView
     *
     * @param load 是否需要加载
     */
    public void bindToRecyclerView(RecyclerView recyclerView, boolean load) {
        super.bindToRecyclerView(recyclerView);

        if (load)
            this.setOnLoadMoreListener(this, recyclerView);
    }

    @Override
    public void onLoadMoreRequested() {
        if (loaded) {
            this.loadMoreEnd();
            return;
        }
        MQViewModel viewModel = getViewModel();
        if (viewModel == null) return;
        viewModel.onListLoad(viewModel.getLoadOffset());
    }

    @Nullable
    protected MQViewModel getViewModel() {
        return null;
    }

    public void setLoaded() {
        loaded = true;
    }
}
