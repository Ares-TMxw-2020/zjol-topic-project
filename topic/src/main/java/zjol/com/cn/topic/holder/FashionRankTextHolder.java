package zjol.com.cn.topic.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.zjrb.core.recycleView.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.nav.Nav;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.bean.TopicRankBean;

/**
 * 频道 - ViewHolder
 *
 * @author a_liYa
 * @date 2017/7/23 06:42.
 */
public class FashionRankTextHolder extends BaseRecyclerViewHolder<TopicRankBean> {


    @BindView(R2.id.tv_topic)
    TextView tvTopic;

    public FashionRankTextHolder(ViewGroup parent) {
        super(inflate(R.layout.module_news_item_fashion_topic_rank, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView() {
        tvTopic.setText(mData.getName());
        Nav.with(itemView.getContext()).to(mData.getUrl());
    }
}
