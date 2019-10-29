package zjol.com.cn.topic.holder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjrb.core.recycleView.PageItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.FashionRankAdapter;
import zjol.com.cn.topic.bean.TopicRankBean;

/**
 * Banner (焦点图) - ViewHolder
 *
 * @author a_liYa
 * @date 2017/7/18 10:04.
 */
public class TopicRankHeader extends PageItem {


    @BindView(R2.id.tv_full_rank)
    TextView tvFullRank;
    @BindView(R2.id.recycler)
    RecyclerView recycler;
    private List<TopicRankBean> list;
    private FashionRankAdapter mRankAdapter;

    public TopicRankHeader(ViewGroup parent) {
        super(parent, R.layout.module_news_layout_header_topic_rank);
        ButterKnife.bind(this, itemView);
        tvFullRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setData(List<TopicRankBean> topicRankBeans){
        list = topicRankBeans;
        if (mRankAdapter==null){
            mRankAdapter = new FashionRankAdapter(list);
            recycler.setLayoutManager(new GridLayoutManager(itemView.getContext(),2));
            recycler.setAdapter(mRankAdapter);
        }else {
            mRankAdapter.setData(list);
            mRankAdapter.notifyDataSetChanged();
        }
    }


    public void setVisiable(boolean visiable) {
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        if (lp != null) {
            lp.height = !visiable ? 0 : ViewGroup.LayoutParams.WRAP_CONTENT;
            itemView.setLayoutParams(lp);
        }
    }
}
