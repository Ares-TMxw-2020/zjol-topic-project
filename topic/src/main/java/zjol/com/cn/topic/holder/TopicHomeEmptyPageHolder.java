package zjol.com.cn.topic.holder;

import android.view.ViewGroup;

import com.zjrb.core.recycleView.PageItem;

import butterknife.ButterKnife;
import zjol.com.cn.topic.R;

/**
 * RecyclerAdapter 空页面
 *
 * @author lujialei 自适应剩余高度的空页面
 */
public class TopicHomeEmptyPageHolder extends PageItem {

    /**
     * 构造方法
     *
     * @param parent a ViewGroup
     */
    public TopicHomeEmptyPageHolder(ViewGroup parent) {
        super(inflate(R.layout.module_topic_empty_topic_home, parent, false));
        ButterKnife.bind(this, itemView);
    }



}
