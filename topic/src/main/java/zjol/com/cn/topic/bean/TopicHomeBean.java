package zjol.com.cn.topic.bean;

import java.util.List;

import cn.com.zjol.biz.core.model.TopicLabelBean;
import zjol.com.cn.player.bean.ShortVideoBean;

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
    private List<ShortVideoBean.ArticleListBean> article_list;

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

    public List<ShortVideoBean.ArticleListBean> getArticles() {
        return article_list;
    }

    public void setArticles(List<ShortVideoBean.ArticleListBean> articles) {
        this.article_list = articles;
    }

}
