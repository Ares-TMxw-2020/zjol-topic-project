package zjol.com.cn.topic.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.ItemClickCallback;
import com.zjrb.daily.db.dao.ReadNewsDaoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.glide.AppGlideOptions;
import zjol.com.cn.news.R;
import zjol.com.cn.news.R2;
import zjol.com.cn.news.common.widget.GlideRoundTransform;
import zjol.com.cn.player.bean.ShortVideoBean;

/**
 * @author: lujialei
 * @date: 2019/7/25
 * @describe:
 */


public class TopicHomeVideoHolder extends BaseRecyclerViewHolder<ShortVideoBean.ArticleListBean> implements ItemClickCallback {
    @BindView(R2.id.iv_picture)
    ImageView ivPicture;


    public TopicHomeVideoHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.module_topic_holder_topic_home_video);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView() {
        String path = "";
        if (getData().getList_pics() != null && !getData().getList_pics().isEmpty()) {
            path = getData().getList_pics().get(0);
        }
        GlideApp.with(itemView.getContext())
                .load(path)
                .apply(AppGlideOptions.newsBigOptions())
                .transform(new GlideRoundTransform(itemView.getContext(),5))
                .into(ivPicture);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        ReadNewsDaoHelper.addAlreadyRead(mData.getId());
    }
}
