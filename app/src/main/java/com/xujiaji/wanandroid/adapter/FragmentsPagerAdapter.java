package com.xujiaji.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xujiaji.wanandroid.model.FragmentModel;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/8/4 0:09
 * description:
 */
public class FragmentsPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentModel> fragments;

    public FragmentsPagerAdapter(FragmentManager fm, List<FragmentModel> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    public void remove(FragmentModel model) {
        if (fragments != null) {
            fragments.remove(model);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (fragments != null) {
            fragments.remove(position);
            notifyDataSetChanged();
        }
    }
}
