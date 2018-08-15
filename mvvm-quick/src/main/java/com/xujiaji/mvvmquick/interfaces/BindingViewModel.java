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

package com.xujiaji.mvvmquick.interfaces;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;

/**
 * author: xujiaji
 * created on: 2018/7/4 23:19
 * description:
 */
public interface BindingViewModel<B extends ViewDataBinding, VM extends AndroidViewModel> {
    /**
     * 实例化Binding后调用该方法
     */
    void onBinding(B binding);


    /**
     * 实例化ViewModel后调用该方法
     */
    void onObserveViewModel(VM viewModel);
}
