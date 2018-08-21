package com.xujiaji.wanandroid.module.main.fragment.web_nav;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xujiaji.mvvmquick.base.NoneViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.module.main.fragment.openapis.OpenAPISAdapter;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;
import com.xujiaji.wanandroid.repository.bean.WebNavBean;
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
public class WebNavFragment extends BaseFragment<LayoutRefreshBinding, WebNavViewModel> {

    OpenAPISAdapter mAdapter;

    @Inject
    public WebNavFragment() {}

    @Override
    public void onBinding(LayoutRefreshBinding binding) {
        super.onBinding(binding);

    }

    @Override
    public void onObserveViewModel(WebNavViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        mAdapter = new OpenAPISAdapter(viewModel);
        mAdapter.bindToRecyclerView(binding.list);
        mAdapter.setEmptyView(R.layout.no_item_archived, binding.list);

        viewModel.getObservableWebNavs().observeData(this, new DataCallbackImp<List<WebNavBean>>(binding.refresh) {
            @Override
            public void success(List<WebNavBean> list) {
                ArrayList<MultiItemEntity> src = new ArrayList<>();
                for (WebNavBean wnb : list) {
                    ThreeAPIBean t = new ThreeAPIBean();
                    t.setName(wnb.getName());
                    for (WebNavBean.ArticlesBean ab : wnb.getArticles()) {
                        ThreeAPIBean.LinkBean linkBean = new ThreeAPIBean.LinkBean();
                        linkBean.setName(ab.getTitle());
                        linkBean.setUrl(ab.getLink());
                        t.addSubItem(linkBean);
                    }
                    src.add(t);
                }
                mAdapter.setNewData(src);
//                mAdapter.expandAll();
            }
        });

        viewModel.mClickChildEvent.observe(this, linkBean -> ReadActivity.launch(WebNavFragment.this, linkBean.getName(), linkBean.getUrl()));
    }
}
