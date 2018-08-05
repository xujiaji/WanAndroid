package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageBean<T> {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":"ayvytr","chapterId":295,"chapterName":"混淆","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":3223,"link":"https://www.jianshu.com/p/cba8ca7fc36d","niceDate":"1天前","origin":"","projectLink":"","publishTime":1533383552000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android混淆最佳实践","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"0xZhangKe","chapterId":401,"chapterName":"二维码","collect":false,"courseId":13,"desc":"基于 com.google.zxing 开发，用于简化 Android 设备扫描、生成二维码等操作。\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/c26d2229-dea5-4518-9b71-83af8de92f1e.png","fresh":false,"id":3220,"link":"http://www.wanandroid.com/blog/show/2265","niceDate":"1天前","origin":"","projectLink":"https://github.com/0xZhangKe/QRCodeView","publishTime":1533353760000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=401"}],"title":"更专注的 Android 二维码扫描的控件 QRCodeView ","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"duanhong169","chapterId":325,"chapterName":"PickerView","collect":false,"courseId":13,"desc":"Android滚动选择器控件，支持日期选择、时间选择、省市区联动选择。滚动效果流畅，支持丰富的自定义属性。","envelopePic":"http://www.wanandroid.com/blogimgs/58133e0a-6939-4cbe-a9c4-455ff2b34719.png","fresh":false,"id":3221,"link":"http://www.wanandroid.com/blog/show/2266","niceDate":"2天前","origin":"","projectLink":"https://github.com/duanhong169/PickerView","publishTime":1533294774000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=325"}],"title":"另一个好用的PickerView","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"andev009","chapterId":159,"chapterName":"OpenGL ES","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3219,"link":"https://www.jianshu.com/p/7d59b3c4f8d4","niceDate":"2天前","origin":"","projectLink":"","publishTime":1533292682000,"superChapterId":186,"superChapterName":"热门专题","tags":[],"title":"Android平台OpenGL SE Camera滤镜实现","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"lovejjfg","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"Readhub Android 客户端，当然是非官方的，不能说是最早做的，但是说到 Kotlin 语言编写，那么应该算最早的啦。项目 2.3M 左右，目前正在持续更新中，GooglePlay 小米应用市场均可下载。Readhub 实验室有收录，欢迎点赞。https://github.com/lovejjfg/Readhub/blob/master/README.md","envelopePic":"http://www.wanandroid.com/blogimgs/0ce4709e-9b54-4eea-9337-9f33823f0b81.png","fresh":false,"id":3216,"link":"http://www.wanandroid.com/blog/show/2262","niceDate":"2天前","origin":"","projectLink":"https://github.com/lovejjfg/Readhub","publishTime":1533226271000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Readhub 客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"mouxuefei","chapterId":339,"chapterName":"K线图","collect":false,"courseId":13,"desc":"今天花了一天时间绘制了一个自定义的曲线图和折线图的自定义控件,可以说现在是身心疲惫了,有点累,下班回家写这篇博客总结下自己的绘制思路,如果有人喜欢的话,麻烦给个star了^_^; \u2003\u2003 ","envelopePic":"http://www.wanandroid.com/blogimgs/fba5398e-0d0e-4288-8377-4c70992afd98.png","fresh":false,"id":3215,"link":"http://www.wanandroid.com/blog/show/2261","niceDate":"2天前","origin":"","projectLink":"https://github.com/mouxuefei/ChartView","publishTime":1533226205000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=339"}],"title":"自定义控件之kotlin绘制折线图和曲线图 ChartView","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"千山万水迷了鹿","chapterId":157,"chapterName":"获取设备唯一标识","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3214,"link":"https://www.jianshu.com/p/fd34fa2ddcd9","niceDate":"2天前","origin":"","projectLink":"","publishTime":1533226113000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android 上关于设备唯一标识的调研","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"猪_队友","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3213,"link":"https://www.jianshu.com/p/b535d1bbdb9f","niceDate":"2天前","origin":"","projectLink":"","publishTime":1533226049000,"superChapterId":186,"superChapterName":"热门专题","tags":[],"title":"Android性能优化一篇文章搞定","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"codelang","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3212,"link":"https://juejin.im/post/5b5edc5ce51d453489493d87","niceDate":"2天前","origin":"","projectLink":"","publishTime":1533226025000,"superChapterId":186,"superChapterName":"热门专题","tags":[],"title":"Android应届生在杭州的求职经历","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Gityuan","chapterId":171,"chapterName":"binder","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3211,"link":"http://gityuan.com/2018/05/19/android-process-adj/","niceDate":"2天前","origin":"","projectLink":"","publishTime":1533225980000,"superChapterId":155,"superChapterName":"framework","tags":[],"title":"解读Android进程优先级ADJ算法","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"想飞的鱼","chapterId":86,"chapterName":"图片处理","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3210,"link":"https://segmentfault.com/a/1190000015723665","niceDate":"2天前","origin":"","projectLink":"","publishTime":1533225868000,"superChapterId":87,"superChapterName":"图片加载","tags":[],"title":"Android图片优化指南","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"openXu","chapterId":339,"chapterName":"K线图","collect":false,"courseId":13,"desc":"自定义图标库，使用方便，扩展性强，后续会添加更多开发中使用到的图表\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/2870d58d-c9a4-47a5-8123-ef869b6bd6d7.png","fresh":false,"id":3207,"link":"http://www.wanandroid.com/blog/show/2259","niceDate":"2018-08-01","origin":"","projectLink":"https://github.com/openXu/OXChart","publishTime":1533127845000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=339"}],"title":"各种自定义图表库，使用简单，支持扩展 OXChart","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"asdzheng","chapterId":321,"chapterName":"算法","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3206,"link":"https://blog.csdn.net/asdzheng/article/details/70226007","niceDate":"2018-07-31","origin":"","projectLink":"","publishTime":1533028396000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Hash算法总结","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"jjlanbupt","chapterId":10,"chapterName":"Activity","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3205,"link":"https://www.jianshu.com/p/f0e53e770e32","niceDate":"2018-07-31","origin":"","projectLink":"","publishTime":1533014688000,"superChapterId":10,"superChapterName":"四大组件","tags":[],"title":"你可能不知道的Activity启动的诡异现象探索","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"mouxuefei","chapterId":338,"chapterName":"日历&时钟","collect":false,"courseId":13,"desc":"今天玩小米mix2的时候看到了小米的时间控件效果真的很棒。有各种动画效果，3d触摸效果，然后就想着自己能不能也实现一个这样的时间控件，那就开始行动绘制一个简易版本的小米时间控件吧o((≧▽≦o)","envelopePic":"http://www.wanandroid.com/blogimgs/d61e55e4-cd39-4940-a1ad-befc4aeb9f78.png","fresh":false,"id":3202,"link":"http://www.wanandroid.com/blog/show/2256","niceDate":"2018-07-30","origin":"","projectLink":"https://github.com/mouxuefei/MIClockView","publishTime":1532965108000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=338"}],"title":"自定义view之kotlin绘制精简小米时间控件 MIClockView","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"LillteZheng","chapterId":400,"chapterName":"ViewPager","collect":false,"courseId":13,"desc":"这个一个 viewpager 工具类，能够帮你快速实现导航栏轮播图，app引导页，viewpager + fragment；内置多种tab指示器，让你告别 viewpager 的繁琐操作，专注逻辑功能","envelopePic":"http://www.wanandroid.com/blogimgs/ea5cac16-f9b7-4b8d-8812-d215a00f2853.png","fresh":false,"id":3204,"link":"http://www.wanandroid.com/blog/show/2258","niceDate":"2018-07-30","origin":"","projectLink":"https://github.com/LillteZheng/ViewPagerHelper","publishTime":1532963885000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=400"}],"title":"ViewPager 工具类，快速实现轮播图，app引导页，tab标签 ViewPagerHelper","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"CodingEnding","chapterId":358,"chapterName":"项目基础功能","collect":false,"courseId":13,"desc":"FairySearchView是经过封装的通用搜索控件，可以根据需求切换不同的布局模式。内置多种事件监听器，可以满足不同场景的业务需求，使用方式非常灵活。","envelopePic":"http://www.wanandroid.com/blogimgs/26974b8f-5042-4023-af8e-ce8bef726dee.png","fresh":false,"id":3203,"link":"http://www.wanandroid.com/blog/show/2257","niceDate":"2018-07-30","origin":"","projectLink":"https://github.com/CodingEnding/FairySearchView","publishTime":1532963643000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=358"}],"title":"FairySearchView 灵活的通用搜索控件","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"vvengzt","chapterId":97,"chapterName":"音视频","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3200,"link":"https://www.jianshu.com/p/1f78c4211ab7","niceDate":"2018-07-29","origin":"","projectLink":"","publishTime":1532879484000,"superChapterId":95,"superChapterName":"多媒体技术","tags":[],"title":"Android音视频开发初探之AudioRecord与AudioTrack完成音频采集与播放","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"光源_Android","chapterId":295,"chapterName":"混淆","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3199,"link":"https://www.jianshu.com/p/158aa484da13","niceDate":"2018-07-29","origin":"","projectLink":"","publishTime":1532879339000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"写给Android开发者的混淆使用手册","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"腾讯Bugly","chapterId":295,"chapterName":"混淆","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3198,"link":"https://mp.weixin.qq.com/s/WmJyiA3fDNriw5qXuoA9MA","niceDate":"2018-07-29","origin":"","projectLink":"","publishTime":1532879220000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android 混淆那些事儿","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 76
         * size : 20
         * total : 1515
         */

        @SerializedName("curPage")
        private int curPage;
        @SerializedName("offset")
        private int offset;
        @SerializedName("over")
        private boolean over;
        @SerializedName("pageCount")
        private int pageCount;
        @SerializedName("size")
        private int size;
        @SerializedName("total")
        private int total;
        @SerializedName("datas")
        private List<T> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getDatas() {
            return datas;
        }

        public void setDatas(List<T> datas) {
            this.datas = datas;
        }

    }