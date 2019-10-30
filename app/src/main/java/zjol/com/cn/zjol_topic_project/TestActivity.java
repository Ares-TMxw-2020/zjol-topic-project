package zjol.com.cn.zjol_topic_project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zjrb.core.common.glide.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.zjol.biz.core.DailyActivity;
import zjol.com.cn.player.utils.GlideBlurformation;

/**
 * @author: lujialei
 * @date: 2019-10-30
 * @describe:
 */

public class TestActivity extends DailyActivity {
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        String pic = "https://upfile.asqql.com/2009pasdfasdfic2009s305985-ts/2019-7/201972513144382307.gif";
        GlideApp.with(this)
                .load(R.drawable.test)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new GlideBlurformation(this, 15))
                .into(iv);
    }
}
