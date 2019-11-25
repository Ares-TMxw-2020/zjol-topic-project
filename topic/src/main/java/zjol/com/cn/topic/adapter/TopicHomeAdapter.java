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
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.topic.bean.TopicHomeBean;
import zjol.com.cn.topic.holder.TopicHomeVideoHolder;
import zjol.com.cn.topic.holder.TopicNO1VideoHolder;
import zjol.com.cn.topic.holder.TopicNO2VideoHolder;
import zjol.com.cn.topic.holder.TopicNO3VideoHolder;
import zjol.com.cn.topic.task.TopicHomeTask;


public class TopicHomeAdapter extends BaseRecyclerAdapter<ShortVideoBean.ArticleListBean> implements LoadMoreListener<TopicHomeBean> {

    private String mTopicId;
    private int mSortBy;
    private final FooterLoadMoreV2<TopicHomeBean> mLoadMore;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NO_1 = 1;
    public static final int TYPE_NO_2 = 2;
    public static final int TYPE_NO_3 = 3;


    public void setSortBy(int sortBy) {
        mSortBy = sortBy;
    }

    public TopicHomeAdapter(TopicHomeBean data, ViewGroup parent, String topicId, int sortBy) {
        super(null);
        mTopicId = topicId;
        mSortBy = sortBy;
        mLoadMore = new FooterLoadMoreV2<>((RecyclerView) parent, this);
        setFooterLoadMore(mLoadMore.itemView);
        setData(data);
    }

    public void setData(TopicHomeBean data) {
        cancelLoadMore();
        mLoadMore.setState(noMore(data) ? LoadMore.TYPE_NO_MORE : LoadMore.TYPE_IDLE);
        setData(data != null ? data.getArticles() : null);
    }

    public void addData(List<ShortVideoBean.ArticleListBean> data) {
        addData(data, true); // 增量刷新
    }

    private boolean noMore(TopicHomeBean data) {
        return data == null || data.getArticles() == null
                || data.getArticles().size() == 0
                || !data.isHas_more();
    }

    @Override
    public void onLoadMore(LoadingCallBack<TopicHomeBean> callback) {
        new TopicHomeTask(callback).setTag(this).exe(mTopicId,mSortBy, getLastOneTag());
    }


    @Override
    public void onLoadMoreSuccess(TopicHomeBean data, LoadMore loadMore) {
        if (noMore(data)) {
            loadMore.setState(LoadMore.TYPE_NO_MORE);
        }
        if (data != null) {
            data = handleSortBy(data);
            addData(data.getArticles());
        }
    }

    private TopicHomeBean handleSortBy(TopicHomeBean data) {
        if (data==null||data.getArticles()==null){
            return data;
        }
        for (int i = 0; i < data.getArticles().size(); i++) {
            if (data.getArticles().get(i)!=null){
                data.getArticles().get(i).setSort_by(mSortBy);
            }
        }
        return data;
    }

    public void cancelLoadMore() {
        APICallManager.get().cancel(this);
    }

    private Double getLastOneTag() {
        int size = getDataSize();
        if (size > 0) {
            int count = 1;
            while (size - count >= 0) {
                Object data = getData(size - count++);
                if (data instanceof ShortVideoBean.ArticleListBean) {
                    return ((ShortVideoBean.ArticleListBean) data).getSort_number_double();
                }
            }
        }
        return null;
    }

    @Override
    public BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_NO_1){
            return new TopicNO1VideoHolder(parent);
        }else if (viewType==TYPE_NO_2){
            return new TopicNO2VideoHolder(parent);
        }else if (viewType==TYPE_NO_3){
            return new TopicNO3VideoHolder(parent);
        }
        return new TopicHomeVideoHolder(parent);
    }


    @Override
    public int getAbsItemViewType(int position) {
        if (position==0&&getDataSize()>=3&&mSortBy==0){
            return TYPE_NO_1;
        }else if (position==1&&getDataSize()>=3&&mSortBy==0){
            return TYPE_NO_2;
        }else if (position==2&&getDataSize()>=3&&mSortBy==0){
            return TYPE_NO_3;
        }
        return TYPE_NORMAL;
    }

    public int getArticleItemSize(){
        int articleSize = 0;
        if (datas!=null){
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i) instanceof ShortVideoBean.ArticleListBean){
                    articleSize++;
                }
            }
        }
        return articleSize;
    }
}
