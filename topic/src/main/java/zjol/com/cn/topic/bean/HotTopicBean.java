package zjol.com.cn.topic.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019-10-29
 * @describe:
 */

public class HotTopicBean {

    /**
     * rank : [{"id":"aa","name":"家有萌宠","url":"url","article_count":123,"article_count_general":"1万","sort_number_double":1.5}]
     * has_more : true
     */

    private boolean has_more;
    private List<RankBean> rank;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<RankBean> getRank() {
        return rank;
    }

    public void setRank(List<RankBean> rank) {
        this.rank = rank;
    }

    public static class RankBean {
        /**
         * id : aa
         * name : 家有萌宠
         * url : url
         * article_count : 123
         * article_count_general : 1万
         * sort_number_double : 1.5
         */

        private String id;
        private String name;
        private String url;
        private int article_count;
        private String article_count_general;
        private double sort_number_double;

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

        public double getSort_number_double() {
            return sort_number_double;
        }

        public void setSort_number_double(double sort_number_double) {
            this.sort_number_double = sort_number_double;
        }
    }
}
