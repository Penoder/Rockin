package com.rockin.entity.discovery.hot;

import java.util.List;

/**
 * @author Penoder
 * @date 18-1-27.
 */
public class HotEntity {

    private List<ItemEntity> itemList;

    public List<ItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemEntity> itemList) {
        this.itemList = itemList;
    }

    public static class ItemEntity {
        /**
         * horizontalScrollCard 类型的数据用于显示在轮播
         * squareCardCollection 表示排行
         * video 为视频相关数据
         */
        private String type;    // 数据类型 horizontalScrollCard、textHeader、squareCardCollection、video等

        private DataEntity data;  // 各种类型的详细数据

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataEntity getData() {
            return data;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public static class DataEntity {

            private String dataType; // 和上面的 类型 一致,三种类型都有的字段

            private List<ItemDataEntity> itemList;  // HorizontalScrollCard 类型的字段

            private int count;  // HorizontalScrollCard 类型的字段 itemList 中的数量

            private String text;    //  text: "最新发布" 某个Text后面才是对应该text的数据

            /**
             * 下面为 video 类型的数据的字段
             */
            private int id;

            /**
             * video 标题
             */
            private String title;

            /**
             * video 副标题
             */
            private String slogan;

            /**
             * 视频描述内容
             */
            private String description;

            /**
             * 视频种类（音乐、剧情、动画等）
             */
            private String category;

            /**
             * 作者信息（插入到作者表中）
             */
            private AuthorEntity author;

            /**
             * 封面预览图片
             */
            private CoverEntity cover;

            /**
             * 视频播放地址
             */
            private String playUrl;

            /**
             * 视频时长，单位为 秒s
             */
            private int duration;

            /**
             * WebView 加载地址，用于分享视频时的 URL
             */
            private WebUrlEntity webUrl;

            /**
             * 视频的创建时间还是什么时间，时间戳形式，可用于每日内容时，定位视频属于哪一天
             */
            private long releaseTime;

            /**
             * 视频创建属于的时间戳，该时间戳一般是某天 9 点所对应的时间戳，可用于判断视频属于那一天的条件
             */
            private long date;

            /**
             * DAILY，具体意义不详
             */
            private String library;

            /**
             * 用户信息，包括收藏、分享、评论数量
             */
            private ConsumptionEntity consumption;

            /**
             * 是否是开眼精选， NORMAL普通
             */
            private String type;

            /**
             * height : 480
             * width : 854
             * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=aliyun","size":33200203},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=qcloud","size":33200203},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=ucloud","size":33200203}]
             * name : 标清
             * type : normal
             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=aliyun
             */

            private List<PlayInfoEntity> playInfo;

            public List<ItemDataEntity> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemDataEntity> itemList) {
                this.itemList = itemList;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public void setText(String text) {
                this.text = text;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setAuthor(AuthorEntity author) {
                this.author = author;
            }

            public void setCover(CoverEntity cover) {
                this.cover = cover;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public void setWebUrl(WebUrlEntity webUrl) {
                this.webUrl = webUrl;
            }

            public void setReleaseTime(long releaseTime) {
                this.releaseTime = releaseTime;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public void setLibrary(String library) {
                this.library = library;
            }

            public void setConsumption(ConsumptionEntity consumption) {
                this.consumption = consumption;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setPlayInfo(List<PlayInfoEntity> playInfo) {
                this.playInfo = playInfo;
            }

            public String getDataType() {
                return dataType;
            }

            public String getText() {
                return text;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getSlogan() {
                return slogan;
            }

            public String getDescription() {
                return description;
            }

            public String getCategory() {
                return category;
            }

            public AuthorEntity getAuthor() {
                return author;
            }

            public CoverEntity getCover() {
                return cover;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public int getDuration() {
                return duration;
            }

            public WebUrlEntity getWebUrl() {
                return webUrl;
            }

            public long getReleaseTime() {
                return releaseTime;
            }

            public long getDate() {
                return date;
            }

            public String getLibrary() {
                return library;
            }

            public ConsumptionEntity getConsumption() {
                return consumption;
            }

            public String getType() {
                return type;
            }

            public List<PlayInfoEntity> getPlayInfo() {
                return playInfo;
            }

            /**
             * HorizontalScrollCard 的数据
             */
            public static class ItemDataEntity {

                /**
                 * banner2
                 */
                private String type;

                /**
                 * Banner 的数据
                 */
                private BannerEntity data;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public BannerEntity getData() {
                    return data;
                }

                public void setData(BannerEntity data) {
                    this.data = data;
                }

                public static class BannerEntity {

                    private String dataType;

                    private int id;

                    private String title;

                    private String description;

                    /**
                     * Banner 封面图
                     */
                    private String image;

                    /**
                     * Banner 详情的 WebUrl， 例如：http://www.kaiyanapp.com/topic_article.html?nid=47&cookie={cookie}&shareable=true
                     * 但是 Url 是经历过 网址搜索栏编码后的形式--------
                     */
                    private String actionUrl;

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getActionUrl() {
                        return actionUrl;
                    }

                    public void setActionUrl(String actionUrl) {
                        this.actionUrl = actionUrl;
                    }

                }
            }

            /**
             * 作者的信息，插入到作者表中
             */
            public static class AuthorEntity {

                /**
                 * id : 2164
                 * icon : http://img.kaiyanapp.com/75bc791c5f6cc239d6056e0a52d077fd.jpeg?imageMogr2/quality/60/format/jpg
                 * name : 开眼旅行精选
                 * description : 发现世界的奇妙和辽阔
                 * link :
                 * latestReleaseTime : 1514250000000
                 * videoNum : 435
                 * adTrack : null
                 * follow : {"itemType":"author","itemId":2164,"followed":false}
                 * shield : {"itemType":"author","itemId":2164,"shielded":false}
                 * approvedNotReadyVideoCount : 0
                 * ifPgc : true
                 */

                /**
                 * 作者ID
                 */
                private int id;

                /**
                 * 作者头像
                 */
                private String icon;

                /**
                 * 作者名字
                 */
                private String name;

                /**
                 * 作者介绍
                 */
                private String description;

                /**
                 * 不详
                 */
                private String link;

                /**
                 * 时间戳，意义不详，上次什么时间？
                 */
                private long latestReleaseTime;

                /**
                 * 作者的视频数量
                 */
                private int videoNum;

                /**
                 * 不详
                 */
                private int approvedNotReadyVideoCount;

                /**
                 * 不详
                 */
                private boolean ifPgc;

                public void setId(int id) {
                    this.id = id;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setLatestReleaseTime(long latestReleaseTime) {
                    this.latestReleaseTime = latestReleaseTime;
                }

                public void setVideoNum(int videoNum) {
                    this.videoNum = videoNum;
                }

                public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                    this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                }

                public void setIfPgc(boolean ifPgc) {
                    this.ifPgc = ifPgc;
                }

                public int getId() {
                    return id;
                }

                public String getIcon() {
                    return icon;
                }

                public String getName() {
                    return name;
                }

                public String getDescription() {
                    return description;
                }

                public String getLink() {
                    return link;
                }

                public long getLatestReleaseTime() {
                    return latestReleaseTime;
                }

                public int getVideoNum() {
                    return videoNum;
                }

                public int getApprovedNotReadyVideoCount() {
                    return approvedNotReadyVideoCount;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

            }

            /**
             * 封面图片信息
             */
            public static class CoverEntity {
                /**
                 * feed : http://img.kaiyanapp.com/068978f6689861edf91af6a7d4daef58.jpeg?imageMogr2/quality/60/format/jpg
                 * detail : http://img.kaiyanapp.com/068978f6689861edf91af6a7d4daef58.jpeg?imageMogr2/quality/60/format/jpg
                 * blurred : http://img.kaiyanapp.com/cf887e602b6d45ea222723a81f8b31cb.jpeg?imageMogr2/quality/60/format/jpg
                 * sharing : null
                 * homepage :
                 */

                /**
                 * 视频预览图片
                 */
                private String feed;

                /**
                 * 视频预览图片，和上面一致
                 */
                private String detail;

                /**
                 * 封面预览图片，经过了模糊处理
                 */
                private String blurred;

                /**
                 * 封面预览图片裁剪成正方形后的图片，可用于分享时
                 */
                private String homepage;

                public void setFeed(String feed) {
                    this.feed = feed;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public void setBlurred(String blurred) {
                    this.blurred = blurred;
                }

                public void setHomepage(String homepage) {
                    this.homepage = homepage;
                }

                public String getFeed() {
                    return feed;
                }

                public String getDetail() {
                    return detail;
                }

                public String getBlurred() {
                    return blurred;
                }

                public String getHomepage() {
                    return homepage;
                }
            }

            /**
             * 网页地址，用于分享
             */
            public static class WebUrlEntity {

                /**
                 * raw : http://www.eyepetizer.net/detail.html?vid=12513
                 * forWeibo : http://wandou.im/3l7v23
                 */

                /**
                 * WebView 加载地址
                 */
                private String raw;

                private String forWeibo;

                public void setRaw(String raw) {
                    this.raw = raw;
                }

                public void setForWeibo(String forWeibo) {
                    this.forWeibo = forWeibo;
                }

                public String getRaw() {
                    return raw;
                }

                public String getForWeibo() {
                    return forWeibo;
                }
            }

            public static class ConsumptionEntity {
                /**
                 * collectionCount : 972
                 * shareCount : 1476
                 * replyCount : 19
                 */

                /**
                 * 关于该视频的收藏数量
                 */
                private int collectionCount;

                /**
                 * 分享次数
                 */
                private int shareCount;

                /**
                 * 评论数量
                 */
                private int replyCount;

                public void setCollectionCount(int collectionCount) {
                    this.collectionCount = collectionCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public void setReplyCount(int replyCount) {
                    this.replyCount = replyCount;
                }

                public int getCollectionCount() {
                    return collectionCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public int getReplyCount() {
                    return replyCount;
                }
            }

            public static class PlayInfoEntity {
                private int height;
                private int width;
                private String name;
                private String type;
                private String url;
                /**
                 * name : aliyun
                 * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=aliyun
                 * size : 33200203
                 */

                private List<UrlListEntity> urlList;

                public void setHeight(int height) {
                    this.height = height;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setUrlList(List<UrlListEntity> urlList) {
                    this.urlList = urlList;
                }

                public int getHeight() {
                    return height;
                }

                public int getWidth() {
                    return width;
                }

                public String getName() {
                    return name;
                }

                public String getType() {
                    return type;
                }

                public String getUrl() {
                    return url;
                }

                public List<UrlListEntity> getUrlList() {
                    return urlList;
                }

                public static class UrlListEntity {
                    private String name;
                    private String url;
                    private int size;

                    public void setName(String name) {
                        this.name = name;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public void setSize(int size) {
                        this.size = size;
                    }

                    public String getName() {
                        return name;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public int getSize() {
                        return size;
                    }
                }
            }
        }
    }
}
