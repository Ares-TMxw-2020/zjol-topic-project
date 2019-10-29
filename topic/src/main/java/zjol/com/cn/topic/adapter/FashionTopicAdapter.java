package zjol.com.cn.topic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zjrb.core.load.LoadMoreListener;
import com.zjrb.core.load.LoadingCallBack;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.FooterLoadMoreV2;
import com.zjrb.core.recycleView.LoadMore;
import com.zjrb.core.recycleView.adapter.BaseRecyclerAdapter;

import java.util.List;

import cn.com.zjol.biz.core.network.compatible.APICallManager;
import zjol.com.cn.news.home.bean.ArticleItemBean;
import zjol.com.cn.topic.bean.FashionTopicBean;
import zjol.com.cn.topic.bean.TopicSquareBean;
import zjol.com.cn.topic.holder.NewsHorizontalTopicHolder;
import zjol.com.cn.topic.task.FashionTopicTask;

/**
 * 新闻 - Adapter
 *
 * @author a_liYa
 * @date 2017/7/6 14:40.
 */
public class FashionTopicAdapter extends BaseRecyclerAdapter<TopicSquareBean> implements LoadMoreListener<FashionTopicBean> {

    private String mChannelId;
    private final FooterLoadMoreV2<FashionTopicBean> mLoadMore;

    public FashionTopicAdapter(FashionTopicBean data, ViewGroup parent, String channelId) {
        super(null);
        mChannelId = channelId;
        mLoadMore = new FooterLoadMoreV2<>((RecyclerView) parent, this);
        setFooterLoadMore(mLoadMore.itemView);
        setData(data);
    }

    public void setData(FashionTopicBean data) {
        cancelLoadMore();
        mLoadMore.setState(noMore(data) ? LoadMore.TYPE_NO_MORE : LoadMore.TYPE_IDLE);

        setData(data != null ? data.getSquares() : null);
    }

    public void addData(List<TopicSquareBean> data) {
        addData(data, true); // 增量刷新
    }

    private boolean noMore(FashionTopicBean data) {
        return data == null || data.getSquares() == null
                || data.getSquares().size() == 0;
    }

    @Override
    public void onLoadMore(LoadingCallBack<FashionTopicBean> callback) {
        new FashionTopicTask(callback).setTag(this).exe(getLastOneTag());
    }


    @Override
    public void onLoadMoreSuccess(FashionTopicBean data, LoadMore loadMore) {
        if (noMore(data)) {
            loadMore.setState(LoadMore.TYPE_NO_MORE);
        }
        if (data != null) {
            addData(data.getSquares());
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

    @Override
    public BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHorizontalTopicHolder(parent);
    }

}
