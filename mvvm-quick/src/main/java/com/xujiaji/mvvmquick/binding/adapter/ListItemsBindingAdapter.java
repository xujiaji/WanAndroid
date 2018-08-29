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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xujiaji.mvvmquick.R;

import java.util.List;
import java.util.Objects;

/**
 * author: xujiaji
 * created on: 2018/6/13 16:21
 * description:
 */
public class ListItemsBindingAdapter {
    @BindingAdapter("bind:items")
    public static <T, V extends BaseViewHolder> void setItems(RecyclerView recyclerView, List<T> items) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (!(adapter instanceof BaseQuickAdapter)) return;
        BaseQuickAdapter<T, V> ad = (BaseQuickAdapter<T, V>) adapter;
        ad.setNewData(items);
    }

    @BindingAdapter("bind:hideLine")
    public static void showLine(RecyclerView recyclerView, boolean hideLine) {
        if (hideLine) {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecorationAt(0);
            }
        } else {
            DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.shape_line)));
            recyclerView.addItemDecoration(itemDecoration);
        }
    }
}

