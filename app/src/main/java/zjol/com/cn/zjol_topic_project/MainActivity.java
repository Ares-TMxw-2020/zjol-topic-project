package zjol.com.cn.zjol_topic_project;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //话题主页
//        startActivity(new Intent(getBaseContext(), TopicHomeActivity.class));
        //选择话题
//        TopicChooserDialog dialog = new TopicChooserDialog();
//        dialog.show(getSupportFragmentManager());
    }
}
