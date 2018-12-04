package com.xujiaji.wanandroid.helper;

import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextPaint;

import java.lang.reflect.Field;

/**
 * author: xujiaji
 * created on: 2018/9/15 0:30
 * description:
 */
public class ClassHelper {

    /**
     * 通过反射得到CollapsingToolbarLayout中标题的画笔。通过它得到标题变化中的颜色
     */
    public static TextPaint getCollapsingTitlePaint(CollapsingToolbarLayout collapsing) {
        try {
            Class clazz = Class.forName("android.support.design.widget.CollapsingToolbarLayout");
            Field fieldTextHelper = clazz.getDeclaredField("collapsingTextHelper");
            fieldTextHelper.setAccessible(true);
            Object obj = fieldTextHelper.get(collapsing);

            Class clazzHelper = Class.forName("android.support.design.widget.CollapsingTextHelper");
            Field fieldTextPaint = clazzHelper.getDeclaredField("textPaint");
            fieldTextPaint.setAccessible(true);
            return (TextPaint) fieldTextPaint.get(obj);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
