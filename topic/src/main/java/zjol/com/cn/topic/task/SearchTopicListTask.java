package zjol.com.cn.topic.task;

import com.core.network.callback.ApiCallback;

import cn.com.zjol.biz.core.network.compatible.APIGetTask;
import zjol.com.cn.topic.bean.TopicSearchBean;

public class SearchTopicListTask extends APIGetTask<TopicSearchBean> {
    public SearchTopicListTask(ApiCallback<TopicSearchBean> callback) {
        super(callback);
    }

    @Override
    public void onSetupParams(Object... params) {
        if (params != null) {
            if (params.length > 0 && params[0] != null) {
                put("keyword", params[0]);
            }
            if (params.length > 1 && params[1] != null) {
                put("from", params[1]);
            }
        }
    }

    @Override
    public String getApi() {
        return "/api/ugc_topic/search";
    }
}
