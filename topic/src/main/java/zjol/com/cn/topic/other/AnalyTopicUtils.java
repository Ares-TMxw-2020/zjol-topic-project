package zjol.com.cn.topic.other;

import android.content.Context;

import cn.com.zjol.biz.core.model.ArticleBean;
import cn.com.zjol.biz.core.model.TopicLabelBean;
import cn.daily.news.analytics.Analytics;

/**
 * @author: lujialei
 * @date: 2019-12-25
 * @describe:
 */

public class AnalyTopicUtils {


    /**
     * 话题取消收藏埋点
     * @param context
     * @param data
     * @param pageType
     */
    public static void analyTopicCancelCollect(Context context, TopicLabelBean data, String pageType) {
        Analytics.create(context, "A0124", pageType, false)
                .topicName(data.getName())
                .topicID(data.getId())
                .build()
                .send();
    }

    /**
     * 话题收藏埋点
     * @param context
     * @param data
     * @param pageType
     */
    public static void analyTopicCollect(Context context, TopicLabelBean data, String pageType) {
        Analytics.create(context, "A0024", pageType, false)
                .topicName(data.getName())
                .topicID(data.getId())
                .build()
                .send();
    }

    /**
     * 帮助反馈埋点
     * @param context
     * @param data
     * @param pageType
     */
    public static void analyHelpFeedback(Context context, TopicLabelBean data, String pageType) {
        Analytics.create(context, "800007", pageType, false)
                .topicName(data.getName())
                .topicID(data.getId())
                .build()
                .send();
    }


    /**
     * 复制链接埋点
     * @param context
     * @param data
     * @param pageType
     */
    public static void analyCopyLink(Context context, TopicLabelBean data, String pageType) {
        Analytics.create(context, "A0030", pageType, false)
                .topicName(data.getName())
                .topicID(data.getId())
                .build()
                .send();
    }
}
