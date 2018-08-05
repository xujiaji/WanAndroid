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
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xujiaji.mvvmquick.R;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/7/4 22:23
 * description:
 */
public abstract class MQQuickAdapter<T, B extends ViewDataBinding> extends BaseQuickAdapter<T, MQViewHolder<B>>
{

    public MQQuickAdapter(int layoutResId)
    {
        super(layoutResId);
    }

    public MQQuickAdapter(@Nullable List<T> data)
    {
        super(data);
    }

    public MQQuickAdapter(int layoutResId, @Nullable List<T> data)
    {
        super(layoutResId, data);
    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent)
    {
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
}
