package zjol.com.cn.topic.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.zjrb.core.recycleView.EmptyPageHolder;
import com.zjrb.core.recycleView.HeaderRefresh;
import com.zjrb.core.recycleView.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import cn.com.zjol.biz.core.constant.C;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import cn.daily.android.statusbar.DarkStatusBar;
import zjol.com.cn.news.common.utils.State;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.news.home.adapter.FashionSelectAdapter;
import zjol.com.cn.news.home.bean.DataArticleList;
import zjol.com.cn.news.home.task.FashionListTask;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;

/**
 * 精选推荐位全部页面
 */


public class TopicHomeActivity extends DailyActivity implements OnItemClickListener, HeaderRefresh.OnRefreshListener {
    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    @BindView(R2.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R2.id.rl_topbar)
    RelativeLayout rlTopbar;
    private FashionSelectAdapter mAdapter;
    private LoadViewHolder mLoadViewHolder;
    private HeaderRefresh mRefresh;
    private GridLayoutManager mGridLayoutManager;
    private String mChannelId = "5d6f42348e79b87eba1e5538";
    private State mCurrentState = State.IDLE;

    @Override
    public boolean isShowTopBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_news_activity_topic_home);
        ButterKnife.bind(this);
        DarkStatusBar.get().fitDark(this);
        initView();
        loadData(true);
    }

    private void initView() {
        mAppBarLayout.addOnOffsetChangedListener(new MyBaseOnOffsetChangedListener());
        int statusHeight = StatusBarUtil.getStatusBarHeight(getBaseContext());
        rlTopbar.getLayoutParams().height = rlTopbar.getLayoutParams().height + statusHeight;
        rlTopbar.setPadding(0,statusHeight,0,0);
        rlTopbar.requestLayout();
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
        new FashionListTask(new APIExpandCallBack<DataArticleList>() {
            @Override
            public void onSuccess(DataArticleList data) {
                bindData(data);
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
                .exe(mChannelId, null, null);
    }


    private void bindData(DataArticleList data) {
        if (mAdapter == null) {
            mGridLayoutManager = new GridLayoutManager(getBaseContext(), 3);
            mRecycler.setLayoutManager(mGridLayoutManager);
//            mRecycler.addItemDecoration(new NewsSpaceDivider());
            mAdapter = new FashionSelectAdapter(data, mRecycler, mChannelId);
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
