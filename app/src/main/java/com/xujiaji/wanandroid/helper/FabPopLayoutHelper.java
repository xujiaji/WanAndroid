package com.xujiaji.wanandroid.helper;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

/**
 * author: xujiaji
 * created on: 2018/8/8 12:26
 * description:
 */
public class FabPopLayoutHelper {

    private static boolean rotate = false;

    public static void initPopLayout(FloatingActionButton fab, View backDrop, View ... popLayouts) {
        rotate = false;
        if (popLayouts == null || popLayouts.length == 0 || backDrop == null) return;

        for (View ll: popLayouts) {
            AnimHelper.initShowOut(ll);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMode(fab, popLayouts, backDrop);
            }
        });

        backDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMode(fab, popLayouts, backDrop);
            }
        });

    }


    private static void toggleFabMode(View v, View[] popLayouts, View backDrop) {

        rotate = AnimHelper.rotateFab(v, !rotate);
        v.setTag(rotate);
        if (rotate) {
            for (View ll: popLayouts) {
                AnimHelper.showIn(ll);
            }
            backDrop.setVisibility(View.VISIBLE);
        } else {
            for (View ll: popLayouts) {
                AnimHelper.showOut(ll);
            }
            backDrop.setVisibility(View.GONE);
        }
    }

}
