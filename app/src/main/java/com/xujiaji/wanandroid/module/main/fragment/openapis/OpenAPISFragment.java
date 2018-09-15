package com.xujiaji.wanandroid.module.main.fragment.openapis;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.helper.EmptyViewHelper;
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

    OpenAPISAdapter mAdapter;

    @Inject
    public OpenAPISFragment() {}

    @Override
    public void onBinding(@NonNull LayoutRefreshBinding binding) {

    }

    @Override
    public void onObserveViewModel(@NonNull OpenAPISViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        mAdapter = new OpenAPISAdapter(viewModel);
        mAdapter.bindToRecyclerView(binding.list);
        EmptyViewHelper.initEmpty(binding.list);

        viewModel.getObservableThreeAPIS().observeData(this, new DataCallbackImp<List<ThreeAPIBean>>(binding.refresh) {
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

        viewModel.mClickChildEvent.observe(this, linkBean -> ReadActivity.launch(OpenAPISFragment.this, linkBean.getName(), linkBean.getUrl()));
    }
}
