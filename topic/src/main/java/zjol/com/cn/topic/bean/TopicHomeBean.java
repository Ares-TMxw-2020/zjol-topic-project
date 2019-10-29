package zjol.com.cn.topic.bean;

import java.util.List;

import zjol.com.cn.news.home.bean.ArticleItemBean;

/**
 * @author: lujialei
 * @date: 2019-10-29
 * @describe:
 */

public class TopicHomeBean {

    /**
     * sort_by : 1
     * topic_label : {"id":"abcd","auto_pk":1,"name":"家有萌宠","description":"测试描述","type":1,"logo_url":"logo","url":"url","account_id":"5497cd87498edbd5586a4712","account_name":"雨过天晴云开雾散","created_by":"2","updated_by":"3","article_count":6,"article_count_general":"6","weekly_article_count":0,"weekly_article_count_general":"","like_count":0,"like_count_general":"","participant_count":0,"participant_count_general":"","created_at":1571710673000,"updated_at":1571735886000,"sort_number":0,"sort_number_double":1.5717106730001E12,"enabled":true}
     * articles : [{"id":23,"uuid":"5daeb48e56cfa339f819f41b","mlf_id":84353,"list_title":"金华安地桂花飘香","list_pics":["https://app-stc.zjol.com.cn/assets/20191022/1571712010386_5dae6c0a93269600018c9a38.jpg"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"http://10.100.62.206/short_video.html?id=5daeb48e56cfa339f819f41b&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d36614c29545240b9c532a2","channel_name":"潮客","channel_code":"chaoke","author":"高山1205","author_img":"https://stc-new.8531.cn/assets/20171207/1512637911001_5a2905d7b4a13d1851b04e0f.png?w=174&h=174","sort_number":1571730570280,"sort_number_double":0.23,"published_timestamp":1571730570280,"published_at":1571730570000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3814747348996_hd.mp4?width=592&height=1280&isVertical=1","video_duration":25,"video_size":7324303,"video_id":3757823219333,"status":4,"visible":true,"account_id":"5b2376bca45a590550f89bf9","account_nick_name":"高山1205","author_url":"http://10.100.62.206/native/user_page.html?id=5b2376bca45a590550f89bf9","own":true,"follow_status":0,"first_cover":""}]
     * has_more : false
     */

    private int sort_by;
    private TopicLabelBean topic_label;
    private boolean has_more;
    private List<ArticleItemBean> articles;

    public int getSort_by() {
        return sort_by;
    }

    public void setSort_by(int sort_by) {
        this.sort_by = sort_by;
    }

    public TopicLabelBean getTopic_label() {
        return topic_label;
    }

    public void setTopic_label(TopicLabelBean topic_label) {
        this.topic_label = topic_label;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<ArticleItemBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleItemBean> articles) {
        this.articles = articles;
    }

    public static class TopicLabelBean {
        /**
         * id : abcd
         * auto_pk : 1
         * name : 家有萌宠
         * description : 测试描述
         * type : 1
         * logo_url : logo
         * url : url
         * account_id : 5497cd87498edbd5586a4712
         * account_name : 雨过天晴云开雾散
         * created_by : 2
         * updated_by : 3
         * article_count : 6
         * article_count_general : 6
         * weekly_article_count : 0
         * weekly_article_count_general :
         * like_count : 0
         * like_count_general :
         * participant_count : 0
         * participant_count_general :
         * created_at : 1571710673000
         * updated_at : 1571735886000
         * sort_number : 0
         * sort_number_double : 1.5717106730001E12
         * enabled : true
         */

        private String id;
        private int auto_pk;
        private String name;
        private String description;
        private int type;
        private String logo_url;
        private String url;
        private String account_id;
        private String account_name;
        private String created_by;
        private String updated_by;
        private int article_count;
        private String article_count_general;
        private int weekly_article_count;
        private String weekly_article_count_general;
        private int like_count;
        private String like_count_general;
        private int participant_count;
        private String participant_count_general;
        private long created_at;
        private long updated_at;
        private int sort_number;
        private double sort_number_double;
        private boolean enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAuto_pk() {
            return auto_pk;
        }

        public void setAuto_pk(int auto_pk) {
            this.auto_pk = auto_pk;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getUpdated_by() {
            return updated_by;
        }

        public void setUpdated_by(String updated_by) {
            this.updated_by = updated_by;
        }

        public int getArticle_count() {
            return article_count;
        }

        public void setArticle_count(int article_count) {
            this.article_count = article_count;
        }

        public String getArticle_count_general() {
            return article_count_general;
        }

        public void setArticle_count_general(String article_count_general) {
            this.article_count_general = article_count_general;
        }

        public int getWeekly_article_count() {
            return weekly_article_count;
        }

        public void setWeekly_article_count(int weekly_article_count) {
            this.weekly_article_count = weekly_article_count;
        }

        public String getWeekly_article_count_general() {
            return weekly_article_count_general;
        }

        public void setWeekly_article_count_general(String weekly_article_count_general) {
            this.weekly_article_count_general = weekly_article_count_general;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public String getLike_count_general() {
            return like_count_general;
        }

        public void setLike_count_general(String like_count_general) {
            this.like_count_general = like_count_general;
        }

        public int getParticipant_count() {
            return participant_count;
        }

        public void setParticipant_count(int participant_count) {
            this.participant_count = participant_count;
        }

        public String getParticipant_count_general() {
            return participant_count_general;
        }

        public void setParticipant_count_general(String participant_count_general) {
            this.participant_count_general = participant_count_general;
        }

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
        }

        public int getSort_number() {
            return sort_number;
        }

        public void setSort_number(int sort_number) {
            this.sort_number = sort_number;
        }

        public double getSort_number_double() {
            return sort_number_double;
        }

        public void setSort_number_double(double sort_number_double) {
            this.sort_number_double = sort_number_double;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
