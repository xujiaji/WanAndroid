package com.xujiaji.wanandroid.module.main.fragment.projects;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainProjectsBinding;
import com.xujiaji.wanandroid.helper.RefreshLoadHelper;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 21:06
 * description:
 */
@ActivityScoped
public class MainProjectsFragment extends BaseFragment<FragmentMainProjectsBinding, MainProjectsViewModel> {
    @Inject
    MainProjectsAdapter mAdapter;

    @Inject
    public MainProjectsFragment() {}

    @Override
    public void onBinding(FragmentMainProjectsBinding binding) {
        super.onBinding(binding);
        RefreshLoadHelper.init(binding.refresh, mAdapter, binding.list);
    }

    @Override
    public void onObserveViewModel(MainProjectsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setMainProjectsViewModel(viewModel);
        viewModel.getObservableProjects().observe(this, RefreshLoadHelper.listener(this, mAdapter, binding.refresh, viewModel));
    }
}
