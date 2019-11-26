package zjol.com.cn.topic.holder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaomi.push.P;
import com.zjrb.core.base.BaseFragment;
import com.zjrb.core.recycleView.BaseRecyclerViewHolder;
import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.ui.divider.ListSpaceDivider;
import com.zjrb.core.utils.click.ClickTracker;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.constant.Constants;
import cn.com.zjol.biz.core.nav.Nav;
import cn.daily.news.analytics.Analytics;
import cn.daily.news.analytics.ObjectType;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.player.manager.shortvideo.topic.TopicShortVideoPlayActivity;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicHorizontalAdapter;
import zjol.com.cn.topic.bean.TopicElementsBean;

/**
 * 新闻列表 - 栏目类型
 *
 * @author a_liYa
 * @date 2017/7/7 15:33.
 */
public class NewsHorizontalTopicHolder extends BaseRecyclerViewHolder<TopicElementsBean> implements
        OnItemClickListener,TopicFooterLeftPull.PullCallback {


    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    @BindView(R2.id.tv_topic)
    TextView tvTopic;
    @BindView(R2.id.ll_more)
    LinearLayout llMore;

    private TopicFooterLeftPull mFooterLeftPull;
    private TopicHorizontalAdapter mAdapter;

    public NewsHorizontalTopicHolder(ViewGroup parent) {
        super(parent, R.layout.module_news_item_horizontal_topic);
        ButterKnife.bind(this, itemView);
        mRecycler.setLayoutManager(
                new LinearLayoutManager(
                        parent.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFooterLeftPull = new TopicFooterLeftPull(mRecycler, this);
    }

    @Override
    public void bindView() {
        if (mData == null) return;
        tvTopic.setText(mData.getName());
        tvTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav.with(itemView.getContext()).to(mData.getUrl());
            }
        });
        //左边井号image
        Drawable drawableLeft = itemView.getContext().getResources().getDrawable(R.mipmap.zjov_ugc_topic_topic_icon);
        drawableLeft.setBounds(0,0,drawableLeft.getMinimumWidth(),drawableLeft.getMinimumHeight());
        //新 image icon
        Drawable drawableNew = itemView.getContext().getResources().getDrawable(R.mipmap.zjov_ugc_topic_new_icon);
        drawableNew.setBounds(0,0,drawableNew.getMinimumWidth(),drawableNew.getMinimumHeight());
        //热 image icon
        Drawable drawableHot = itemView.getContext().getResources().getDrawable(R.mipmap.zjov_ugc_topic_hot_icon);
        drawableHot.setBounds(0,0,drawableHot.getMinimumWidth(),drawableHot.getMinimumHeight());
        if (mData.isShow_hot()){
            tvTopic.setCompoundDrawables(drawableLeft,null,drawableHot,null);
        }else if (mData.isShow_new()){
            tvTopic.setCompoundDrawables(drawableLeft,null,drawableNew,null);
        }else {
            tvTopic.setCompoundDrawables(drawableLeft,null,null,null);
        }
        llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav.with(itemView.getContext()).to(mData.getUrl());
                Analytics.create(v.getContext(), "200024", "首页", false)
                        .name("点击更多进入话题主页")
                        .pageType("首页")
//                        .topicID(mData.getId())
//                        .topicName(mData.getName())
                        .build()
                        .send();
            }
        });
        mAdapter = new TopicHorizontalAdapter(mData.getArticle_list());
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.addFooterView(mFooterLeftPull.getItemView());
    }


    @Override
    public void onItemClick(View itemView, int position) {
        if (mAdapter != null) {
            if (ClickTracker.isDoubleClick()) return;
            Intent intent = new Intent(itemView.getContext(), TopicShortVideoPlayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.DATA, (Serializable) mAdapter.datas);
            bundle.putInt(Constants.POSITION, position);
            bundle.putString(Constants.TOPIC_ID,mData.getId());
            bundle.putInt(Constants.SORT_BY,0);
            intent.putExtras(bundle);
            itemView.getContext().startActivity(intent);



            ShortVideoBean.ArticleListBean article = mAdapter.getData(position);
            StringBuilder topicId = new StringBuilder();
            StringBuilder topicName = new StringBuilder();
            if (article.getTopic_labels()!=null){
                for (int i = 0; i < article.getTopic_labels().size(); i++) {
                    topicId.append(article.getTopic_labels().get(i).getId());
                    topicName.append(article.getTopic_labels().get(i).getName());
                    if (i!=article.getTopic_labels().size()-1){
                        topicId.append(",");
                        topicName.append(",");
                    }
                }
            }
            Analytics.create(itemView.getContext(), "200007", "首页", false)
                    .selfObjectID(article.getId()+"")
//                    .objectID(article.getMlf_id()+"")
                    .objectShortName(article.getDoc_title())
                    .ilurl(article.getUrl())
                    .classID(article.getChannel_id())
                    .classShortName(article.getChannel_name())
                    .objectType("C01")
//                    .accountName(article.getAccount_nick_name())
                    .accountID(article.getAccount_id())
//                    .topicName(topicName.toString())
//                    .topicID(topicId.toString())
                    .build()
                    .send();

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
