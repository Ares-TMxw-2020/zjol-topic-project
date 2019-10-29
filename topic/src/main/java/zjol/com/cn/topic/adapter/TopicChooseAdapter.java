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

import zjol.com.cn.topic.bean.HotTopicBean;
import zjol.com.cn.topic.holder.TopicChooseHolder;
import zjol.com.cn.topic.task.HotTopicListTask;


public class TopicChooseAdapter extends BaseRecyclerAdapter<HotTopicBean.RankBean> implements LoadMoreListener<HotTopicBean> {

    private String mChannelId;
    private String mChannelName;
    private FooterLoadMore<HotTopicBean> mLoadMore;

    public TopicChooseAdapter(RecyclerView parent, List<HotTopicBean.RankBean> data, String channelId, String channelName) {
        super(data);
        mChannelId = channelId;
        mChannelName = channelName;
        // 加载更多
        mLoadMore = new FooterLoadMore<>(parent, this);
        setFooterLoadMore(mLoadMore.itemView);
    }



    @Override
    public BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicChooseHolder(parent);
    }


    @Override
    public void onLoadMoreSuccess(HotTopicBean data, LoadMore loadMore) {
        if (noMore(data)) {
            loadMore.setState(LoadMore.TYPE_NO_MORE);
        }
        if (data != null) {
            addData(data.getRank(),true);
        }
    }

    @Override
    public void onLoadMore(LoadingCallBack<HotTopicBean> callback) {
        new HotTopicListTask(callback).setTag(this).exe(getLastOneTag());
    }

    private boolean noMore(HotTopicBean data) {
        return data == null || data.getRank() == null || data.getRank().size() == 0
                || !data.isHas_more();
    }

    private Double getLastOneTag() {
        int size = getDataSize();
        if (size > 0) {
            int count = 1;
            while (size - count >= 0) {
                Object data = getData(size - count++);
                if (data instanceof HotTopicBean.RankBean) {
                    return ((HotTopicBean.RankBean) data).getSort_number_double();
                }
            }
        }
        return null;
    }
}
