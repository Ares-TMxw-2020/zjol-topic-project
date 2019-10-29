package zjol.com.cn.zjol_topic_project;

import android.app.Application;
import android.content.Context;

import com.aliya.uimode.UiModeManager;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.zjrb.core.utils.AppUtils;
import com.zjrb.core.utils.UIUtils;
import com.zjrb.daily.db.DatabaseLoader;
import com.zjrb.daily.db.dao.ReadNewsDaoHelper;

import cn.com.zjol.biz.core.UserBiz;
import cn.com.zjol.biz.core.db.SettingManager;
import cn.com.zjol.biz.core.network.DailyNetworkManager;
import cn.daily.news.analytics.AnalyticsManager;

/**
 * @author: lujialei
 * @date: 2019/7/31
 * @describe:
 */


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DailyNetworkManager.init(this);
        AppUtils.setChannel("bianfeng");
        UIUtils.init(this);
        UiModeManager.init(this,null);
        DatabaseLoader.init(this);
        ReadNewsDaoHelper.initReadIds();
        SettingManager.init(this);
        SettingManager.getInstance().setProvincialTraffic(true);
        initUmengLogin(this,"bianfeng");
        initAnalytic(true);
    }

    /**
     * 友盟第三方登录
     */
    private void initUmengLogin(Context context, String channel) {
        UMConfigure.init(context, "535879d256240b8965030920", channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(context).setShareConfig(config);
        PlatformConfig.setWeixin("wxc8bcb96e972bd147", "6bde68292c1295c7cf81d47a3a520030");
        PlatformConfig.setSinaWeibo("287017146", "5113d5e528ae8335f230f025bcbd6fa1", "http://www.zjol.com.cn");
        PlatformConfig.setQQZone("101096646", "da6306af99c94ba949029b563a69a9a4");
        PlatformConfig.setDing("dingoahuzuxhqyb2jtypgm");
    }


    /**
     * 初始化埋点相关信息
     */
    private void initAnalytic(boolean isDebug) {
        String appKey = isDebug ? "jzcif5f3_07rbh5dzvuuk9" : "jzcid4st_04o7ebuj3yhqv";
        long mpID = isDebug ? 102 : 100;
        String statisticsURL = isDebug ? "https://ta.8531.cn/c" : "https://ta.8531.cn/c";

        AnalyticsManager.TAConfig taConfig = new AnalyticsManager.TAConfig(appKey, mpID, statisticsURL);
        taConfig.setEnable(true);
        if (UserBiz.get() != null && UserBiz.get().isLoginUser()) {
            taConfig.setAccountId(UserBiz.get().getAccountID());
        }

        String saStatisticsURL = isDebug ? "http://sa.tmuyun.com/sa?project=zjxwtest" : "http://sa.tmuyun.com/sa?project=zjxwprod";
        AnalyticsManager.SAConfig saConfig = new AnalyticsManager.SAConfig(saStatisticsURL);
        saConfig.setEnable(false);
        if (UserBiz.get() != null && UserBiz.get().isLoginUser()) {
            saConfig.setAccountId(UserBiz.get().getAccountID());
        }
        saConfig.setLogEnable(isDebug);
        saConfig.setAutoTrack(true);

        String logService = "dot.wts.xinwen.cn";
        AnalyticsManager.SHWConfig shwConfig = new AnalyticsManager.SHWConfig("", "", logService);
        shwConfig.setImmediateReport(true);
        shwConfig.setLogEnable(true);
        shwConfig.setEnable(true);
        if (UserBiz.get() != null && UserBiz.get().isLoginUser()) {
            shwConfig.setAccountId(UserBiz.get().getAccountID());
        }

        AnalyticsManager.initAnalytics(this, AppUtils.getChannelName(), taConfig, saConfig, shwConfig);
    }
}
