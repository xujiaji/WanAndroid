package com.xujiaji.wanandroid.module.category_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.text.TextPaint;

import com.xujiaji.wanandroid.adapter.FragmentsPagerAdapter;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityCategoryDetailBinding;
import com.xujiaji.wanandroid.helper.ClassHelper;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.model.FragmentModel;
import com.xujiaji.wanandroid.module.main.fragment.posts.MainBlogPostsFragment;
import com.xujiaji.wanandroid.repository.bean.TreeBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * author: xujiaji
 * created on: 2018/9/14 22:21
 * description:
 */
public class CategoryDetailActivity extends BaseActivity<ActivityCategoryDetailBinding, CategoryDetailViewModel> {

    private TreeBean mTreeBean;
    private TextPaint mTextTitlePaint;

    @Inject
    Provider<MainBlogPostsFragment> providerFrag;

    public static void launch(Context context, TreeBean treeBean) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(TreeBean.class.getSimpleName(), treeBean);
        context.startActivity(intent);
    }

    @Override
    public void onBeforeCreate() {
        ToolbarHelper.initTranslucent(this);
    }

    @Override
    public void onIntentHandle(@NonNull Intent intent) {
        mTreeBean = intent.getParcelableExtra(TreeBean.class.getSimpleName());
    }

    @Override
    public void onBinding(@NonNull ActivityCategoryDetailBinding binding) {
        ToolbarHelper.initFullBar(binding.toolbar, this);
        binding.toolbar.setNavigationIcon(null);
        binding.btnBack.setOnClickListener(v -> finish());

        List<FragmentModel> list = new ArrayList<>();

        int initIndex = 0;
        for (int i = 0, len = mTreeBean.getChildren().size(); i < len; i++) {
            TreeBean treeBean = mTreeBean.getChildren().get(i);

            if (treeBean.getId() == mTreeBean.getCheckedChild().getId()) {
                initIndex = i;
            }

            MainBlogPostsFragment fragment = providerFrag.get();
            Bundle bundle = new Bundle();
            bundle.putInt(MainBlogPostsFragment.TYPE, MainBlogPostsFragment.TYPE_CATEGORY);
            bundle.putInt(MainBlogPostsFragment.ID, treeBean.getId());
            fragment.setArguments(bundle);
            list.add(new FragmentModel(treeBean.getName(), fragment));
        }

        FragmentsPagerAdapter adapter = new FragmentsPagerAdapter(getSupportFragmentManager(), list);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        // 设置初始位置
        binding.viewPager.setCurrentItem(initIndex);

        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (getTextTitlePaint() != null) {
                    binding.btnBack.setColorFilter(mTextTitlePaint.getColor());
                }
            }
        });
    }

    @Override
    public void onObserveViewModel(@NonNull CategoryDetailViewModel viewModel) {
//        binding.setCategoryDetailViewModel(viewModel);
        binding.setTreeBean(mTreeBean);
    }

    private TextPaint getTextTitlePaint() {
        if (mTextTitlePaint == null) {
            mTextTitlePaint = ClassHelper.getCollapsingTitlePaint(binding.collapsing);
        }
        return mTextTitlePaint;
    }
}
