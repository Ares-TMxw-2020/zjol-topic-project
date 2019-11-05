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

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import cn.com.zjol.biz.core.UserBiz;
import cn.com.zjol.biz.core.constant.C;
import cn.com.zjol.biz.core.nav.Nav;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import cn.com.zjol.biz.core.share.UmengShareBean;
import cn.com.zjol.biz.core.share.UmengShareUtils;
import cn.com.zjol.me.activity.login.LoginActivity;
import cn.daily.android.statusbar.DarkStatusBar;
import zjol.com.cn.news.common.utils.State;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.news.common.widget.GlideRoundTransform;
import zjol.com.cn.player.bean.ShortVideoBean;
import zjol.com.cn.player.manager.shortvideo.topic.TopicShortVideoPlayActivity;
import zjol.com.cn.player.utils.Constants;
import zjol.com.cn.player.utils.GlideBlurformation;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicHomeAdapter;
import zjol.com.cn.topic.bean.TopicHomeBean;
import zjol.com.cn.topic.holder.TopicHomeEmptyPageHolder;
import zjol.com.cn.topic.other.Code;
import zjol.com.cn.topic.task.TopicHomeTask;

/**
 * 潮客小视频的话题主页
 */


public class TopicHomeActivity extends DailyActivity implements OnItemClickListener, HeaderRefresh.OnRefreshListener, View.OnClickListener {
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
    private TopicHomeAdapter mAdapter;
    private LoadViewHolder mLoadViewHolder;
    private HeaderRefresh mRefresh;
    private GridLayoutManager mGridLayoutManager;
    private String mChannelId = "";
    private State mCurrentState = State.IDLE;
    private String mTopicId = "";
    private int mSortBy = 0;//0最热 1最新
    private TopicHomeBean mTopicHomeBean;

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
//        llTop.setPadding(0,statusHeight,0,0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlSpandTopbar.getLayoutParams();
        layoutParams.topMargin += statusHeight;
        llTop.postInvalidate();

        //网络错误页面topbar处理
        LinearLayout.LayoutParams errorLayoutParams = (LinearLayout.LayoutParams) errorTopbar.getLayoutParams();
        errorLayoutParams.height += statusHeight;
        errorTopbar.setPadding(0,statusHeight,0,0);
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
        tvHotNew.setOnClickListener(this);
        ivTopBarBack.setOnClickListener(this);
        ivSpandShare.setOnClickListener(this);
        ivTopBarShare.setOnClickListener(this);
    }

    private void initView() {
        mAppBarLayout.addOnOffsetChangedListener(new MyBaseOnOffsetChangedListener());
        int statusHeight = StatusBarUtil.getStatusBarHeight(getBaseContext());
        rlTopbar.getLayoutParams().height += statusHeight;
        rlTopbar.setPadding(0, statusHeight, 0, 0);
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

    private TopicHomeBean handleSortBy(TopicHomeBean data) {
        for (int i = 0; i < data.getArticles().size(); i++) {
            data.getArticles().get(i).setSort_by(mSortBy);
        }
        return data;
    }

    /**
     * 显示话题下架页面
     */
    private void showWithDrawView() {
        llWithDraw.setVisibility(View.VISIBLE);
    }

    private void refreshView(TopicHomeBean data) {
        if (data == null) {
            return;
        }
        if (data.getArticles() == null || data.getArticles().isEmpty()) {
            llBottom.setVisibility(View.GONE);
        } else {
            llBottom.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(data.getTopic_label().getName())) {
            String name = data.getTopic_label().getName();
            tvLogo.setText(name.substring(0, 1));
            tvTitle.setText(name);
            tvTopBarTitle.setText(name);
        }
        GlideApp.with(getBaseContext())
                .load(data.getTopic_label().getLogo_url())
                .centerCrop()
                .placeholder(R.mipmap.zjov_app_header_bg)
                .error(R.mipmap.zjov_app_header_bg)
                .transform(new GlideBlurformation(getBaseContext(), 15))
                .into(ivHeader);
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
        tvVideo.setText("视频  " + data.getTopic_label().getParticipant_count_general());
        tvPrise.setText("点赞  " + data.getTopic_label().getLike_count_general());
        String des = data.getTopic_label().getDescription();
        if (!TextUtils.isEmpty(des)){
            tvOther.setText("简介:" + des);
        }
    }

    private void bindData(TopicHomeBean data, int sortBy) {
        if (mAdapter == null) {
            mGridLayoutManager = new GridLayoutManager(getBaseContext(), 3);
            mRecycler.setLayoutManager(mGridLayoutManager);
            mRecycler.addItemDecoration(new GridSpaceDivider(8));
            mAdapter = new TopicHomeAdapter(data, mRecycler, mTopicId, sortBy);
            mRecycler.setAdapter(mAdapter);


            // 下拉刷新
            mRefresh = new HeaderRefresh(mRecycler, this);
            mAdapter.setHeaderRefresh(mRefresh.getItemView());

            mAdapter.setEmptyView(new TopicHomeEmptyPageHolder(mRecycler).itemView);

            // 条目点击
            mAdapter.setOnItemClickListener(this);
        } else {
            mAdapter.setSortBy(sortBy);
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();
        }
        setCanRefresh(false);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Intent intent = new Intent(itemView.getContext(), TopicShortVideoPlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATA, (Serializable) mAdapter.datas);
        bundle.putInt(Constants.POSITION, position);
        bundle.putString(Constants.TOPIC_ID, mTopicId);
        bundle.putInt(Constants.SORT_BY, mSortBy);
        intent.putExtras(bundle);
        itemView.getContext().startActivity(intent);
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
        } else if (v.getId() == tvHotNew.getId()) {//最新最热
            if (mSortBy == 0) {
                mSortBy = 1;
                tvHotNew.setText("最新");
            } else {
                mSortBy = 0;
                tvHotNew.setText("最热");
            }
            loadData(true, mSortBy);
        } else if (v.getId() == ivTopBarBack.getId()) {
            onBackPressed();
        } else if (v.getId() == ivTopBarShare.getId()) {
            share();
        } else if (v.getId() == ivSpandShare.getId()) {
            share();
        }
    }

    private void goShotActivity() {
        if (UserBiz.get().isLoginUser()) {
            Nav.with(getBaseContext()).toPath("/native/publish/video");
//            overridePendingTransition(R.anim.topic_bottom_up, 0);
        } else {
            startActivityForResult(new Intent(getBaseContext(), LoginActivity.class),
                    LOGIN_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE) {
            if (UserBiz.get().isLoginUser()) { // 进入小视频页
                Nav.with(getBaseContext()).toPath("/native/publish/video");
//                overridePendingTransition(R.anim.topic_bottom_up, 0);
            } else {
                T.showShort(this, "请先登录");
            }
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
