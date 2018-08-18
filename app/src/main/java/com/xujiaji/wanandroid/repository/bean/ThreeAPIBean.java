package com.xujiaji.wanandroid.repository.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.xujiaji.wanandroid.module.main.fragment.openapis.OpenAPISAdapter.TYPE_API;
import static com.xujiaji.wanandroid.module.main.fragment.openapis.OpenAPISAdapter.TYPE_SECTION;

/**
 * author: xujiaji
 * created on: 2018/8/13 10:21
 * description:
 */
public class ThreeAPIBean extends AbstractExpandableItem<ThreeAPIBean.LinkBean> implements MultiItemEntity {

    /**
     * name : 日历
     * links : [{"url":"https://mmp.51wnl.com/apiDetail_calandar.html","name":"万年历API文档"}]
     */

    @SerializedName("name")
    private String name;
    @SerializedName("links")
    private List<LinkBean> links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LinkBean> getLinks() {
        return links;
    }

    public void setLinks(List<LinkBean> links) {
        this.links = links;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return TYPE_SECTION;
    }

    public static class LinkBean implements MultiItemEntity {
        /**
         * url : https://mmp.51wnl.com/apiDetail_calandar.html
         * name : 万年历API文档
         */

        @SerializedName("url")
        private String url;
        @SerializedName("name")
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int getItemType() {
            return TYPE_API;
        }
    }
}
