package zjol.com.cn.topic.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import cn.com.zjol.biz.core.model.ShortVideoBean;

public class TopicElementsBean implements Parcelable {
        /**
         * id : 1a
         * auto_pk : 1
         * name : 家有萌宠
         * description : 描述
         * type : 1
         * logo_url : logo_url
         * url : https://apibeta.zjol.com.cn/topic_label.html?id=1a
         * account_id : 5d5c9915c77ba624d09b8ff2
         * account_name : 读友_gosnxw_merge
         * created_by :
         * updated_by : 59a94dfcddf6b041182a9f56
         * article_count : 7
         * article_count_general : 7
         * weekly_article_count : 7
         * weekly_article_count_general : 7
         * like_count : 0
         * like_count_general :
         * participant_count : 0
         * participant_count_general :
         * created_at : 1572260008000
         * updated_at : 1572851675000
         * sort_number : 1572331888731
         * enabled : true
         * article_list : [{"id":373,"uuid":"5dbfcfd58e79b840419815c8","mlf_id":42992,"list_title":"3话题稿件#家有萌宠#","list_pics":["https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190905/1567668165256_5d70b7c58e79b80b90ef1db0.png"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"https://apibeta.zjol.com.cn/short_video.html?id=5dbfcfd58e79b840419815c8&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d6f42348e79b87eba1e5538","channel_name":"潮客","channel_code":"chaoke","author":"zsg","author_img":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190909/1568032016881_5d7645108e79b869fa8e1bcf.jpeg","sort_number":1572851669163,"published_timestamp":1572851669163,"published_at":1572851669000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3886497291780_hd.mp4?width=720&height=1280&isVertical=1","video_duration":11,"video_size":3796773,"video_id":3632093759365,"status":4,"visible":true,"account_id":"5d8827c58e79b81c6abad72e","account_nick_name":"zsg","author_url":"https://apibeta.zjol.com.cn/native/user_page.html?id=5d8827c58e79b81c6abad72e","own":false,"follow_status":0,"first_cover":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20191104/1572851671183_5dbfcfd78e79b840419815cc.jpeg","topic_labels":[{"id":"1a","name":"#家有萌宠#","url":"https://apibeta.zjol.com.cn/topic_label.html?id=1a"}]},{"id":372,"uuid":"5dbfcfd58e79b840419815c6","mlf_id":42995,"list_title":"6话题稿件#家有萌宠#","list_pics":["https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190905/1567668165256_5d70b7c58e79b80b90ef1db0.png"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"https://apibeta.zjol.com.cn/short_video.html?id=5dbfcfd58e79b840419815c6&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d6f42348e79b87eba1e5538","channel_name":"潮客","channel_code":"chaoke","author":"zsg","author_img":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190909/1568032016881_5d7645108e79b869fa8e1bcf.jpeg","sort_number":1572851669116,"published_timestamp":1572851669116,"published_at":1572851669000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3886497289732_hd.mp4?width=720&height=1280&isVertical=1","video_duration":11,"video_size":3796773,"video_id":3632093759365,"status":4,"visible":true,"account_id":"5d8827c58e79b81c6abad72e","account_nick_name":"zsg","author_url":"https://apibeta.zjol.com.cn/native/user_page.html?id=5d8827c58e79b81c6abad72e","own":false,"follow_status":0,"first_cover":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20191104/1572851671201_5dbfcfd78e79b840419815d0.jpeg","topic_labels":[{"id":"1a","name":"#家有萌宠#","url":"https://apibeta.zjol.com.cn/topic_label.html?id=1a"}]},{"id":371,"uuid":"5dbfcfd58e79b840419815c7","mlf_id":42993,"list_title":"4话题稿件#家有萌宠#","list_pics":["https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190905/1567668165256_5d70b7c58e79b80b90ef1db0.png"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"https://apibeta.zjol.com.cn/short_video.html?id=5dbfcfd58e79b840419815c7&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d6f42348e79b87eba1e5538","channel_name":"潮客","channel_code":"chaoke","author":"zsg","author_img":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190909/1568032016881_5d7645108e79b869fa8e1bcf.jpeg","sort_number":1572851669140,"published_timestamp":1572851669140,"published_at":1572851669000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3886497291076_hd.mp4?width=720&height=1280&isVertical=1","video_duration":11,"video_size":3796773,"video_id":3632093759365,"status":4,"visible":true,"account_id":"5d8827c58e79b81c6abad72e","account_nick_name":"zsg","author_url":"https://apibeta.zjol.com.cn/native/user_page.html?id=5d8827c58e79b81c6abad72e","own":false,"follow_status":0,"first_cover":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20191104/1572851671178_5dbfcfd78e79b840419815cb.jpeg","topic_labels":[{"id":"1a","name":"#家有萌宠#","url":"https://apibeta.zjol.com.cn/topic_label.html?id=1a"}]},{"id":370,"uuid":"5dbfcfd58e79b840419815c5","mlf_id":42994,"list_title":"5话题稿件#家有萌宠#","list_pics":["https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190905/1567668165256_5d70b7c58e79b80b90ef1db0.png"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"https://apibeta.zjol.com.cn/short_video.html?id=5dbfcfd58e79b840419815c5&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d6f42348e79b87eba1e5538","channel_name":"潮客","channel_code":"chaoke","author":"zsg","author_img":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190909/1568032016881_5d7645108e79b869fa8e1bcf.jpeg","sort_number":1572851669090,"published_timestamp":1572851669090,"published_at":1572851669000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3886497288388_hd.mp4?width=720&height=1280&isVertical=1","video_duration":11,"video_size":3796773,"video_id":3632093759365,"status":4,"visible":true,"account_id":"5d8827c58e79b81c6abad72e","account_nick_name":"zsg","author_url":"https://apibeta.zjol.com.cn/native/user_page.html?id=5d8827c58e79b81c6abad72e","own":false,"follow_status":0,"first_cover":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20191104/1572851671184_5dbfcfd78e79b840419815cd.jpeg","topic_labels":[{"id":"1a","name":"#家有萌宠#","url":"https://apibeta.zjol.com.cn/topic_label.html?id=1a"}]},{"id":369,"uuid":"5dbfcfd58e79b840419815ca","mlf_id":42990,"list_title":"1话题稿件#家有萌宠#","list_pics":["https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190905/1567668165256_5d70b7c58e79b80b90ef1db0.png"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"https://apibeta.zjol.com.cn/short_video.html?id=5dbfcfd58e79b840419815ca&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d6f42348e79b87eba1e5538","channel_name":"潮客","channel_code":"chaoke","author":"zsg","author_img":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190909/1568032016881_5d7645108e79b869fa8e1bcf.jpeg","sort_number":1572851669210,"published_timestamp":1572851669210,"published_at":1572851669000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3886497295108_hd.mp4?width=720&height=1280&isVertical=1","video_duration":11,"video_size":3796773,"video_id":3632093759365,"status":4,"visible":true,"account_id":"5d8827c58e79b81c6abad72e","account_nick_name":"zsg","author_url":"https://apibeta.zjol.com.cn/native/user_page.html?id=5d8827c58e79b81c6abad72e","own":false,"follow_status":0,"first_cover":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20191104/1572851671188_5dbfcfd78e79b840419815ce.jpeg","topic_labels":[{"id":"1a","name":"#家有萌宠#","url":"https://apibeta.zjol.com.cn/topic_label.html?id=1a"}]},{"id":368,"uuid":"5dbfcfd58e79b840419815c9","mlf_id":42991,"list_title":"2话题稿件#家有萌宠#","list_pics":["https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190905/1567668165256_5d70b7c58e79b80b90ef1db0.png"],"list_type":2,"doc_type":10,"doc_category":2,"comment_count":0,"comment_count_general":"","url":"https://apibeta.zjol.com.cn/short_video.html?id=5dbfcfd58e79b840419815c9&H5Vertical=1","liked":false,"like_enabled":true,"comment_level":1,"channel_id":"5d6f42348e79b87eba1e5538","channel_name":"潮客","channel_code":"chaoke","author":"zsg","author_img":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20190909/1568032016881_5d7645108e79b869fa8e1bcf.jpeg","sort_number":1572851669186,"published_timestamp":1572851669186,"published_at":1572851669000,"timeline":"1小时前","video_type":1,"video_url":"https://testmc-public.tmuyun.com/3886497293956_hd.mp4?width=720&height=1280&isVertical=1","video_duration":11,"video_size":3796773,"video_id":3632093759365,"status":4,"visible":true,"account_id":"5d8827c58e79b81c6abad72e","account_nick_name":"zsg","author_url":"https://apibeta.zjol.com.cn/native/user_page.html?id=5d8827c58e79b81c6abad72e","own":false,"follow_status":0,"first_cover":"https://zjolapp-dev.oss-cn-hangzhou.aliyuncs.com/assets/20191104/1572851671189_5dbfcfd78e79b840419815cf.jpeg","topic_labels":[{"id":"1a","name":"#家有萌宠#","url":"https://apibeta.zjol.com.cn/topic_label.html?id=1a"}]}]
         * class_ids : 1
         * show_style : 0
         * audit_status : 1
         * deleted : false
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
        private long sort_number;
        private boolean enabled;
        private String class_ids;
        private int show_style;
        private int audit_status;
        private boolean deleted;
        private boolean show_new;
        private boolean show_hot;
        private List<ShortVideoBean.ArticleListBean> article_list;

    public boolean isShow_new() {
        return show_new;
    }

    public void setShow_new(boolean show_new) {
        this.show_new = show_new;
    }

    public boolean isShow_hot() {
        return show_hot;
    }

    public void setShow_hot(boolean show_hot) {
        this.show_hot = show_hot;
    }

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

        public long getSort_number() {
            return sort_number;
        }

        public void setSort_number(long sort_number) {
            this.sort_number = sort_number;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getClass_ids() {
            return class_ids;
        }

        public void setClass_ids(String class_ids) {
            this.class_ids = class_ids;
        }

        public int getShow_style() {
            return show_style;
        }

        public void setShow_style(int show_style) {
            this.show_style = show_style;
        }

        public int getAudit_status() {
            return audit_status;
        }

        public void setAudit_status(int audit_status) {
            this.audit_status = audit_status;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public List<ShortVideoBean.ArticleListBean> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<ShortVideoBean.ArticleListBean> article_list) {
            this.article_list = article_list;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.auto_pk);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.type);
        dest.writeString(this.logo_url);
        dest.writeString(this.url);
        dest.writeString(this.account_id);
        dest.writeString(this.account_name);
        dest.writeString(this.created_by);
        dest.writeString(this.updated_by);
        dest.writeInt(this.article_count);
        dest.writeString(this.article_count_general);
        dest.writeInt(this.weekly_article_count);
        dest.writeString(this.weekly_article_count_general);
        dest.writeInt(this.like_count);
        dest.writeString(this.like_count_general);
        dest.writeInt(this.participant_count);
        dest.writeString(this.participant_count_general);
        dest.writeLong(this.created_at);
        dest.writeLong(this.updated_at);
        dest.writeLong(this.sort_number);
        dest.writeByte(this.enabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.class_ids);
        dest.writeInt(this.show_style);
        dest.writeInt(this.audit_status);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.show_new ? (byte) 1 : (byte) 0);
        dest.writeByte(this.show_hot ? (byte) 1 : (byte) 0);
        dest.writeList(this.article_list);
    }

    public TopicElementsBean() {
    }

    protected TopicElementsBean(Parcel in) {
        this.id = in.readString();
        this.auto_pk = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.type = in.readInt();
        this.logo_url = in.readString();
        this.url = in.readString();
        this.account_id = in.readString();
        this.account_name = in.readString();
        this.created_by = in.readString();
        this.updated_by = in.readString();
        this.article_count = in.readInt();
        this.article_count_general = in.readString();
        this.weekly_article_count = in.readInt();
        this.weekly_article_count_general = in.readString();
        this.like_count = in.readInt();
        this.like_count_general = in.readString();
        this.participant_count = in.readInt();
        this.participant_count_general = in.readString();
        this.created_at = in.readLong();
        this.updated_at = in.readLong();
        this.sort_number = in.readLong();
        this.enabled = in.readByte() != 0;
        this.class_ids = in.readString();
        this.show_style = in.readInt();
        this.audit_status = in.readInt();
        this.deleted = in.readByte() != 0;
        this.show_new = in.readByte() != 0;
        this.show_hot = in.readByte() != 0;
        this.article_list = new ArrayList<ShortVideoBean.ArticleListBean>();
        in.readList(this.article_list, ShortVideoBean.ArticleListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TopicElementsBean> CREATOR = new Parcelable.Creator<TopicElementsBean>() {
        @Override
        public TopicElementsBean createFromParcel(Parcel source) {
            return new TopicElementsBean(source);
        }

        @Override
        public TopicElementsBean[] newArray(int size) {
            return new TopicElementsBean[size];
        }
    };
}