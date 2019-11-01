package zjol.com.cn.topic.task;

import com.core.network.callback.ApiCallback;

import cn.com.zjol.biz.core.network.compatible.APIGetTask;
import zjol.com.cn.topic.bean.FashionTopicBean;

public class FashionTopicTask extends APIGetTask<FashionTopicBean> {


    public FashionTopicTask(ApiCallback<FashionTopicBean> mCallBack) {
        super(mCallBack);
    }

    @Override
    public void onSetupParams(Object... params) {
        if (params != null) {
            if (params.length > 0 && params[0] != null) {
                put("start", params[0]);
            }
        }
//        put("size", C.ARTICLE_PAGE_SIZE);
    }

    @Override
    public String getApi() {
        return "/api/ugc_topic/square";
    }
}
