package zjol.com.cn.topic.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.zjrb.core.recycleView.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.bean.HotTopicBean;


public class TopicChooseHolder extends BaseRecyclerViewHolder<HotTopicBean.RankBean> {

    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_other)
    TextView tvOther;

    public TopicChooseHolder(ViewGroup parent) {
        super(inflate(R.layout.module_news_item_topic_choose_holder, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView() {
        tvTitle.setText(mData.getName());
        tvOther.setText(mData.getArticle_count_general());
    }

}
