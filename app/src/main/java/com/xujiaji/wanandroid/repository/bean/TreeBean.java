package com.xujiaji.wanandroid.repository.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/8/19 17:06
 * description:
 */
public class TreeBean implements Parcelable {

    /**
     * children : []
     * courseId : 13
     * id : 60
     * name : Android Studio相关
     * order : 1000
     * parentChapterId : 150
     * visible : 1
     */

    @SerializedName("courseId")
    private int courseId;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private int order;
    @SerializedName("parentChapterId")
    private int parentChapterId;
    @SerializedName("visible")
    private int visible;
    @SerializedName("children")
    private List<TreeBean> children;

    private TreeBean checkedChild;

    public TreeBean getCheckedChild() {
        return checkedChild;
    }

    public TreeBean setCheckedChild(TreeBean checkedChild) {
        this.checkedChild = checkedChild;
        return this;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<TreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<TreeBean> children) {
        this.children = children;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.courseId);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.order);
        dest.writeInt(this.parentChapterId);
        dest.writeInt(this.visible);
        dest.writeList(this.children);
        dest.writeParcelable(this.checkedChild, flags);
    }

    public TreeBean() {
    }

    protected TreeBean(Parcel in) {
        this.courseId = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.order = in.readInt();
        this.parentChapterId = in.readInt();
        this.visible = in.readInt();
        this.children = new ArrayList<TreeBean>();
        in.readList(this.children, TreeBean.class.getClassLoader());
        this.checkedChild = in.readParcelable(TreeBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TreeBean> CREATOR = new Parcelable.Creator<TreeBean>() {
        @Override
        public TreeBean createFromParcel(Parcel source) {
            return new TreeBean(source);
        }

        @Override
        public TreeBean[] newArray(int size) {
            return new TreeBean[size];
        }
    };
}
