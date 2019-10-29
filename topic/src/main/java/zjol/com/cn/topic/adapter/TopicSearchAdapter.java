package zjol.com.cn.topic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zjrb.core.load.LoadMoreListener;
import com.zjrb.core.load.LoadingCallBack;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.FooterLoadMore;
import com.zjrb.core.recycleView.LoadMore;
import com.zjrb.core.recycleView.adapter.BaseRecyclerAdapter;

import java.util.List;

import zjol.com.cn.topic.bean.TopicSearchBean;
import zjol.com.cn.topic.holder.TopicSerachHolder;
import zjol.com.cn.topic.task.SearchTopicListTask;


public class TopicSearchAdapter extends BaseRecyclerAdapter<TopicSearchBean.ElementsBean> implements LoadMoreListener<TopicSearchBean> {

    private FooterLoadMore<TopicSearchBean> mLoadMore;
    private String mKeyWord;

    public TopicSearchAdapter(RecyclerView parent, List<TopicSearchBean.ElementsBean> data, String keyWord) {
        super(data);
        mKeyWord = keyWord;
        // 加载更多
        mLoadMore = new FooterLoadMore<>(parent, this);
        setFooterLoadMore(mLoadMore.itemView);
    }

    public void setKeyWord(String keyWord) {
        mKeyWord = keyWord;
    }

    @Override
    public BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicSerachHolder(parent);
    }


    @Override
    public void onLoadMoreSuccess(TopicSearchBean data, LoadMore loadMore) {
        if (noMore(data)) {
            loadMore.setState(LoadMore.TYPE_NO_MORE);
        }
        if (data != null) {
            addData(data.getElements(),true);
        }
    }

    @Override
    public void onLoadMore(LoadingCallBack<TopicSearchBean> callback) {
        new SearchTopicListTask(callback).setTag(this).exe(mKeyWord,getDataSize());
    }

    private boolean noMore(TopicSearchBean data) {
        return data == null || data.getElements() == null || data.getElements().size() == 0
                || !data.isHas_more();
    }


}
