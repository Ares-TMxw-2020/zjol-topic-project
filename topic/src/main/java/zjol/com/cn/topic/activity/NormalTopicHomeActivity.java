package zjol.com.cn.topic.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.EmptyPageHolder;
import com.zjrb.core.recycleView.HeaderRefresh;
import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import cn.com.zjol.biz.core.constant.C;
import cn.com.zjol.biz.core.constant.Constants;
import cn.com.zjol.biz.core.model.ArticleBean;
import cn.com.zjol.biz.core.nav.Nav;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import cn.com.zjol.biz.core.share.ShareAnalytic;
import cn.com.zjol.biz.core.share.UmengShareBean;
import cn.com.zjol.biz.core.share.UmengShareUtils;
import cn.daily.android.statusbar.DarkStatusBar;
import cn.daily.news.analytics.Analytics;
import zjol.com.cn.list.utils.NewsUtils;
import zjol.com.cn.news.common.utils.State;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.news.common.widget.GlideRoundTransform;
import zjol.com.cn.news.common.widget.NewsSpaceDivider;
import zjol.com.cn.player.utils.GlideBlurformation;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicNewsAdapter;
import zjol.com.cn.topic.bean.NormalTopicHomeBean;
import zjol.com.cn.topic.bean.TopicHomeBean;
import zjol.com.cn.topic.holder.TopicHomeEmptyPageHolder;
import zjol.com.cn.topic.other.Code;
import zjol.com.cn.topic.task.NormalTopicHomeTask;

