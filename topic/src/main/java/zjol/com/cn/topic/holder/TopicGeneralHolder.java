package zjol.com.cn.topic.holder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.glide.AppGlideOptions;
import zjol.com.cn.news.common.widget.GlideRoundTransform;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;

public class TopicGeneralHolder extends BaseRecyclerViewHolder<ShortVideoBean.ArticleListBean> {


    @BindView(R2.id.iv)
    ImageView iv;

    public TopicGeneralHolder(ViewGroup parent) {
        super(parent, R.layout.module_topic_item_horizontal_hoder);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView() {
        GlideApp.with(itemView.getContext())
                .load(mData.getFirstPic())
                .apply(AppGlideOptions.newsBigOptions())
                .transform(new GlideRoundTransform(itemView.getContext(),3))
                .into(iv);
    }

}
