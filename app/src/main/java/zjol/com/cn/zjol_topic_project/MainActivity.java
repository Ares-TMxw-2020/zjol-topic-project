package zjol.com.cn.zjol_topic_project;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import zjol.com.cn.topic.activity.TopicHomeActivity;
import zjol.com.cn.topic.callbacks.OnTopicSelectCallBack;
import zjol.com.cn.topic.fragment.TopicChooserDialog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //话题主页
//        startActivity(new Intent(getBaseContext(), TopicHomeActivity.class));
        //选择话题
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TopicChooserDialog dialog = new TopicChooserDialog();
                dialog.setOnChooseCallback(new OnTopicSelectCallBack() {
                    @Override
                    public void onTopicSelect(String id, String content) {

                    }
                });
                dialog.show(getSupportFragmentManager());
            }
        },5000);

    }
}
