package zjol.com.cn.zjol_topic_project;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import cn.com.zjol.biz.core.nav.Nav;
import zjol.com.cn.topic.activity.NormalTopicHomeActivity;
import zjol.com.cn.topic.activity.TopicChooserActivity;


public class MainActivity extends AppCompatActivity {

    int requestCode = 201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(getBaseContext(), TestActivity.class));
        startActivity(new Intent(getBaseContext(), NormalTopicHomeActivity.class));
        //选择话题
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(getBaseContext(), TopicChooserActivity.class));
//                Nav.with(getBaseContext()).toPath("/topic/choose/activity",requestCode);
//                overridePendingTransition(R.anim.topic_bottom_up,0);
            }
        },3000);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==requestCode&&resultCode==RESULT_OK&&data.getExtras()!=null){
            String topicId = data.getExtras().getString("ID");
            String topicText = data.getExtras().getString("CONTENT");

        }
    }
}
