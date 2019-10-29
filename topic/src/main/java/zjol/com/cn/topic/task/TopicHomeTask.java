package zjol.com.cn.topic.task;

import com.core.network.callback.ApiCallback;

import cn.com.zjol.biz.core.network.compatible.APIGetTask;
import zjol.com.cn.topic.bean.FashionTopicBean;
import zjol.com.cn.topic.bean.TopicHomeBean;

/**
 * 频道列表接口 - Task
 *
 * @author a_liYa
 * @date 2017/8/30 15:45.
 */
public class TopicHomeTask extends APIGetTask<TopicHomeBean> {


    public TopicHomeTask(ApiCallback<TopicHomeBean> mCallBack) {
        super(mCallBack);
    }

    @Override
    public void onSetupParams(Object... params) {
        if (params != null) {
            if (params.length > 0 && params[0] != null) {
                put("id", params[0]);
            }
            if (params.length > 1 && params[1] != null) {
                put("sort_by", params[1]);
            }
            if (params.length > 2 && params[2] != null) {
                put("start", params[2]);
            }
        }
    }

    @Override
    public String getApi() {
        return "/api/ugc_topic/detail";
    }
}
