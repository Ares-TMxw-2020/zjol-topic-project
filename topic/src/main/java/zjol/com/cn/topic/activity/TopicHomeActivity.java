package zjol.com.cn.topic.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.core.network.api.ApiCall;
import com.zjrb.core.common.glide.GlideApp;
import com.zjrb.core.recycleView.HeaderRefresh;
import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.ui.divider.GridSpaceDivider;
import com.zjrb.core.utils.T;
import com.zjrb.core.utils.UIUtils;
import com.zjrb.core.utils.click.ClickTracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import cn.com.zjol.biz.core.UserBiz;
import cn.com.zjol.biz.core.callbacks.OnCollectListener;
import cn.com.zjol.biz.core.constant.C;
import cn.com.zjol.biz.core.constant.Constants;
import cn.com.zjol.biz.core.constant.IKey;
import cn.com.zjol.biz.core.nav.Nav;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import cn.com.zjol.biz.core.share.CUSTOM_SHARE_MEDIA;
import cn.com.zjol.biz.core.share.OnCustomShareMediaListener;
import cn.com.zjol.biz.core.share.ShareAnalytic;
import cn.com.zjol.biz.core.share.UmengShareBean;
import cn.com.zjol.biz.core.share.UmengShareUtils;
import cn.daily.android.statusbar.DarkStatusBar;
import cn.daily.news.analytics.Analytics;
import zjol.com.cn.list.utils.TypeFaceUtils;
import zjol.com.cn.news.common.utils.State;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.news.common.widget.GlideRoundTransform;
import cn.com.zjol.biz.core.model.ShortVideoBean;
import zjol.com.cn.player.manager.shortvideo.topic.TopicShortVideoPlayActivity;
import zjol.com.cn.player.utils.GlideBlurformation;
import zjol.com.cn.player.utils.LocalFollowChangeManager;
import zjol.com.cn.player.utils.LocalLikeChangeManager;
import zjol.com.cn.player.utils.PageDataManager;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicHomeAdapter;
import zjol.com.cn.topic.bean.TopicHomeBean;
import zjol.com.cn.topic.holder.TopicHomeEmptyPageHolder;
import zjol.com.cn.topic.other.AnalyTopicUtils;
import zjol.com.cn.topic.other.Code;
import zjol.com.cn.topic.task.TopicHomeTask;

/**
 * 潮客小视频的话题主页
 */


public class TopicHomeActivity extends DailyActivity implements OnItemClickListener, HeaderRefresh.OnRefreshListener, View.OnClickListener, LocalFollowChangeManager.OnFollowChangeListener, LocalLikeChangeManager.OnLikeChangeListener {
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
    @BindView(R2.id.tv_video)
    TextView tvVideo;
    @BindView(R2.id.tv_prise)
    TextView tvPrise;
    @BindView(R2.id.tv_shot)
    TextView tvShot;
    @BindView(R2.id.tv_hot_new)
    TextView tvHotNew;
    @BindView(R2.id.iv_top_bar_back)
    ImageView ivTopBarBack;
    @BindView(R2.id.tv_top_bar_title)
    TextView tvTopBarTitle;
    @BindView(R2.id.iv_spand_share)
    ImageView ivSpandShare;
    @BindView(R2.id.iv_top_bar_share)
    ImageView ivTopBarShare;
    @BindView(R2.id.iv_logo)
    ImageView ivLogo;
    @BindView(R2.id.iv_header)
    ImageView ivHeader;
    @BindView(R2.id.ll_bottom)
    LinearLayout llBottom;
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
    public static final int LOGIN_REQUEST_CODE = 305;
    @BindView(R2.id.ll_hot_new)
    RelativeLayout llHotNew;
    private TopicHomeAdapter mAdapter;
    private LoadViewHolder mLoadViewHolder;
    private HeaderRefresh mRefresh;
    private GridLayoutManager mGridLayoutManager;
    private State mCurrentState = State.IDLE;
    private String mTopicId = "";
    private int mSortBy = 0;//0最热 1最新
    private TopicHomeBean mTopicHomeBean;
    private String mCurrentLogoUrl;
    private ApiCall mSwitchCall;
    private TopicHomeEmptyPageHolder mTopicHomeEmptyPageHolder;

