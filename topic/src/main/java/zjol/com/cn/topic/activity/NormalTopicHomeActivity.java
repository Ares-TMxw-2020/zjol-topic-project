package zjol.com.cn.topic.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.EmptyPageHolder;
import com.zjrb.core.recycleView.HeaderRefresh;
import com.zjrb.core.recycleView.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import cn.com.zjol.biz.core.constant.C;
import cn.com.zjol.biz.core.nav.Nav;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import cn.com.zjol.biz.core.share.UmengShareBean;
import cn.com.zjol.biz.core.share.UmengShareUtils;
import cn.daily.android.statusbar.DarkStatusBar;
import zjol.com.cn.news.common.utils.State;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.news.home.bean.ArticleItemBean;
import zjol.com.cn.player.utils.Constants;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicNewsAdapter;
import zjol.com.cn.topic.bean.NormalTopicHomeBean;
import zjol.com.cn.topic.task.NormalTopicHomeTask;

/**
 * 普通稿件的话题主页
 */


public class NormalTopicHomeActivity extends DailyActivity implements OnItemClickListener, HeaderRefresh.OnRefreshListener, View.OnClickListener {
    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    @BindView(R2.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R2.id.rl_topbar)
    RelativeLayout rlTopbar;
    @BindView(R2.id.rl_spand_topbar)
    RelativeLayout rlSpandTopbar;
    @BindView(R2.id.iv_spand_back)
    ImageView ivSpandBack;
    @BindView(R2.id.tv_logo)
    TextView tvLogo;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_other)
    TextView tvOther;
    @BindView(R2.id.iv_top_bar_back)
    ImageView ivTopBarBack;
    @BindView(R2.id.tv_top_bar_title)
    TextView tvTopBarTitle;
    @BindView(R2.id.iv_header)
    ImageView ivHeader;
    @BindView(R2.id.iv_spand_share)
    ImageView ivSpandShare;
    @BindView(R2.id.iv_logo)
    ImageView ivLogo;
    @BindView(R2.id.iv_top_bar_share)
    ImageView ivTopBarShare;
    private TopicNewsAdapter mAdapter;
    private LoadViewHolder mLoadViewHolder;
    private HeaderRefresh mRefresh;
    private State mCurrentState = State.IDLE;
    private String mTopicId = "";
    private NormalTopicHomeBean mTopicHomeBean;

    @Override
    public boolean isShowTopBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_news_activity_normal_topic_home);
        ButterKnife.bind(this);
        getArgs();
        DarkStatusBar.get().fitDark(this);
        initView();
        initListener();
        loadData(true);
    }

    private void getArgs() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            mTopicId = getIntent().getExtras().getString(Constants.TOPIC_ID);
        }
    }

    private void initListener() {
        ivSpandBack.setOnClickListener(this);
        ivTopBarBack.setOnClickListener(this);
        ivSpandShare.setOnClickListener(this);
        ivTopBarShare.setOnClickListener(this);
    }

    private void initView() {
        mAppBarLayout.addOnOffsetChangedListener(new MyBaseOnOffsetChangedListener());
        int statusHeight = StatusBarUtil.getStatusBarHeight(getBaseContext());
        rlTopbar.getLayoutParams().height = rlTopbar.getLayoutParams().height + statusHeight;
        rlTopbar.setPadding(0, statusHeight, 0, 0);
        rlTopbar.requestLayout();
        rlSpandTopbar.getLayoutParams().height = rlSpandTopbar.getLayoutParams().height + statusHeight;
        rlSpandTopbar.setPadding(0, statusHeight, 0, 0);
        rlSpandTopbar.requestLayout();
    }


    /**
     * 刷新数据
     *
     * @param isFirst true:页面创建加载数据； false:下拉刷新
     */
    private void loadData(final boolean isFirst) {
        if (mLoadViewHolder != null) { // 复用时撤消上次失败
            mLoadViewHolder.finishLoad();
            mLoadViewHolder = null;
        }
        new NormalTopicHomeTask(new APIExpandCallBack<NormalTopicHomeBean>() {
            @Override
            public void onSuccess(NormalTopicHomeBean data) {
                mTopicHomeBean = data;
                bindData(data);
                refreshView(data);
                mLoadViewHolder = null;
            }

            @Override
            public void onAfter() {
                if (!isFirst && mRefresh != null) {
                    mRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onError(String errMsg, int errCode) {
                if (!isFirst) {
                    super.onError(errMsg, errCode);
                }
            }
        }).setTag(this)
                .setShortestTime(isFirst ? 0 : C.REFRESH_SHORTEST_TIME)
                .bindLoadViewHolder(isFirst ? (mLoadViewHolder = replaceLoad(mRecycler)) : null)
                .exe(mTopicId);
    }

    private void refreshView(NormalTopicHomeBean data) {
        if (!TextUtils.isEmpty(data.getTopic_label().getName())) {
            String name = data.getTopic_label().getName();
            tvLogo.setText(name.substring(0, 1));
            tvTitle.setText(name);
            tvTopBarTitle.setText(name);
        }
        GlideApp.with(getBaseContext()).load(data.getTopic_label().getLogo_url()).into(ivHeader);
        GlideApp.with(getBaseContext()).load(data.getTopic_label().getLogo_url()).into(ivLogo);
        tvOther.setText(data.getTopic_label().getCreated_by());
    }


    private void bindData(NormalTopicHomeBean data) {
        if (mAdapter == null) {
            mRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
//            mRecycler.addItemDecoration(new NewsSpaceDivider());
            mAdapter = new TopicNewsAdapter(data, mRecycler, mTopicId);
            mRecycler.setAdapter(mAdapter);

            // 下拉刷新
            mRefresh = new HeaderRefresh(mRecycler, this);
            mAdapter.setHeaderRefresh(mRefresh.getItemView());

            mAdapter.setEmptyView(
                    new EmptyPageHolder(mRecycler,
                            EmptyPageHolder.ArgsBuilder.newBuilder()
                                    .resId(R.mipmap.zjov_news_blank_icon)
                                    .content("暂无内容")
                    ).itemView);

            // 条目点击
            mAdapter.setOnItemClickListener(this);
        } else {
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();
        }
        setCanRefresh(false);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        if (mAdapter.getData(position) instanceof  ArticleItemBean && mTopicHomeBean!=null && mTopicHomeBean.getTopic_label()!=null){
            ArticleItemBean bean = (ArticleItemBean) mAdapter.getData(position);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.ID,bean.getId()+"");
            bundle.putString(Constants.TOPIC_ID,mTopicHomeBean.getTopic_label().getId());
            Nav.with(getBaseContext()).setExtras(bundle).toPath("/player/fullscreen/topic/vertical");
        }

    }

    public void setCanRefresh(boolean b) {
        if (mRefresh != null) {
            mRefresh.setCanrfresh(b);
        }
    }

    @Override
    public void onRefresh() {
        if (mAdapter != null) {
            mAdapter.cancelLoadMore();
        }
        loadData(false);
    }


    private void onStateChanged(AppBarLayout appBarLayout, State state) {
        if (mRefresh == null) {
            return;
        }
        if (state == State.EXPANDED) {
            setCanRefresh(true);
        } else {//未展开状态 不允许下拉刷新
            setCanRefresh(false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivSpandBack.getId()) {
            onBackPressed();
        } else if (v.getId() == ivTopBarBack.getId()) {
            onBackPressed();
        }else if (v.getId() == ivTopBarShare.getId()) {
            share();
        } else if (v.getId() == ivSpandShare.getId()) {
            share();
        }
    }

    private void share() {
        if (mTopicHomeBean == null) {
            return;
        }
//        ShareAnalytic analytic = ShareAnalytic.create("列表页", "新闻卡片详情页")
//                .objectID(String.valueOf(mData.getMlf_id()))
//                .selfObjectID(String.valueOf(mData.getId()))
//                .objectShortName(mData.getDoc_title())
//                .accountId(mData.getAccount_id())
//                .nickName(mData.getAccount_nick_name())
//                .ilurl(mData.getUrl())
//                .classID(mData.getChannel_id())
//                .classShortName(mData.getChannel_name())
//                .objectType("C01")
//                .build();


        UmengShareUtils.getInstance().startShare(UmengShareBean.getInstance()
                .setSingle(false)
                .setShareType("视频")
//                .setCardUrl(mData.getCard_url())
                .setEventName("NewsShare")
//                .setArticleId("" + mTopicHomeBean.getTopic_label().getId())
                .setImgUri(mTopicHomeBean.getTopic_label().getLogo_url())
                .setTitle(mTopicHomeBean.getTopic_label().getName())
                .setTextContent(mTopicHomeBean.getTopic_label().getName())
//                .setAnalytic(analytic)
                .setTargetUrl(mTopicHomeBean.getTopic_label().getUrl()));
//
//        Analytics.create(itemView.getContext(), "400011", "列表页", false)
//                .build()
//                .send();
    }


    private class MyBaseOnOffsetChangedListener implements AppBarLayout.BaseOnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }
    }
}
