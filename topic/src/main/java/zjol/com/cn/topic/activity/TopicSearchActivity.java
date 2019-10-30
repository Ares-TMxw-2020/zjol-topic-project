package zjol.com.cn.topic.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.swipeback.app.SwipeBackActivity;
import com.zjrb.core.ui.divider.ListSpaceDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.daily.android.statusbar.DarkStatusBar;
import zjol.com.cn.news.common.utils.StatusBarUtil;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;
import zjol.com.cn.topic.adapter.TopicSearchAdapter;
import zjol.com.cn.topic.bean.TopicSearchBean;
import zjol.com.cn.topic.task.SearchTopicListTask;

/**
 * @author: lujialei
 * @date: 2019-10-30
 * @describe:
 */

public class TopicSearchActivity extends SwipeBackActivity {

    @BindView(R2.id.input)
    TextInputEditText mEditText;
    @BindView(R2.id.recycler)
    RecyclerView mRecycler;
    private TopicSearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_topic_search_activity);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color._000000));
        DarkStatusBar.get().fitLight(getBaseContext());
        initView();
        initListener();
    }

    private void initListener() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                requestSearchData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new ListSpaceDivider(1d, R.color._dcdcdc, 15, true, true));
    }


    private void requestSearchData(final String keyWord) {
        new SearchTopicListTask(new APIExpandCallBack<TopicSearchBean>() {
            @Override
            public void onSuccess(TopicSearchBean data) {
                bindSearchData(data,keyWord);
            }
        }).setTag(this).exe(keyWord);
    }


    private void bindSearchData(TopicSearchBean data, String keyWord) {
        if (mSearchAdapter ==null){
            mSearchAdapter = new TopicSearchAdapter(mRecycler,data.getElements(),keyWord);
            mSearchAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
//                    if (mCallback!=null){
//                        TopicSearchBean.ElementsBean elementsBean = mSearchAdapter.getData().get(position);
//                        mCallback.onTopicSelect(elementsBean.getId(),elementsBean.getName());
//                    }
                }
            });
            mRecycler.setAdapter(mSearchAdapter);
        }else {
            mRecycler.setAdapter(mSearchAdapter);
            mSearchAdapter.setKeyWord(keyWord);
            mSearchAdapter.setData(data.getElements());
            mSearchAdapter.notifyDataSetChanged();
        }
    }
}
