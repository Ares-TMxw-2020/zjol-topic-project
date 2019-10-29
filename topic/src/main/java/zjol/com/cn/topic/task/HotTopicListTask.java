package zjol.com.cn.topic.task;

import com.core.network.callback.ApiCallback;

import cn.com.zjol.biz.core.network.compatible.APIGetTask;
import zjol.com.cn.topic.bean.HotTopicBean;


public class HotTopicListTask extends APIGetTask<HotTopicBean> {
    public HotTopicListTask(ApiCallback<HotTopicBean> callback) {
        super(callback);
    }

    @Override
    public void onSetupParams(Object... params) {
        if (params != null) {
            if (params.length > 0 && params[0] != null) {
                put("start", params[0]);
            }
        }
    }

    @Override
    public String getApi() {
        return "/api/ugc_topic/rank";
    }
}
