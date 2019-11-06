package zjol.com.cn.topic.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.ItemClickCallback;
import com.zjrb.daily.db.dao.ReadNewsDaoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.glide.AppGlideOptions;
import zjol.com.cn.news.common.widget.GlideRoundTransform;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;

/**
 * @author: lujialei
 * @date: 2019/7/25
 * @describe:
 */


public class TopicHomeVideoHolder extends BaseRecyclerViewHolder<ShortVideoBean.ArticleListBean> implements ItemClickCallback {
    @BindView(R2.id.iv_picture)
    ImageView ivPicture;
    @BindView(R2.id.tv_tag)
    protected TextView tvTag;
    @BindView(R2.id.fl_tag)
    protected FrameLayout flTag;


    public TopicHomeVideoHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.module_topic_holder_topic_home_video);
        ButterKnife.bind(this, itemView);
        flTag.setVisibility(View.GONE);
    }

    @Override
    public void bindView() {
        String path = "";
        if (getData()!=null&&getData().getList_pics() != null && !getData().getList_pics().isEmpty()) {
            path = getData().getList_pics().get(0);
        }
        GlideApp.with(itemView.getContext())
                .load(path)
                .apply(AppGlideOptions.newsBigOptions())
                .transform(new GlideRoundTransform(itemView.getContext(), 3))
                .into(ivPicture);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        ReadNewsDaoHelper.addAlreadyRead(mData.getId());
    }
}
