package zjol.com.cn.topic.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.ItemClickCallback;
import com.zjrb.daily.db.dao.ReadNewsDaoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.glide.AppGlideOptions;
import zjol.com.cn.news.R;
import zjol.com.cn.news.R2;
import zjol.com.cn.news.common.utils.Format;
import zjol.com.cn.news.home.bean.ArticleItemBean;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.topic.other.GlideRoundTransform;

/**
 * @author: lujialei
 * @date: 2019/7/25
 * @describe:
 */


public class TopicHomeVideoHolder extends BaseRecyclerViewHolder<ShortVideoBean.ArticleListBean> implements ItemClickCallback {
    @BindView(R2.id.iv_picture)
    ImageView ivPicture;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_time)
    TextView tvTime;
    @BindView(R2.id.ll_time)
    LinearLayout llTime;

    public TopicHomeVideoHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.module_news_item_fashion_holder);
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
                .transform(new GlideRoundTransform(itemView.getContext(),3))
                .into(ivPicture);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        ReadNewsDaoHelper.addAlreadyRead(mData.getId());
    }
}
