package zjol.com.cn.topic.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019-10-22
 * @describe:
 */

public class FashionTopicBean {
    private List<TopicRankBean> rank;
    private List<TopicSquareBean> squares;

    public List<TopicRankBean> getRank() {
        return rank;
    }

    public void setRank(List<TopicRankBean> rank) {
        this.rank = rank;
    }

    public List<TopicSquareBean> getSquares() {
        return squares;
    }

    public void setSquares(List<TopicSquareBean> squares) {
        this.squares = squares;
    }
}
