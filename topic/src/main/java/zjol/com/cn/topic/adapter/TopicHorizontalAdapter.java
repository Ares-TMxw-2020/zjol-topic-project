package zjol.com.cn.topic.adapter;

import android.view.ViewGroup;

import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.adapter.BaseRecyclerAdapter;

import java.util.List;

import zjol.com.cn.news.home.bean.ArticleItemBean;
import zjol.com.cn.news.home.bean.type.DocType;
import zjol.com.cn.news.home.holder.ColumnGeneralHolder;
import zjol.com.cn.news.home.holder.ColumnLiveHolder;
import zjol.com.cn.news.home.holder.ColumnVideoHolder;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.topic.holder.TopicGeneralHolder;

/**
 * 话题横向列表adapter
 */
public class TopicHorizontalAdapter extends BaseRecyclerAdapter<ShortVideoBean.ArticleListBean> {


    public TopicHorizontalAdapter(List<ShortVideoBean.ArticleListBean> list) {
        super(list);
    }

    @Override
    public BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new TopicGeneralHolder(viewGroup);
    }
}
