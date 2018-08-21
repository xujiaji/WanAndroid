package com.xujiaji.wanandroid.module.main.fragment.openapis;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseRefreshClickChildViewModel;
import com.xujiaji.wanandroid.databinding.ItemOpenApiBinding;
import com.xujiaji.wanandroid.databinding.ItemOpenApiSectionBinding;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;

import java.util.ArrayList;

/**
 * author: xujiaji
 * created on: 2018/8/18 22:47
 * description:
 */
public class OpenAPISAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_SECTION = 0;
    public static final int TYPE_API = 1;
    private BaseRefreshClickChildViewModel mViewModel;

    public OpenAPISAdapter(BaseRefreshClickChildViewModel viewModel) {
        super(new ArrayList<>());
        addItemType(TYPE_SECTION, R.layout.item_open_api_section);
        addItemType(TYPE_API, R.layout.item_open_api);
        mViewModel = viewModel;
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding;
        if (layoutResId == R.layout.item_open_api) {
            ItemOpenApiBinding b1 = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            b1.setCallback((GeneralClickCallback<ThreeAPIBean.LinkBean>) mViewModel.mClickChildEvent::setValue);
            binding = b1;
        } else if (layoutResId == R.layout.item_open_api_section) {
            ItemOpenApiSectionBinding b2 = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            binding = b2;
        } else {
            return super.getItemView(layoutResId, parent);
        }

        View view = binding.getRoot();
        view.setTag(com.xujiaji.mvvmquick.R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_SECTION:
                ThreeAPIBean threeAPIBean = (ThreeAPIBean) item;
                ItemOpenApiSectionBinding binding = (ItemOpenApiSectionBinding) helper.itemView.getTag(com.xujiaji.mvvmquick.R.id.BaseQuickAdapter_databinding_support);
                binding.setThreeAPIBean(threeAPIBean);
                helper.itemView.setOnClickListener(v -> {
                    int pos = helper.getAdapterPosition();
                    if (threeAPIBean.isExpanded()) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                });
                break;
            case TYPE_API:
                ThreeAPIBean.LinkBean linkBean = (ThreeAPIBean.LinkBean) item;
                ItemOpenApiBinding b = (ItemOpenApiBinding) helper.itemView.getTag(com.xujiaji.mvvmquick.R.id.BaseQuickAdapter_databinding_support);
                b.setLinkBean(linkBean);
                break;
        }
    }
}
