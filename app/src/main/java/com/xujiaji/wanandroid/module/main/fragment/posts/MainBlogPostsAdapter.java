package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.mvvmquick.di.FragmentScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemBlogPostBinding;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/6 11:22
 * description:
 */
public class MainBlogPostsAdapter extends MQQuickAdapter<BlogPostBean, ItemBlogPostBinding> {

    private final MainBlogPostsViewModel mViewModel;

    public MainBlogPostsAdapter(MainBlogPostsViewModel viewModel) {
        super(R.layout.item_blog_post);
        this.mViewModel = viewModel;
    }

    @Override
    protected void onBinding(ItemBlogPostBinding binding) {
        binding.setCallback((GeneralClickCallback<BlogPostBean>) mViewModel.mClickEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemBlogPostBinding> helper, BlogPostBean item) {
        item.setIndex(getData().indexOf(item));
        helper.binding.setBlogPost(item);
        helper.binding.executePendingBindings();
    }

    @Nullable
    @Override
    protected MQViewModel getViewModel() {
        return mViewModel;
    }
}
