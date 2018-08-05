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

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.interfaces.BindingViewModel;
import com.xujiaji.mvvmquick.util.ClassUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * author: xujiaji
 * created on: 2018/7/2 14:23
 * description:
 */
public class MQActivity<B extends ViewDataBinding, VM extends AndroidViewModel> extends DaggerAppCompatActivity implements BindingViewModel<B, VM>
{

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected B binding;

    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initBinding();
        initViewModel();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel()
    {
        Class<VM> viewModelClass = ClassUtils.getViewModel(this);
        if (viewModelClass == null) return;
        final VM viewModel = ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass);
        this.viewModel = viewModel;
        onObserveViewModel(viewModel);
    }

    /**
     * 初始化Binding
     */
    private void initBinding()
    {
        binding = ClassUtils.getBinding(this, getLayoutInflater(), ((ViewGroup)findViewById(android.R.id.content)));
        if (binding != null)
            setContentView(binding.getRoot());
        onBinding(binding);
    }

    @Override
    public void onBinding(B binding)
    {

    }

    @Override
    public void onObserveViewModel(VM viewModel)
    {

    }
}
