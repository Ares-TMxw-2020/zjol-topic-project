package zjol.com.cn.topic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.swipeback.app.SwipeBackActivity;
import com.zjrb.core.ui.divider.ListSpaceDivider;
import com.zjrb.passport.listener.IFailure;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import cn.daily.android.statusbar.DarkStatusBar;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicChooseAdapter;
import zjol.com.cn.topic.bean.HotTopicBean;
import zjol.com.cn.topic.fragment.ApplyCreateTopicDialogFragment;
import zjol.com.cn.topic.task.HotTopicListTask;

/**
 * @author: lujialei
 * @date: 2019-10-30
 * @describe:
 */

public class TopicChooserActivity extends SwipeBackActivity implements View.OnClickListener {


    @BindView(R2.id.ic_nav_back)
    ImageView icNavBack;
    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    @BindView(R2.id.btn_finish)
    TextView btnCreateTopic;
    @BindView(R2.id.btn_search)
    TextView btnSearch;
    private TopicChooseAdapter mHotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_topic_choose_activity);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color._000000));
        DarkStatusBar.get().fitLight(getBaseContext());
        initView();
        initListener();
        requestHotData();
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new ListSpaceDivider(1d, R.color._dcdcdc, 15, true, true));
    }

    private void initListener() {
        icNavBack.setOnClickListener(this);
        btnCreateTopic.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    private void requestHotData() {
        new HotTopicListTask(new APIExpandCallBack<HotTopicBean>() {
            @Override
            public void onSuccess(HotTopicBean data) {
                bindHotData(data);
            }
        })
                .bindLoadViewHolder(new LoadViewHolder(mRecycler, null))
                .setTag(mRecycler).exe();
    }


    private void bindHotData(HotTopicBean data) {
        if (mHotAdapter == null) {
            mHotAdapter = new TopicChooseAdapter(mRecycler, data.getRank(), "", "");
            mHotAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
//                    if (mCallback!=null){
//                        HotTopicBean.RankBean rankBean = mHotAdapter.getData().get(position);
//                        mCallback.onTopicSelect(rankBean.getId(),rankBean.getName());
//                    }
                }
            });
            mRecycler.setAdapter(mHotAdapter);
        } else {
            mRecycler.setAdapter(mHotAdapter);
            mHotAdapter.setData(data.getRank());
            mHotAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_nav_back) {
            onBackPressed();
        } else if (id == R.id.btn_finish) {//创建话题
            ApplyCreateTopicDialogFragment fragment = new ApplyCreateTopicDialogFragment();
            fragment.show(getSupportFragmentManager(), "");
        }
        else if (id == R.id.btn_search) {//搜索话题
            startActivityForResult(new Intent(getBaseContext(),TopicSearchActivity.class),151);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==151&&resultCode==RESULT_OK){
            if (data.getExtras()==null){
                return;
            }
            String id = data.getExtras().getString("ID");
            String content = data.getExtras().getString("CONTENT");

        }
    }
}
