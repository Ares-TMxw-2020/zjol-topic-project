package zjol.com.cn.topic.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019-10-31
 * @describe:
 */

public class TopicLabelBean implements Parcelable,Serializable {
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
    private double sort_number;
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

    public double getSort_number() {
        return sort_number;
    }

    public void setSort_number(double sort_number) {
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
        dest.writeDouble(this.sort_number);
        dest.writeDouble(this.sort_number_double);
        dest.writeByte(this.enabled ? (byte) 1 : (byte) 0);
    }

    public TopicLabelBean() {
    }

    protected TopicLabelBean(Parcel in) {
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
        this.sort_number = in.readDouble();
        this.sort_number_double = in.readDouble();
        this.enabled = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TopicLabelBean> CREATOR = new Parcelable.Creator<TopicLabelBean>() {
        @Override
        public TopicLabelBean createFromParcel(Parcel source) {
            return new TopicLabelBean(source);
        }

        @Override
        public TopicLabelBean[] newArray(int size) {
            return new TopicLabelBean[size];
        }
    };
}
