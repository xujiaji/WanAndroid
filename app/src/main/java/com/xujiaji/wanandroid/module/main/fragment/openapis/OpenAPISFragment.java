package com.xujiaji.wanandroid.module.main.fragment.openapis;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/18 21:29
 * description:
 */
@ActivityScoped
public class OpenAPISFragment extends BaseFragment<LayoutRefreshBinding, OpenAPISViewModel> {

    @Inject
    OpenAPISAdapter mAdapter;

    @Inject
    public OpenAPISFragment() {}

    @Override
    public void onBinding(LayoutRefreshBinding binding) {
        super.onBinding(binding);
        mAdapter.bindToRecyclerView(binding.list);
        mAdapter.setEmptyView(R.layout.no_item_archived, binding.list);
    }

    @Override
    public void onObserveViewModel(OpenAPISViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        viewModel.getObservableThreeAPIS().observeData(this, new DataCallbackImp<List<ThreeAPIBean>>() {

            @Override
            public void finished() {
                super.finished();
                binding.refresh.setRefreshing(false);
            }

            @Override
            public void success(List<ThreeAPIBean> bean) {
                ArrayList<MultiItemEntity> src = new ArrayList<>();
                for (ThreeAPIBean t : bean) {
                    src.add(t);
                    t.setSubItems(t.getLinks());
                }
                mAdapter.setNewData(src);
                mAdapter.expandAll();
            }
        });

        viewModel.mClickEvent.observe(this, linkBean -> ReadActivity.launch(OpenAPISFragment.this, linkBean.getName(), linkBean.getUrl()));
    }
}
