package zjol.com.cn.zjol_topic_project;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import zjol.com.cn.topic.activity.TopicChooserActivity;


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
                startActivity(new Intent(getBaseContext(), TopicChooserActivity.class));
            }
        },2000);

    }
}
