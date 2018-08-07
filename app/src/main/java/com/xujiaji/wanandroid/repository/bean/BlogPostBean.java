package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogPostBean {
    /**
     * apkLink :
     * author : ayvytr
     * chapterId : 295
     * chapterName : 混淆
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : true
     * id : 3223
     * link : https://www.jianshu.com/p/cba8ca7fc36d
     * niceDate : 1天前
     * origin :
     * projectLink :
     * publishTime : 1533383552000
     * superChapterId : 135
     * superChapterName : 项目必备
     * tags : []
     * title : Android混淆最佳实践
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    @SerializedName("apkLink")
    private String apkLink;
    @SerializedName("author")
    private String author;
    @SerializedName("chapterId")
    private int chapterId;
    @SerializedName("chapterName")
    private String chapterName;
    @SerializedName("collect")
    private boolean collect;
    @SerializedName("courseId")
    private int courseId;
    @SerializedName("desc")
    private String desc;
    @SerializedName("envelopePic")
    private String envelopePic;
    @SerializedName("fresh")
    private boolean fresh;
    @SerializedName("id")
    private int id;
    @SerializedName("link")
    private String link;
    @SerializedName("niceDate")
    private String niceDate;
    @SerializedName("origin")
    private String origin;
    @SerializedName("projectLink")
    private String projectLink;
    @SerializedName("publishTime")
    private long publishTime;
    @SerializedName("superChapterId")
    private int superChapterId;
    @SerializedName("superChapterName")
    private String superChapterName;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;
    @SerializedName("userId")
    private int userId;
    @SerializedName("visible")
    private int visible;
    @SerializedName("zan")
    private int zan;
    @SerializedName("tags")
    private List<TagsBean> tags;


    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * name : 项目
         * url : /project/list/1?cid=386
         */

        @SerializedName("name")
        private String name;
        @SerializedName("url")
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}