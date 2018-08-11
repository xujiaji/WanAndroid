package com.xujiaji.wanandroid.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.xujiaji.mvvmquick.util.LogUtil;


public final class BottomNavigationFABBehavior extends Behavior<FloatingActionButton> {
    public BottomNavigationFABBehavior(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean layoutDependsOn(@Nullable CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        return dependency instanceof SnackbarLayout;
    }

    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        child.setTranslationY(0.0f);
    }

    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        return this.updateButton(child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        float scale = Math.max(0, Math.min(1, child.getScaleX() - dy * 1.0f / child.getHeight()));
        child.setScaleX(scale);
        child.setScaleY(scale);
    }

    private boolean updateButton(View child, View dependency) {
        if (dependency instanceof SnackbarLayout) {
            float oldTranslation = child.getTranslationY();
            float height = (float) dependency.getHeight();
            float newTranslation = dependency.getTranslationY() - height;
            child.setTranslationY(newTranslation);
            return oldTranslation != newTranslation;
        } else {
            return false;
        }
    }
}