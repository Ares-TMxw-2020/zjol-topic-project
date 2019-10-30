package zjol.com.cn.topic.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zjrb.core.recycleView.listener.OnItemClickListener;
import com.zjrb.core.ui.divider.ListSpaceDivider;

import cn.com.zjol.biz.core.network.compatible.APIExpandCallBack;
import cn.com.zjol.biz.core.network.compatible.LoadViewHolder;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.adapter.TopicChooseAdapter;
import zjol.com.cn.topic.adapter.TopicSearchAdapter;
import zjol.com.cn.topic.bean.HotTopicBean;
import zjol.com.cn.topic.bean.TopicSearchBean;
import zjol.com.cn.topic.callbacks.OnTopicSelectCallBack;
import zjol.com.cn.topic.task.HotTopicListTask;
import zjol.com.cn.topic.task.SearchTopicListTask;

/**
 * 选择话题
 */
public class TopicChooserDialog extends DialogFragment implements View.OnClickListener{

    private RecyclerView mRecycler;
    private TopicChooseAdapter mHotAdapter;
    private TopicSearchAdapter mSearchAdapter;
    private EditText mEditText;
    private OnTopicSelectCallBack mCallback;
    private LinearLayout mContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.module_news_choose_topic_dialog, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestHotData();
    }

    private void requestHotData() {
        new HotTopicListTask(new APIExpandCallBack<HotTopicBean>() {
            @Override
            public void onSuccess(HotTopicBean data) {
                bindHotData(data);
            }
        })
                .bindLoadViewHolder(new LoadViewHolder(mRecycler,null))
                .setTag(mRecycler).exe();
    }

    private void bindHotData(HotTopicBean data) {
        if (mHotAdapter ==null){
            mHotAdapter = new TopicChooseAdapter(mRecycler,data.getRank(),"","");
            mHotAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    if (mCallback!=null){
                        HotTopicBean.RankBean rankBean = mHotAdapter.getData().get(position);
                        mCallback.onTopicSelect(rankBean.getId(),rankBean.getName());
                    }
                }
            });
            mRecycler.setAdapter(mHotAdapter);
        }else {
            mRecycler.setAdapter(mHotAdapter);
            mHotAdapter.setData(data.getRank());
            mHotAdapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        view.findViewById(R.id.ic_nav_back).setOnClickListener(this);
        view.findViewById(R.id.btn_finish).setOnClickListener(this);
        mContainer = view.findViewById(R.id.container);
        mRecycler = view.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addItemDecoration(new ListSpaceDivider(1d, R.color._dcdcdc, 15, true, true));
        mEditText = view.findViewById(R.id.et_input);
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
                    if (mCallback!=null){
                        TopicSearchBean.ElementsBean elementsBean = mSearchAdapter.getData().get(position);
                        mCallback.onTopicSelect(elementsBean.getId(),elementsBean.getName());
                    }
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_nav_back) {
            dismissAllowingStateLoss();
        } else if (id == R.id.btn_finish) {//创建话题
            ApplyCreateTopicDialogFragment fragment = new ApplyCreateTopicDialogFragment();
            fragment.show(getChildFragmentManager(),"");
        }
    }


    public void setOnChooseCallback(OnTopicSelectCallBack callback) {
        this.mCallback = callback;
    }

    public void show(FragmentManager manager) {
        String tag = TopicChooserDialog.class.getName();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment != null) {
            transaction.show(fragment);
        } else {
            transaction.add(this, tag);
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
            if (Build.VERSION.SDK_INT >= 21) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int uiOptions = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                window.getDecorView().setSystemUiVisibility(uiOptions);
            }

            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.windowAnimations = R.style.TopicAnimation;
            window.setAttributes(attributes);

            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    }
                    return false;
                }
            });
        }
    }
}
