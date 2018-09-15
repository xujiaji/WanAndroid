package com.xujiaji.wanandroid.module.main.fragment.project_category;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.helper.EmptyViewHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.repository.bean.TreeBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/22 13:44
 * description:
 */
@ActivityScoped
public class ProjectCategoryFragment extends BaseFragment<LayoutRefreshBinding, ProjectCategoryViewModel> {

    @Inject
    ProjectCategoryAdapter mAdapter;

    @Inject
    public ProjectCategoryFragment() {}

    @Override
    public void onBinding(@NonNull LayoutRefreshBinding binding) {
        mAdapter.bindToRecyclerView(binding.list);
        EmptyViewHelper.initEmpty(binding.list);
    }

    @Override
    public void onObserveViewModel(@NonNull ProjectCategoryViewModel viewModel) {
        binding.setRefreshViewModel(viewModel);
        viewModel.getObservableProjectTree().observeData(this, new DataCallbackImp<List<TreeBean>>(binding.refresh) {
            @Override
            public void success(List<TreeBean> bean) {
                viewModel.items.clear();
                viewModel.items.addAll(bean);
            }
        });

        viewModel.mClickEvent.observe(this, new Observer<TreeBean>() {
            @Override
            public void onChanged(@Nullable TreeBean treeBean) {
                ToastHelper.info(treeBean.getName());
            }
        });
    }
}
