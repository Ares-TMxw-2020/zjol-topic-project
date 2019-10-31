package zjol.com.cn.topic.task;

import com.core.network.callback.ApiCallback;

import cn.com.zjol.biz.core.network.compatible.APIGetTask;
import zjol.com.cn.topic.bean.NormalTopicHomeBean;
import zjol.com.cn.topic.bean.TopicHomeBean;

/**
 * 频道列表接口 - Task
 *
 * @author a_liYa
 * @date 2017/8/30 15:45.
 */
public class NormalTopicHomeTask extends APIGetTask<NormalTopicHomeBean> {


    public NormalTopicHomeTask(ApiCallback<NormalTopicHomeBean> mCallBack) {
        super(mCallBack);
    }

    @Override
    public void onSetupParams(Object... params) {
        if (params != null) {
            if (params.length > 0 && params[0] != null) {
                put("label_id", params[0]);
            }
        }
    }

    @Override
    public String getApi() {
        return "/api/topic_label/article_list";
    }
}
