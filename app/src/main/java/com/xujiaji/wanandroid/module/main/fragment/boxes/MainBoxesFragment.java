package com.xujiaji.wanandroid.module.main.fragment.boxes;

import android.view.LayoutInflater;
import android.view.View;

import com.qihoo360.replugin.RePlugin;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.BuildConfig;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBoxesBinding;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.BoxBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 21:07
 * description:
 */
@ActivityScoped
public class MainBoxesFragment extends BaseFragment<FragmentMainBoxesBinding, MainBoxesViewModel> {

    @Inject
    MainBoxesAdapter mAdapter;

    @Inject
    public MainBoxesFragment() {}

    @Override
    public void onBinding(FragmentMainBoxesBinding binding) {
        super.onBinding(binding);
        mAdapter.bindToRecyclerView(binding.list);
        mAdapter.setEmptyView(R.layout.no_item_archived, binding.list);
        final View addOwnProjectView;
        mAdapter.addFooterView(addOwnProjectView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_add_own_project, null));
        mAdapter.openLoadAnimation();
        addOwnProjectView.setOnClickListener(v -> ReadActivity.launch(MainBoxesFragment.this, getString(R.string.add_own_project), BuildConfig.ADD_OWN_PLUGIN));
    }

    @Override
    public void onObserveViewModel(MainBoxesViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setMainBoxesViewModel(viewModel);

        viewModel.mClickEvent.observe(this, this::startPlugin);

        viewModel.mClickGithubEvent.observe(this, boxBean -> ReadActivity.launch(MainBoxesFragment.this, boxBean.getName(), boxBean.getUrl()));

        viewModel.getBoxes().observeData(this, new DataCallbackImp<List<BoxBean>>() {

            @Override
            public void finished() {
                binding.refresh.setRefreshing(false);
            }

            @Override
            public void success(List<BoxBean> bean) {
                viewModel.items.clear();
                viewModel.items.addAll(bean);
            }
        });
    }

    private void startPlugin(BoxBean box) {
        int currentVersion = PrefHelper.getInt(box.getPkg());
        int pluginVersion = RePlugin.getPluginVersion(box.getPkg());
        if (currentVersion != pluginVersion) {
            RePlugin.uninstall(box.getPkg());
        } else {
            PrefHelper.set(box.getPkg(), box.getVersionCode());
        }
        RePlugin.startActivity(getActivity(),
                RePlugin.createIntent(box.getPkg(),
                        box.getStartClass()));
    }
}
