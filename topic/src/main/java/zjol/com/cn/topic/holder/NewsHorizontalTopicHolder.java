package zjol.com.cn.topic.holder;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjrb.core.base.BaseFragment;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.ui.divider.ListSpaceDivider;
import com.zjrb.core.utils.click.ClickTracker;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.nav.Nav;
import cn.daily.news.analytics.Analytics;
import cn.daily.news.analytics.ObjectType;
import zjol.com.cn.news.common.fragment.NewsFragment;
import zjol.com.cn.news.common.utils.NewsUtils;
import zjol.com.cn.news.home.holder.FooterLeftPull;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicHorizontalAdapter;
import zjol.com.cn.topic.bean.TopicSquareBean;

/**
 * 新闻列表 - 栏目类型
 *
 * @author a_liYa
 * @date 2017/7/7 15:33.
 */
public class NewsHorizontalTopicHolder extends BaseRecyclerViewHolder<TopicSquareBean> implements
        OnItemClickListener, FooterLeftPull.PullCallback {


    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    @BindView(R2.id.tv_topic)
    TextView tvTopic;
    @BindView(R2.id.ll_more)
    LinearLayout llMore;

    private FooterLeftPull mFooterLeftPull;
    private TopicHorizontalAdapter mAdapter;

    public NewsHorizontalTopicHolder(ViewGroup parent) {
        super(parent, R.layout.module_news_item_horizontal_topic);
        ButterKnife.bind(this, itemView);
        mRecycler.setLayoutManager(
                new LinearLayoutManager(
                        parent.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecycler.addItemDecoration(new ListSpaceDivider(5d, Color.TRANSPARENT, false, false));
        mFooterLeftPull = new FooterLeftPull(mRecycler, this);
    }

    @Override
    public void bindView() {
        if (mData == null) return;
        tvTopic.setText(mData.getName());
        llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav.with(itemView.getContext()).to(mData.getUrl());
            }
        });
        mAdapter = new TopicHorizontalAdapter(mData.getArticles());
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
//        mAdapter.addFooterView(mFooterLeftPull.getItemView());
    }


    @Override
    public void onItemClick(View itemView, int position) {
        if (mAdapter != null) {
            if (ClickTracker.isDoubleClick()) return;
            NewsUtils.itemClick(itemView.getContext(), mAdapter.getData(position));
            Fragment fragment = BaseFragment.findAttachFragmentByView(itemView);
            if (fragment instanceof NewsFragment) {
                NewsFragment news = (NewsFragment) fragment;
                ShortVideoBean.ArticleListBean article = mAdapter.getData(position);
//                String newsId = String.valueOf(article.getMlf_id());
//                if (article.getDoc_type() == 10) {
//                    newsId = String.valueOf(article.guid);
//                }
                Analytics.newBuilder(itemView.getContext(), "200003", "AppContentClick", false)
                        .name("推荐widget点击进入新闻详情页")
//                        .objectID(String.valueOf(article.getMlf_id()))
                        .objectShortName(article.getDoc_title())
                        .seObjectType(ObjectType.C01)
                        .classID(article.getChannel_id())
                        .classShortName(article.getChannel_name())
                        .pageType(news.getType() == NewsFragment.TYPE_CHANNEL ? "频道页面" : "首页")
//                        .newsID(newsId)
                        .selfNewsID(String.valueOf(article.getId()))
                        .newsTitle(article.getDoc_title())
                        .selfChannelID(article.getChannel_id())
                        .channelName(article.getChannel_name())
                        .objectType("widget")
                        .ilurl(article.getUrl())
                        .pubUrl(article.getUrl())
//                        .relatedColumn(article.getColumn_id())
                        .selfObjectID(String.valueOf(article.getId()))
                        .build().send();
            }
        }
    }


    @Override
    public void leftPull() {
        Nav.with(itemView.getContext()).to(mData.getUrl());

        if (itemView.getParent() instanceof View) {
            View parent = (View) itemView.getParent();
            if (parent.getTag(zjol.com.cn.news.R.id.tag_data) instanceof String[]) {
                String[] tagData = (String[]) parent.getTag(zjol.com.cn.news.R.id.tag_data);
                Analytics.newBuilder(itemView.getContext(), "200004", "", false)
                        .name("推荐widget滑动进入")
                        .seObjectType(ObjectType.C90)
                        .classID(tagData[0])
                        .classShortName(tagData[1])
                        .pageType(tagData[2])
                        .build().send();
            }
        }
    }

}
