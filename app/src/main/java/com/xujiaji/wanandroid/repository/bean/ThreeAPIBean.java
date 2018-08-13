package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/8/13 10:21
 * description:
 */
public class ThreeAPIBean {

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

    public static class LinkBean {
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
    }
}
