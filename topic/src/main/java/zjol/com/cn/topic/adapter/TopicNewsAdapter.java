package zjol.com.cn.topic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zjrb.core.load.LoadMoreListener;
import com.zjrb.core.load.LoadingCallBack;
import com.zjrb.core.recycleView.FooterLoadMoreV2;
import com.zjrb.core.recycleView.LoadMore;

import java.util.List;
import java.util.Random;

import cn.com.zjol.biz.core.network.compatible.APICallManager;
import zjol.com.cn.news.common.adapter.NewsBaseAdapter;
import zjol.com.cn.news.home.bean.ArticleItemBean;
import zjol.com.cn.news.home.bean.ColumnWidget;
import zjol.com.cn.news.home.bean.DataArticleList;
import zjol.com.cn.news.home.task.ChannelListTask;
import zjol.com.cn.topic.bean.NormalTopicHomeBean;
import zjol.com.cn.topic.task.NormalTopicHomeTask;

/**
 * 新闻 - Adapter
 *
 * @author a_liYa
 * @date 2017/7/6 14:40.
 */
public class TopicNewsAdapter extends NewsBaseAdapter implements LoadMoreListener<NormalTopicHomeBean> {

    private String mTopicId;
    private int mLastIndex; // 推荐栏目上一个插入的位置
    private Random mRandom = new Random();
    private final FooterLoadMoreV2<NormalTopicHomeBean> mLoadMore;


    public TopicNewsAdapter(NormalTopicHomeBean data, ViewGroup parent, String topicId) {
        super(null);
        mTopicId = topicId;
        mLoadMore = new FooterLoadMoreV2<>((RecyclerView) parent, this);
        setFooterLoadMore(mLoadMore.itemView);
        setData(data);
    }

    public void setData(NormalTopicHomeBean data) {
        mLastIndex = 0;
        cancelLoadMore();
        mLoadMore.setState(noMore(data) ? LoadMore.TYPE_NO_MORE : LoadMore.TYPE_IDLE);

        setData(data != null ? data.getArticles() : null);
    }

    public void addData(List<ArticleItemBean> data) {
        addData(data, true); // 增量刷新
    }

    private boolean noMore(NormalTopicHomeBean data) {
        return data == null || data.getArticles() == null
                || data.getArticles().size() == 0
                || !data.isHas_more();
    }

    @Override
    public void onLoadMore(LoadingCallBack<NormalTopicHomeBean> callback) {
        new NormalTopicHomeTask(callback).setTag(this).exe(mTopicId, getLastOneTag());
    }


    @Override
    public void onLoadMoreSuccess(NormalTopicHomeBean data, LoadMore loadMore) {
        if (noMore(data)) {
            loadMore.setState(LoadMore.TYPE_NO_MORE);
        }
        if (data != null) {
            addData(data.getArticles());
        }
    }

    public void cancelLoadMore() {
        APICallManager.get().cancel(this);
    }

    private Long getLastOneTag() {
        int size = getDataSize();
        if (size > 0) {
            int count = 1;
            while (size - count >= 0) {
                Object data = getData(size - count++);
                if (data instanceof ArticleItemBean) {
                    return ((ArticleItemBean) data).getSort_number();
                }
            }
        }
        return null;
    }

}
