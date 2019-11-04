package zjol.com.cn.topic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.zjrb.core.recycleView.EmptyPageHolder;
import com.zjrb.core.recycleView.HeaderRefresh;
import com.zjrb.daily.db.bean.ChannelBean;
import com.zjrb.daily.db.bean.CityBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyFragment;
import cn.com.zjol.biz.core.constant.C;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.FashionTopicAdapter;
import zjol.com.cn.topic.bean.FashionTopicBean;
import zjol.com.cn.topic.bean.TopicRankBean;
import zjol.com.cn.topic.holder.TopicRankHeader;
import zjol.com.cn.topic.task.FashionTopicTask;

/**
 * 潮客话题
 */
public class FashionTopicFragment extends DailyFragment implements View.OnClickListener, HeaderRefresh.OnRefreshListener {

    @BindView(R2.id.recycler)
    RecyclerView mRecycler;

    private View mCacheView;

    private FashionTopicAdapter mAdapter;

    private long mRefreshTime; // 每次刷新的时间记录
    public String mChannelName;
    public String mChannelId;

    private HeaderRefresh mRefresh;
    public static final String KEY_CHANNEL_ID = "channel_id";
    public static final String KEY_CHANNEL_NAME = "channel_name";
    public static final String KEY_CHANNEL_TYPE = "channel_type";
    /**
     * 刷新缓存数据失效期限 - 间隔
     */
    public static final int REFRESH_CACHE_DEADLINE = 20 * 60 * 1000; // 20分钟

    public static Fragment fragment(CityBean city) {
        FashionTopicFragment fragment = new FashionTopicFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FashionTopicFragment.KEY_CHANNEL_ID, city.getNav_parameter());
        bundle.putString(FashionTopicFragment.KEY_CHANNEL_NAME, city.getName());
        fragment.setArguments(bundle);
        return fragment;
    }

    public static FashionTopicFragment fragment(ChannelBean channel) {
        FashionTopicFragment fragment = new FashionTopicFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CHANNEL_ID, channel.getNav_parameter());
        bundle.putString(KEY_CHANNEL_NAME, channel.getName());
        bundle.putString(KEY_CHANNEL_TYPE, channel.getNav_type());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mCacheView != null) {
            ViewParent parent = mCacheView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mCacheView);
            }
            return mCacheView;
        }
        return inflater.inflate(R.layout.module_news_fragment_fashion_topic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mCacheView == null) {
            mCacheView = view;
            initArgs();
            initView(view);
            refreshData(true);
        }
    }

    /**
     * 刷新数据
     *
     * @param isFirst true:页面创建加载数据； false:下拉刷新
     */
    private void refreshData(final boolean isFirst) {
        // 策略 1、第一次使用loadViewHolder; 2、有缓存就不使用
        // 加载网络没有数据，如何展示待定
        if (isFirst && mAdapter != null
                && (System.currentTimeMillis() - mRefreshTime) < REFRESH_CACHE_DEADLINE) {
            // 缓存页面创建，距离上次刷新在 REFRESH_CACHE_DEADLINE 时间间隔内，不重新加载数据
            return;
        }
        if (mLoadViewHolder != null) { // 复用时撤消上次失败
            mLoadViewHolder.finishLoad();
            mLoadViewHolder = null;
        }
        new FashionTopicTask(new APIExpandCallBack<FashionTopicBean>() {
            @Override
            public void onSuccess(FashionTopicBean data) {
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
                .bindLoadViewHolder(isFirst ? (mLoadViewHolder = replaceLoad(mRecycler, LoadViewHolder.LOADING_TYPE.NEWS)) : null)
                .exe();

        mRefreshTime = System.currentTimeMillis();
    }

    private LoadViewHolder mLoadViewHolder;

    private void initView(View view) {
        if (mRecycler == null) {
            ButterKnife.bind(this, view);
        }

    }

    private void initArgs() {
        Bundle args = getArguments();
        if (args != null) {
            mChannelName = args.getString(KEY_CHANNEL_NAME);
            mChannelId = args.getString(KEY_CHANNEL_ID);
        }
    }

    private void bindData(FashionTopicBean data) {
        if (mAdapter == null) {
            mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//            mRecycler.addItemDecoration(new NewsSpaceDivider());
            mAdapter = new FashionTopicAdapter(data, mRecycler, mChannelId);
            mRecycler.setAdapter(mAdapter);

            // 下拉刷新
            mRefresh = new HeaderRefresh(mRecycler, this);
            mAdapter.setHeaderRefresh(mRefresh.getItemView());
            //空页面
            mAdapter.setEmptyView(
                    new EmptyPageHolder(mRecycler,
                            EmptyPageHolder.ArgsBuilder.newBuilder()
                                    .resId(R.mipmap.zjov_chaoke_dynamics_icon)
                                    .content("暂无内容")
                    ).itemView);
        } else {
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAdapter != null) { // 撤销 Adapter内部加载更多请求
            mAdapter.cancelLoadMore();
        }
    }

    // 下拉刷新
    @Override
    public void onRefresh() {
        if (mAdapter != null) {
            mAdapter.cancelLoadMore();
        }
        refreshData(false);
    }

    @Override
    public void onClick(View view) {

    }

    public void setCanRefresh(boolean b) {
        if (mRefresh!=null){
            mRefresh.setCanrfresh(b);
        }
    }

    public void startAutoRefresh() {
        if (mRecycler==null||mRefresh==null){
            return;
        }
        mRecycler.scrollToPosition(0);
        mRefresh.autoRefresh();
    }
}