package zjol.com.cn.topic.holder;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjrb.core.recycleView.PageItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import cn.com.zjol.biz.core.UserBiz;
import cn.com.zjol.biz.core.nav.Nav;
import cn.com.zjol.me.activity.login.LoginActivity;
import zjol.com.cn.topic.R;
import zjol.com.cn.topic.R2;

import static zjol.com.cn.topic.activity.TopicHomeActivity.LOGIN_REQUEST_CODE;

/**
 * RecyclerAdapter 空页面
 *
 * @author lujialei 自适应剩余高度的空页面
 */
public class TopicHomeEmptyPageHolder extends PageItem {

    @BindView(R2.id.tv_shot)
    TextView tvShot;

    /**
     * 构造方法
     *
     * @param parent a ViewGroup
     */
    public TopicHomeEmptyPageHolder(ViewGroup parent) {
        super(inflate(R.layout.module_topic_empty_topic_home, parent, false));
        ButterKnife.bind(this, itemView);
        tvShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goShotActivity(v);
            }
        });
    }

    private void goShotActivity(View v) {
        if (v.getContext() instanceof DailyActivity){
            DailyActivity activity = (DailyActivity) v.getContext();
            if (UserBiz.get().isLoginUser()) {
                Nav.with(getItemView().getContext()).toPath("/native/publish/video");
                activity.overridePendingTransition(R.anim.topic_slide_bottom_in, 0);
            } else {
                activity.startActivityForResult(new Intent(activity, LoginActivity.class),
                        LOGIN_REQUEST_CODE);
            }
        }
    }


}