    @Override
    public boolean isShowTopBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_news_activity_topic_home);
        ButterKnife.bind(this);
        initStatusBar();
        getArgs();
        DarkStatusBar.get().fitDark(this);
        initView();
        initListener();
        loadData(true, mSortBy);
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
        tvShot.setOnClickListener(this);
        llHotNew.setOnClickListener(this);
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
    private void loadData(final boolean isFirst, final int sortBy) {
        if (mLoadViewHolder != null) { // 复用时撤消上次失败
            mLoadViewHolder.finishLoad();
            mLoadViewHolder = null;
        }
        new TopicHomeTask(new APIExpandCallBack<TopicHomeBean>() {
            @Override
            public void onSuccess(TopicHomeBean data) {
                flError.setVisibility(View.GONE);
                data = handleSortBy(data);
                mTopicHomeBean = data;
                bindData(data, sortBy);
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
                flError.setVisibility(View.VISIBLE);
                if (!isFirst) {
                    super.onError(errMsg, errCode);
                }
                if (errCode == Code.CODE_TOPIC_UNDER_LINE) {
                    showWithDrawView();
                }
            }
        }).setTag(this)
                .setShortestTime(isFirst ? 0 : C.REFRESH_SHORTEST_TIME)
                .bindLoadViewHolder(isFirst ? (mLoadViewHolder = replaceLoad(errorView)) : null)
                .exe(mTopicId, sortBy);
    }

    //处理sortby
    private TopicHomeBean handleSortBy(TopicHomeBean data) {
        if (data == null || data.getArticles() == null) {
            return data;
        }
        for (int i = 0; i < data.getArticles().size(); i++) {
            if (data.getArticles().get(i) != null) {
                data.getArticles().get(i).setSort_by(mSortBy);
            }
        }
        return data;
    }

    /**
     * 显示话题下架页面
     */
    private void showWithDrawView() {
        llWithDraw.setVisibility(View.VISIBLE);
    }

    /**
     * 刷新头部图片等数据
     *
     * @param data
     */
    private void refreshView(TopicHomeBean data) {
        if (data == null) {
            return;
        }
        if (data.getArticles() == null || data.getArticles().isEmpty()) {
            llBottom.setVisibility(View.GONE);
        } else {
            llBottom.setVisibility(View.VISIBLE);
        }
        String name = data.getTopic_label().getName();
        if (!TextUtils.isEmpty(name) && name.length() > 0) {
            tvLogo.setText(name.substring(1, 2));
            tvTitle.setText(name);
            tvTopBarTitle.setText(name);
        }
        String logoUrl = data.getTopic_label().getLogo_url();
        if (!TextUtils.equals(mCurrentLogoUrl, logoUrl)) {
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
                .transform(new GlideRoundTransform(getBaseContext(), 4))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        tvLogo.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        tvLogo.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(ivLogo);
        String videoNumber = data.getTopic_label().getArticle_count_general();
        if (TextUtils.isEmpty(videoNumber)) {
            tvVideo.setVisibility(View.GONE);
        } else {
            tvVideo.setVisibility(View.VISIBLE);
            tvVideo.setText("视频  " + videoNumber);
        }

        String likeNumber = data.getTopic_label().getLike_count_general();
        if (TextUtils.isEmpty(likeNumber)) {
            tvPrise.setVisibility(View.GONE);
        } else {
            tvPrise.setVisibility(View.VISIBLE);
            tvPrise.setText("点赞  " + likeNumber);
        }
        TypeFaceUtils.changeNumberFont(tvVideo);
        TypeFaceUtils.changeNumberFont(tvPrise);
        String des = data.getTopic_label().getAccount_name();
        if (!TextUtils.isEmpty(des)) {
            tvOther.setText("发起人:" + des);
        }
    }

    private void bindData(TopicHomeBean data, int sortBy) {
        if (mAdapter == null) {
            mGridLayoutManager = new GridLayoutManager(getBaseContext(), 3);
            mRecycler.setLayoutManager(mGridLayoutManager);
            mRecycler.addItemDecoration(new GridSpaceDivider(4));
            mAdapter = new TopicHomeAdapter(data, mRecycler, mTopicId, sortBy);
            mRecycler.setAdapter(mAdapter);
            // 下拉刷新
            mRefresh = new HeaderRefresh(mRecycler, this);
            mAdapter.setHeaderRefresh(mRefresh.getItemView());
            //空页面
            mTopicHomeEmptyPageHolder = new TopicHomeEmptyPageHolder(mRecycler);
            mTopicHomeEmptyPageHolder.setData(data);
            mAdapter.setEmptyView(mTopicHomeEmptyPageHolder.itemView);
            // 条目点击
            mAdapter.setOnItemClickListener(this);
        } else {
            mTopicHomeEmptyPageHolder.setData(data);
            mAdapter.setSortBy(sortBy);
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();
        }
        setCanRefresh(true);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        if (ClickTracker.isDoubleClick()) return;
        Intent intent = new Intent(itemView.getContext(), TopicShortVideoPlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.POSITION, position);
        bundle.putString(Constants.TOPIC_ID, mTopicId);
        bundle.putInt(Constants.SORT_BY, mSortBy);
        intent.putExtras(bundle);
        PageDataManager.getInstance().setUGCTopicVideoList(mAdapter.getData());
        itemView.getContext().startActivity(intent);


        ShortVideoBean.ArticleListBean article = mAdapter.getData(position);
        StringBuilder topicId = new StringBuilder();
        StringBuilder topicName = new StringBuilder();
        if (article.getTopic_labels() != null) {
            for (int i = 0; i < article.getTopic_labels().size(); i++) {
                topicId.append(article.getTopic_labels().get(i).getId());
                topicName.append(article.getTopic_labels().get(i).getName());
                if (i != article.getTopic_labels().size() - 1) {
                    topicId.append(",");
                    topicName.append(",");
                }
            }
        }
        Analytics.create(itemView.getContext(), "200007", "话题主页", false)
                .selfObjectID(article.getId() + "")
                .objectID(article.getMlf_id() + "")
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
        loadData(false, mSortBy);
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
        if (v.getId() == tvShot.getId()) {//拍摄
            goShotActivity();
        } else if (v.getId() == ivSpandBack.getId()) {
            onBackPressed();
        } else if (v.getId() == llHotNew.getId()) {//最新最热
            if (mSortBy == 0) {
                mSortBy = 1;
                tvHotNew.setText("最新");
            } else {
                mSortBy = 0;
                tvHotNew.setText("最热");
            }
            switchData();
        } else if (v.getId() == ivTopBarBack.getId()) {
            onBackPressed();
        } else if (v.getId() == ivTopBarShare.getId()) {
            share();
        } else if (v.getId() == ivSpandShare.getId()) {
            share();
        }
    }

    /**
     * 点击切换最热最新 请求数据
     */
    private void switchData() {
        if (mSwitchCall != null && !mSwitchCall.isCanceled()) {
            mSwitchCall.cancel();
            mSwitchCall = null;
        }
        if (mLoadViewHolder != null) { // 复用时撤消上次失败
            mLoadViewHolder.finishLoad();
            mLoadViewHolder = null;
        }
        mLoadViewHolder = new LoadViewHolder(mRecycler, mRootContainer);
        mLoadViewHolder.setLoadingType(LoadViewHolder.LOADING_TYPE.NORMAL);
        mSwitchCall = new TopicHomeTask(new APIExpandCallBack<TopicHomeBean>() {
            @Override
            public void onSuccess(TopicHomeBean data) {
                enableScroll();
                data = handleSortBy(data);
                mTopicHomeBean = data;
                bindData(data, mSortBy);
                refreshView(data);
                mLoadViewHolder = null;
            }

            @Override
            public void onAfter() {

            }

            @Override
            public void onError(String errMsg, int errCode) {
//                if (!isFirst) {
//                    super.onError(errMsg, errCode);
//                }
                forbidScroll();
                if (errCode == Code.CODE_TOPIC_UNDER_LINE) {
                    flError.setVisibility(View.VISIBLE);
                    showWithDrawView();
                }
            }
        }).setTag(this)
                .bindLoadViewHolder(mLoadViewHolder)
                .exe(mTopicId, mSortBy);


        Analytics.create(this, "110054", "话题主页", false)
                .name("排序方式切换")
                .topicID(mTopicHomeBean.getTopic_label().getId())
                .action(mSortBy == 0 ? "最热" : "最新")
                .topicName(mTopicHomeBean.getTopic_label().getName())
                .build()
                .send();
    }

    /**
     * 禁止appbarlayout滑动
     */
    public void forbidScroll() {
        View mAppBarChildAt = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams mAppBarParams = (AppBarLayout.LayoutParams) mAppBarChildAt.getLayoutParams();
        mAppBarParams.setScrollFlags(0);
        mAppBarChildAt.setLayoutParams(mAppBarParams);
    }

    /**
     * 允许appbarlayout滑动
     */
    public void enableScroll() {
        View mAppBarChildAt = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams mAppBarParams = (AppBarLayout.LayoutParams) mAppBarChildAt.getLayoutParams();
        mAppBarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mAppBarChildAt.setLayoutParams(mAppBarParams);
    }

    private void goShotActivity() {
        if (UserBiz.get().isLoginUser()) {
            Bundle bundle = new Bundle();
            String topicName = "";
            if (mTopicHomeBean != null) {
                topicName = mTopicHomeBean.getTopic_label().getName();
            }
            bundle.putString(IKey.TITLE, topicName);
            Nav.with(getBaseContext()).setExtras(bundle).toPath("/native/publish/video");
            overridePendingTransition(R.anim.topic_slide_bottom_in, 0);
        } else {
            Nav.with(this).toPath("/login/LoginActivity", LOGIN_REQUEST_CODE);
        }

        if (mTopicHomeBean != null) {
            Analytics.create(this, "110053", "话题主页", false)
                    .name("点击我也要拍")
                    .topicID(mTopicHomeBean.getTopic_label().getId())
                    .topicName(mTopicHomeBean.getTopic_label().getName())
                    .build()
                    .send();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE) {
            if (UserBiz.get().isLoginUser()) { // 进入小视频页
                Bundle bundle = new Bundle();
                String topicName = "";
                if (mTopicHomeBean != null) {
                    topicName = mTopicHomeBean.getTopic_label().getName();
                }
                bundle.putString(IKey.TITLE, topicName);
                Nav.with(getBaseContext()).setExtras(bundle).toPath("/native/publish/video");
                overridePendingTransition(R.anim.topic_slide_bottom_in, 0);
            } else {
                T.showShort(this, "请先登录");
            }
        }
    }

    private void share() {
        if (mTopicHomeBean == null || mTopicHomeBean.getTopic_label() == null) {
            return;
        }
        ShareAnalytic analytic = ShareAnalytic.create("话题主页", "")
                .topicID(mTopicHomeBean.getTopic_label().getId())
                .topicName(mTopicHomeBean.getTopic_label().getName())
                .objectType("C21")
                .build();

        UmengShareUtils.getInstance().startShare(UmengShareBean.getInstance()
                        .setShareType("视频")
                        .setEventName("NewsShare")
                        .setImgUri(mTopicHomeBean.getTopic_label().getLogo_url())
                        .setTitle(mTopicHomeBean.getTopic_label().getName())
                        .setTextContent("来自天目新闻客户端")
                        .setAnalytic(analytic)
                        .setTargetUrl(mTopicHomeBean.getTopic_label().getUrl())
                        .setShareType("文章")
                        .setFavorite(mTopicHomeBean.getTopic_label().isFollowed())
                        .setCustomShareMediaType(CUSTOM_SHARE_MEDIA.FAVORITE, CUSTOM_SHARE_MEDIA.HELP_FEEDBACK,CUSTOM_SHARE_MEDIA.COPY_LINK)
                , new OnCustomShareMediaListener() {
                    @Override
                    public void onItemClick(View view, CUSTOM_SHARE_MEDIA media) {
                        if (media == CUSTOM_SHARE_MEDIA.FAVORITE) {//收藏
                            doCollect(view);
                        } else if (media == CUSTOM_SHARE_MEDIA.COPY_LINK) {
                            UmengShareUtils.copyLink(mTopicHomeBean.getTopic_label().getUrl());
                        }else if (media == CUSTOM_SHARE_MEDIA.HELP_FEEDBACK) {
                            AnalyTopicUtils.analyHelpFeedback(getBaseContext(),mTopicHomeBean.getTopic_label(),"话题主页");
                        }
                    }

                }

        );

        Analytics.create(getBaseContext(), "800018", "话题主页", false)
                .build()
                .send();
    }

    private void doCollect(final View view) {
        UmengShareUtils.favorite(view, mTopicId, 2, new OnCollectListener() {
            @Override
            public void onOperationSuccess() {
                if (mTopicHomeBean == null || mTopicHomeBean.getTopic_label() == null) {
                    return;
                }
                mTopicHomeBean.getTopic_label().setFollowed(view.isSelected());
                if (view.isSelected()){
                    AnalyTopicUtils.analyTopicCollect(view.getContext(),mTopicHomeBean.getTopic_label(),"话题主页");
                }else {
                    AnalyTopicUtils.analyTopicCancelCollect(view.getContext(),mTopicHomeBean.getTopic_label(),"话题主页");
                }
            }

            @Override
            public void onOperationFail() {

            }
        });
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

    /**
     * 有可能会在小视频详情页面点赞 返回的时候需要同步点赞状态 刷新adapter
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFollowChanged(String userId, int followStatus) {

    }

    @Override
    public void onLikeChange(String articleId, boolean isLike, String likeCountGeneral) {
        if (mAdapter == null) {
            return;
        }
        for (int i = 0; i < mAdapter.getDataSize(); i++) {
            ShortVideoBean.ArticleListBean bean = mAdapter.getData(i);
            if (TextUtils.equals(bean.getUuid(), articleId)) {
                bean.setLiked(isLike);
                bean.setLike_count_general(likeCountGeneral);
//                mAdapter.notifyItemChanged(i);
            }
        }
    }


    /**
     * 注册本地的点赞和关注变化监听
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LocalFollowChangeManager.getInstance().addFollowListener(this);
        LocalLikeChangeManager.getInstance().addLikeListener(this);
    }

    /**
     * 取消本地的点赞和关注变化监听
     */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LocalFollowChangeManager.getInstance().removeFollowListener(this);
        LocalLikeChangeManager.getInstance().removeLikeListener(this);
    }
}
