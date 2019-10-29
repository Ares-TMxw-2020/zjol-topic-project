package zjol.com.cn.topic.adapter;

import android.view.ViewGroup;

import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.adapter.BaseRecyclerAdapter;

import java.util.List;

import zjol.com.cn.topic.bean.TopicRankBean;
import zjol.com.cn.topic.holder.FashionRankTextHolder;

/**
 * 新闻 - Adapter
 *
 * @author a_liYa
 * @date 2017/7/6 14:40.
 */
public class FashionRankAdapter extends BaseRecyclerAdapter<TopicRankBean>  {


    public FashionRankAdapter(List<TopicRankBean> list) {
        super(list);
    }

    @Override
    public BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return new FashionRankTextHolder(parent);
    }

}
