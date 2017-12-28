package com.rockin.entity.homepage;

import java.util.List;

/**
 * Created by wangpeng on 17-12-26.
 */

public class HomeEntity {

    /**
     * count : 14
     * total : 0
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1514077200000&num=2&page=2
     * adExist : false
     * date : 1514250000000
     * nextPublishTime : 1514336400000
     * dialog : null
     * topIssue : null
     * refreshCount : 0
     * lastStartId : 0
     */

    /**
     * 此次网络请求获取到的数据的条数
     */
    private int count;

    /**
     * 下一页 地址，用于分页加载
     */
    private String nextPageUrl;

    /**
     * 当前页请求的时间戳
     */
    private long date;

    /**
     * 下一页请求数据的时间戳
     */
    private long nextPublishTime;

    private List<TypeEntity> itemList;

    public void setCount(int count) {
        this.count = count;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public void setItemList(List<TypeEntity> itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        return count;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public long getDate() {
        return date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public List<TypeEntity> getItemList() {
        return itemList;
    }

    /**
     * itemList 字段对应的对象集合，分为 textHeader 和 Video 两种类型
     */
    public static class TypeEntity {

        /**
         * 数据类型： textHeader（标题，显示日期） 或 Video （视频）
         */
        private String type;

        private VideoEntity data;

        private String tag;

        private int id;

        private int adIndex;

        public void setType(String type) {
            this.type = type;
        }

        public void setData(VideoEntity data) {
            this.data = data;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAdIndex(int adIndex) {
            this.adIndex = adIndex;
        }

        public String getType() {
            return type;
        }

        public VideoEntity getData() {
            return data;
        }

        public String getTag() {
            return tag;
        }

        public int getId() {
            return id;
        }

        public int getAdIndex() {
            return adIndex;
        }

        public static class VideoEntity {

            /**
             * 数据类型，textHeader 或 VideoBeanForClient
             */
            private String dataType;

            /**
             * textHeader 没有，视频 id
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

            private ProviderEntity provider;

            /**
             * 视频种类（音乐、剧情等）
             */
            private String category;

            /**
             * 作者信息
             */
            private AuthorEntity author;

            /**
             * 封面预览图片
             */
            private CoverEntity cover;

            /**
             * 视频地址
             */
            private String playUrl;

            /**
             * 视频时长，单位为 秒s
             */
            private int duration;

            /**
             * WebView 加载地址，相当于 Android 加载 H5 页面
             */
            private WebUrlEntity webUrl;

            /**
             * 视频的创建时间还是什么时间，时间戳形式，可用于每日内容时，定位视频属于哪一天
             */
            private long releaseTime;

            /**
             * DAILY，具体意义不详
             */
            private String library;

            /**
             * 用户信息，包括收藏、分享、评论数量
             */
            private ConsumptionEntity consumption;

            /**
             * 不详 NULL
             */
            private Object campaign;
            private Object waterMarks;
            private Object adTrack;

            /**
             * 不详 NORMAL
             */
            private String type;

            /**
             * 不详 空
             */
            private Object titlePgc;
            private Object descriptionPgc;

            /**
             * 不详 "Teenaged Andrea uses a male stripper to gain the respect of cool girl Daphne.
             * Based on a true story.Hot Seat"
             */
            private String remark;


            private int idx;
            private Object shareAdTrack;
            private Object favoriteAdTrack;
            private Object webAdTrack;

            /**
             * 数据貌似和上面 releaseTime 一致
             */
            private long date;
            private Object promotion;
            private Object label;
            private String descriptionEditor;
            private boolean collected;
            private boolean played;
            private Object lastViewTime;
            private Object playlists;
            private Object src;
            /**
             * height : 480
             * width : 854
             * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=aliyun","size":33200203},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=qcloud","size":33200203},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=ucloud","size":33200203}]
             * name : 标清
             * type : normal
             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12513&editionType=normal&source=aliyun
             */

            private List<PlayInfoEntity> playInfo;
            /**
             * id : 340
             * name : 北欧
             * actionUrl : eyepetizer://tag/340/?title=%E5%8C%97%E6%AC%A7
             * adTrack : null
             * desc : null
             * bgPicture : http://img.kaiyanapp.com/b347db5f4b412c8c55ec819c2cd08242.jpeg?imageMogr2/quality/100
             * headerImage : http://img.kaiyanapp.com/0ce301dbd872e24ca455722eb8cd6bd1.jpeg?imageMogr2/quality/100
             * tagRecType : NORMAL
             */

            private List<TagsEntity> tags;
            private List<?> labelList;
            private List<?> subtitles;

            public void setDataType(String dataType) {
                this.dataType = dataType;
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

            public void setProvider(ProviderEntity provider) {
                this.provider = provider;
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

            public void setLibrary(String library) {
                this.library = library;
            }

            public void setConsumption(ConsumptionEntity consumption) {
                this.consumption = consumption;
            }

            public void setCampaign(Object campaign) {
                this.campaign = campaign;
            }

            public void setWaterMarks(Object waterMarks) {
                this.waterMarks = waterMarks;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setTitlePgc(Object titlePgc) {
                this.titlePgc = titlePgc;
            }

            public void setDescriptionPgc(Object descriptionPgc) {
                this.descriptionPgc = descriptionPgc;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public void setShareAdTrack(Object shareAdTrack) {
                this.shareAdTrack = shareAdTrack;
            }

            public void setFavoriteAdTrack(Object favoriteAdTrack) {
                this.favoriteAdTrack = favoriteAdTrack;
            }

            public void setWebAdTrack(Object webAdTrack) {
                this.webAdTrack = webAdTrack;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public void setPromotion(Object promotion) {
                this.promotion = promotion;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public void setDescriptionEditor(String descriptionEditor) {
                this.descriptionEditor = descriptionEditor;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public void setPlayed(boolean played) {
                this.played = played;
            }

            public void setLastViewTime(Object lastViewTime) {
                this.lastViewTime = lastViewTime;
            }

            public void setPlaylists(Object playlists) {
                this.playlists = playlists;
            }

            public void setSrc(Object src) {
                this.src = src;
            }

            public void setPlayInfo(List<PlayInfoEntity> playInfo) {
                this.playInfo = playInfo;
            }

            public void setTags(List<TagsEntity> tags) {
                this.tags = tags;
            }

            public void setLabelList(List<?> labelList) {
                this.labelList = labelList;
            }

            public void setSubtitles(List<?> subtitles) {
                this.subtitles = subtitles;
            }

            public String getDataType() {
                return dataType;
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

            public ProviderEntity getProvider() {
                return provider;
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

            public String getLibrary() {
                return library;
            }

            public ConsumptionEntity getConsumption() {
                return consumption;
            }

            public Object getCampaign() {
                return campaign;
            }

            public Object getWaterMarks() {
                return waterMarks;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public String getType() {
                return type;
            }

            public Object getTitlePgc() {
                return titlePgc;
            }

            public Object getDescriptionPgc() {
                return descriptionPgc;
            }

            public String getRemark() {
                return remark;
            }

            public int getIdx() {
                return idx;
            }

            public Object getShareAdTrack() {
                return shareAdTrack;
            }

            public Object getFavoriteAdTrack() {
                return favoriteAdTrack;
            }

            public Object getWebAdTrack() {
                return webAdTrack;
            }

            public long getDate() {
                return date;
            }

            public Object getPromotion() {
                return promotion;
            }

            public Object getLabel() {
                return label;
            }

            public String getDescriptionEditor() {
                return descriptionEditor;
            }

            public boolean isCollected() {
                return collected;
            }

            public boolean isPlayed() {
                return played;
            }

            public Object getLastViewTime() {
                return lastViewTime;
            }

            public Object getPlaylists() {
                return playlists;
            }

            public Object getSrc() {
                return src;
            }

            public List<PlayInfoEntity> getPlayInfo() {
                return playInfo;
            }

            public List<TagsEntity> getTags() {
                return tags;
            }

            public List<?> getLabelList() {
                return labelList;
            }

            public List<?> getSubtitles() {
                return subtitles;
            }

            public static class ProviderEntity {

                /**
                 * name : Vimeo
                 * alias : vimeo
                 * icon : http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png
                 */

                private String name;
                private String alias;
                private String icon;

                public void setName(String name) {
                    this.name = name;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public String getAlias() {
                    return alias;
                }

                public String getIcon() {
                    return icon;
                }
            }

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

                private int id;
                private String icon;
                private String name;
                private String description;
                private String link;
                private long latestReleaseTime;
                private int videoNum;
                private Object adTrack;
                /**
                 * itemType : author
                 * itemId : 2164
                 * followed : false
                 */

                private FollowEntity follow;
                /**
                 * itemType : author
                 * itemId : 2164
                 * shielded : false
                 */

                private ShieldEntity shield;
                private int approvedNotReadyVideoCount;
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

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public void setFollow(FollowEntity follow) {
                    this.follow = follow;
                }

                public void setShield(ShieldEntity shield) {
                    this.shield = shield;
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

                public Object getAdTrack() {
                    return adTrack;
                }

                public FollowEntity getFollow() {
                    return follow;
                }

                public ShieldEntity getShield() {
                    return shield;
                }

                public int getApprovedNotReadyVideoCount() {
                    return approvedNotReadyVideoCount;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

                public static class FollowEntity {
                    private String itemType;
                    private int itemId;
                    private boolean followed;

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public void setFollowed(boolean followed) {
                        this.followed = followed;
                    }

                    public String getItemType() {
                        return itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public boolean isFollowed() {
                        return followed;
                    }
                }

                public static class ShieldEntity {
                    private String itemType;
                    private int itemId;
                    private boolean shielded;

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public void setShielded(boolean shielded) {
                        this.shielded = shielded;
                    }

                    public String getItemType() {
                        return itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public boolean isShielded() {
                        return shielded;
                    }
                }
            }

            public static class CoverEntity {
                /**
                 * feed : http://img.kaiyanapp.com/068978f6689861edf91af6a7d4daef58.jpeg?imageMogr2/quality/60/format/jpg
                 * detail : http://img.kaiyanapp.com/068978f6689861edf91af6a7d4daef58.jpeg?imageMogr2/quality/60/format/jpg
                 * blurred : http://img.kaiyanapp.com/cf887e602b6d45ea222723a81f8b31cb.jpeg?imageMogr2/quality/60/format/jpg
                 * sharing : null
                 * homepage :
                 */
                private String feed;
                private String detail;
                private String blurred;
                private Object sharing;
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

                public void setSharing(Object sharing) {
                    this.sharing = sharing;
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

                public Object getSharing() {
                    return sharing;
                }

                public String getHomepage() {
                    return homepage;
                }
            }

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

            public static class TagsEntity {
                private int id;
                private String name;
                private String actionUrl;
                private Object adTrack;
                private Object desc;
                private String bgPicture;
                private String headerImage;
                private String tagRecType;

                public void setId(int id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public void setDesc(Object desc) {
                    this.desc = desc;
                }

                public void setBgPicture(String bgPicture) {
                    this.bgPicture = bgPicture;
                }

                public void setHeaderImage(String headerImage) {
                    this.headerImage = headerImage;
                }

                public void setTagRecType(String tagRecType) {
                    this.tagRecType = tagRecType;
                }

                public int getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public Object getDesc() {
                    return desc;
                }

                public String getBgPicture() {
                    return bgPicture;
                }

                public String getHeaderImage() {
                    return headerImage;
                }

                public String getTagRecType() {
                    return tagRecType;
                }
            }
        }
    }
}
