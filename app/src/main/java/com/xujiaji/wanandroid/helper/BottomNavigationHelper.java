package com.xujiaji.wanandroid.helper;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.util.SparseArray;

import com.annimon.stream.Stream;
import com.xujiaji.wanandroid.R;

/**
 * author: xujiaji
 * created on: 2018/8/14 15:17
 * description:
 */
public class BottomNavigationHelper {
    private static class Menu {
        final int id;
        @StringRes
        final int stringId;
        final int order;
        @DrawableRes
        final int iconRes;

        private Menu(int id, int stringId, int order, int iconRes) {
            this.id = id;
            this.stringId = stringId;
            this.order = order;
            this.iconRes = iconRes;
        }
    }

    private static SparseArray<Menu> map = new SparseArray<>();

    static {
        // 首页Menu
        map.put(R.id.navigation_blog_post       , new Menu(R.id.navigation_blog_post       , R.string.blog_post,        5, R.drawable.ic_home_blog_post));
        map.put(R.id.navigation_project         , new Menu(R.id.navigation_project         , R.string.project,          5, R.drawable.ic_home_project));
        map.put(R.id.navigation_box            , new Menu(R.id.navigation_box            , R.string.box,             5, R.drawable.ic_home_box));

        //其他Menu
        map.put(R.id.navigation_knowledge_system, new Menu(R.id.navigation_knowledge_system, R.string.system,           5, R.drawable.ic_nav_system));
        map.put(R.id.navigation_navigation      , new Menu(R.id.navigation_navigation      , R.string.navigation,       5, R.drawable.ic_nav_navigation));
        map.put(R.id.navigation_project_category, new Menu(R.id.navigation_project_category, R.string.project_category, 5, R.drawable.ic_nav_category_project));

        map.put(R.id.navigation_friend_links    , new Menu(R.id.navigation_friend_links    , R.string.links,            5, R.drawable.ic_nav_links));
        map.put(R.id.navigation_open_apis       , new Menu(R.id.navigation_open_apis       , R.string.openapis,         5, R.drawable.ic_nav_api));

        map.put(R.id.navigation_set             , new Menu(R.id.navigation_set             , R.string.set,              5, R.drawable.ic_nav_set));
        map.put(R.id.navigation_about           , new Menu(R.id.navigation_about           , R.string.about,            5, R.drawable.ic_nav_i));

    }

    private static void removeAll(BottomNavigationView navigationView) {
        navigationView.getMenu().removeGroup(R.id.navigationDrawer);
    }

    public static void showHome(BottomNavigationView navigationView) {
        removeAll(navigationView);
        Stream.of(
                map.get(R.id.navigation_blog_post),
                map.get(R.id.navigation_project),
                map.get(R.id.navigation_box)
        ).forEach(menu ->
                navigationView.getMenu().add(R.id.navigationDrawer, menu.id, menu.order, menu.stringId).setIcon(menu.iconRes));
    }

    public static void onlyShow(BottomNavigationView navigationView, @IdRes int menuItemId) {
        if (navigationView.getMenu().size() == 1 && navigationView.getMenu().getItem(0).getItemId() == menuItemId) return;

        removeAll(navigationView);
        Menu menu = map.get(menuItemId);
        if (menu == null) throw new RuntimeException("Not find this menuItemId in map.");
        navigationView.getMenu().add(R.id.navigationDrawer, menu.id, menu.order, menu.stringId).setIcon(menu.iconRes);
    }
}
