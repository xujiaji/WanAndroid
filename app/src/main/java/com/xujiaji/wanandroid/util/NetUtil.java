package com.xujiaji.wanandroid.util;

import com.annimon.stream.Stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: xujiaji
 * created on: 2018/8/11 18:06
 * description:
 */
public class NetUtil {
    public static String encodeCookie(List<String> cookies) {
        final StringBuilder sb = new StringBuilder();
        final Set<String> set = new HashSet<>();
        Stream.of(cookies)
                .map(cookie -> Arrays.asList(cookie.split(";")))
                .forEach(strings -> Stream.of(strings).forEach(set::add));
        Stream.of(set).forEach(cookie -> sb.append(cookie).append(";"));
        if (sb.length() > 0)
            sb.delete(sb.length() - 1, sb.length());
        return sb.toString().trim();
    }
}
