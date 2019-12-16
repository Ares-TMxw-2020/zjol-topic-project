package zjol.com.cn.topic.adapter;

import android.view.ViewGroup;

import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.adapter.BaseRecyclerAdapter;

import java.util.List;

import cn.com.zjol.biz.core.model.ShortVideoBean;
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