/**
 * 潮客小视频的话题主页
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
    @BindView(R2.id.iv_spand_share)
    ImageView ivSpandShare;
    @BindView(R2.id.iv_top_bar_share)
    ImageView ivTopBarShare;
    @BindView(R2.id.iv_header)
    ImageView ivHeader;
    @BindView(R2.id.ll_top)
    RelativeLayout llTop;
    @BindView(R2.id.fl_error)
    LinearLayout flError;
    @BindView(R2.id.iv_error_back)
    ImageView ivErrorBack;
    @BindView(R2.id.error_topbar)
    RelativeLayout errorTopbar;
    @BindView(R2.id.error_view)
    View errorView;
    @BindView(R2.id.ll_with_draw)
    LinearLayout llWithDraw;
    @BindView(R2.id.error_conetent)
    FrameLayout errorConetent;
    @BindView(R2.id.iv_logo)
    ImageView ivLogo;
    private TopicNewsAdapter mAdapter;
    private LoadViewHolder mLoadViewHolder;
    private HeaderRefresh mRefresh;
    private GridLayoutManager mGridLayoutManager;
    private State mCurrentState = State.IDLE;
    private String mTopicId = "";
    private String mCurrentLogoUrl;
    private TopicHomeEmptyPageHolder mTopicHomeEmptyPageHolder;
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
        initStatusBar();
        getArgs();
        DarkStatusBar.get().fitDark(this);
        initView();
        initListener();
        loadData(true);
    }

    private void initStatusBar() {
        int statusHeight = StatusBarUtil.getStatusBarHeight(this);
        llTop.getLayoutParams().height = llTop.getLayoutParams().height += statusHeight;
        //展开状态下的topbar
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlSpandTopbar.getLayoutParams();
        layoutParams.topMargin += statusHeight;
        //收缩状态下的topbar
        rlTopbar.getLayoutParams().height += statusHeight;
        rlTopbar.setPadding(0, statusHeight, 0, 0);
        //网络错误页面topbar
        LinearLayout.LayoutParams errorLayoutParams = (LinearLayout.LayoutParams) errorTopbar.getLayoutParams();
        errorLayoutParams.height += statusHeight;
        errorTopbar.setPadding(0, statusHeight, 0, 0);
        //recyclerview设置最小高度 loadingview占位时能获取到这个高度
        mRecycler.setMinimumHeight(UIUtils.dip2px(400));

        llTop.postInvalidate();


    }

    private void getArgs() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            String topicId = getIntent().getExtras().getString(Constants.TOPIC_ID);
            if (!TextUtils.isEmpty(topicId)) {
                mTopicId = topicId;
            }
        }


        if (getIntent() != null && getIntent().getData() != null) {
            Uri uri = getIntent().getData();
            String id = uri.getQueryParameter("id");
            if (!TextUtils.isEmpty(id)) {
                mTopicId = id;
            }
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
                enableScroll();
                flError.setVisibility(View.GONE);
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
                forbidScroll();
                if (errCode == Code.CODE_TOPIC_UNDER_LINE) {
                    flError.setVisibility(View.VISIBLE);
                    showWithDrawView();
                }
            }
        }).setTag(this)
                .setShortestTime(isFirst ? 0 : C.REFRESH_SHORTEST_TIME)
                .bindLoadViewHolder(isFirst ? (mLoadViewHolder = replaceLoad(errorView)) : null)
                .exe(mTopicId);
    }

    /**
     * 禁止appbarlayout滑动
     */
    public void forbidScroll(){
        View mAppBarChildAt = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams  mAppBarParams = (AppBarLayout.LayoutParams)mAppBarChildAt.getLayoutParams();
        mAppBarParams.setScrollFlags(0);
        mAppBarChildAt.setLayoutParams(mAppBarParams);
    }

    /**
     * 允许appbarlayout滑动
     */
    public void enableScroll(){
        View mAppBarChildAt = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams  mAppBarParams = (AppBarLayout.LayoutParams)mAppBarChildAt.getLayoutParams();
        mAppBarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mAppBarChildAt.setLayoutParams(mAppBarParams);
    }


    /**
     * 显示话题下架页面
     */
    private void showWithDrawView() {
        llWithDraw.setVisibility(View.VISIBLE);
    }

    /**
     * 刷新头部图片等数据
     * @param data
     */
    private void refreshView(NormalTopicHomeBean data) {
        if (data == null) {
            return;
        }

        String name = data.getTopic_label().getName();
        if (!TextUtils.isEmpty(name)&&name.length()>0) {
            tvLogo.setText(name.substring(1, 2));
            tvTitle.setText(name);
            tvTopBarTitle.setText(name);
        }
        String logoUrl = data.getTopic_label().getLogo_url();
        if (!TextUtils.equals(mCurrentLogoUrl,logoUrl)){
            GlideApp.with(getBaseContext())
                    .load(logoUrl)
                    .centerCrop()
                    .placeholder(R.mipmap.zjov_app_header_bg)
                    .error(R.mipmap.zjov_app_header_bg)
                    .transform(new GlideBlurformation(getBaseContext(), 15))
                    .into(ivHeader);
        }
        mCurrentLogoUrl = logoUrl;
        GlideApp.with(getBaseContext())
                .load(data.getTopic_label().getLogo_url())
                .error(R.mipmap.module_biz_news_item_place_holder_small)
                .placeholder(R.mipmap.module_biz_news_item_place_holder_small)
                .transform(new GlideRoundTransform(getBaseContext(), 4))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        tvLogo.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        tvLogo.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(ivLogo);
        String des = data.getTopic_label().getAccount_name();
        if (!TextUtils.isEmpty(des)) {
            tvOther.setText("发起人:" + des);
        }
    }


    private void bindData(NormalTopicHomeBean data) {
        if (mAdapter == null) {
            mRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            mRecycler.addItemDecoration(new NewsSpaceDivider());
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
        if (mAdapter.getData(position) instanceof ArticleBean && mTopicHomeBean !=null && mTopicHomeBean.getTopic_label()!=null){
            ArticleBean article = (ArticleBean) mAdapter.getData(position);
            NewsUtils.itemClick(getBaseContext(),article);
//            Bundle bundle = new Bundle();
//            bundle.putString(Constants.ID,article.getId()+"");
//            bundle.putString(Constants.TOPIC_ID, mTopicHomeBean.getTopic_label().getId());
//            Nav.with(getBaseContext()).setExtras(bundle).toPath("/player/fullscreen/topic/vertical");


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
            Analytics.create(itemView.getContext(), "200007", "话题主页", false)
                    .selfObjectID(article.getId()+"")
                    .objectID(article.getMlf_id()+"")
                    .objectShortName(article.getList_title())
                    .ilurl(article.getUrl())
                    .classID(article.getChannel_id())
                    .classShortName(article.getChannel_name())
                    .objectType("C01")
                    .accountName(article.getAccount_nick_name())
                    .accountID(article.getAccount_id())
                    .topicName(topicName.toString())
                    .topicID(topicId.toString())
                    .build()
                    .send();
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
        } else if (v.getId() == ivTopBarShare.getId()) {
            share();
        } else if (v.getId() == ivSpandShare.getId()) {
            share();
        }
    }

    private void share() {
        if (mTopicHomeBean == null||mTopicHomeBean.getTopic_label()==null) {
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
//                .topicID(mTopicHomeBean.getTopic_label().getId())
//                .topicName(mTopicHomeBean.getTopic_label().getName())
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
                .setTextContent("来自天目新闻客户端")
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
