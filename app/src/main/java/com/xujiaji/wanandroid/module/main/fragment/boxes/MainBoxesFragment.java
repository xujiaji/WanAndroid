package com.xujiaji.wanandroid.module.main.fragment.boxes;

import com.annimon.stream.Stream;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBoxesBinding;
import com.xujiaji.wanandroid.repository.bean.ToolBean;

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
        mAdapter.openLoadAnimation();
    }

    @Override
    public void onObserveViewModel(MainBoxesViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setMainBoxesViewModel(viewModel);
        ToolBean toolBean = new ToolBean();
        toolBean.setName("工具名字");
        toolBean.setDescription("balabala 一抹多的描述");
        toolBean.setThumb("http://www.wanandroid.com/resources/image/pc/tools/bejson.png");
        viewModel.items.addAll(Stream.of(toolBean, toolBean, toolBean, toolBean, toolBean).toList());
    }
}
