package zjol.com.cn.zjol_topic_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zjol.com.cn.news.home.HomeFragment;
import zjol.com.cn.topic.activity.TopicHomeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(getBaseContext(), TopicHomeActivity.class));
        //选择话题
//        TopicChooserDialog dialog = new TopicChooserDialog();
//        dialog.show(getSupportFragmentManager());
    }
}
