package zjol.com.cn.topic.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: lujialei
 * @date: 2019-11-06
 * @describe:
 */

public class TopicNO2VideoHolder extends TopicHomeVideoHolder {
    public TopicNO2VideoHolder(@NonNull ViewGroup parent) {
        super(parent);
        flTag.setVisibility(View.VISIBLE);
        tvTag.setText("NO2");
    }
}
