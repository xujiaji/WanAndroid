package com.xujiaji.wanandroid.module.main.fragment.project_category;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemProjectCategoryBinding;
import com.xujiaji.wanandroid.repository.bean.TreeBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/19 17:18
 * description:
 */
public class ProjectCategoryAdapter extends MQQuickAdapter<TreeBean, ItemProjectCategoryBinding> {

    @Inject
    ProjectCategoryViewModel mViewModel;

    @Inject
    public ProjectCategoryAdapter() {
        super(R.layout.item_project_category);
    }

    @Override
    protected void onBinding(ItemProjectCategoryBinding binding) {
        binding.setCallback((GeneralClickCallback<TreeBean>) mViewModel.mClickEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemProjectCategoryBinding> helper, TreeBean item) {
        helper.binding.setProjectTree(item);
        helper.binding.executePendingBindings();
    }
}
