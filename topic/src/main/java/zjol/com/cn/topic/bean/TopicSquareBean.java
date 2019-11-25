package zjol.com.cn.topic.bean;

import java.util.List;

import zjol.com.cn.player.bean.ShortVideoBean;

/**
 * @author: lujialei
 * @date: 2019-10-22
 * @describe:
 */

public class TopicSquareBean {


    /**
     * id : aaa
     * name : 家有萌宠
     * url : http://topic.com
     * participant_count : 12
     * participant_count_general : 1万
     * sort_number_double : 123.456
     * participant_account_imgs : ["http://aaa.png"]
     * articles : [{"id":123,"uuid":"aaa","list_title":"标题","list_pics":["http://aaa.png"],"url":"http://url.com","video_url":"http://video-url.com","video_duration":23,"first_cover":"a"}]
     */

    private String id;
    private String name;
    private String url;
    private int participant_count;
    private String participant_count_general;
    private double sort_number_double;
    private List<String> participant_account_imgs;
    private List<ShortVideoBean.ArticleListBean> articles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public double getSort_number_double() {
        return sort_number_double;
    }

    public void setSort_number_double(double sort_number_double) {
        this.sort_number_double = sort_number_double;
    }

    public List<String> getParticipant_account_imgs() {
        return participant_account_imgs;
    }

    public void setParticipant_account_imgs(List<String> participant_account_imgs) {
        this.participant_account_imgs = participant_account_imgs;
    }

    public List<ShortVideoBean.ArticleListBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ShortVideoBean.ArticleListBean> articles) {
        this.articles = articles;
    }

}
