package zjol.com.cn.topic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import zjol.com.cn.topic.R;

/**
 * @author: lujialei
 * @date: 2019-10-24
 * @describe:
 */

public class ApplyCreateTopicDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initWindow();
        View view = inflater.inflate(R.layout.module_news_dialog_fragment_apply_create_topic,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        int width = Utils.dp2px(getContext(),271);
//        int height = Utils.dp2px(getContext(),271);
//        getDialog().getWindow().setLayout(width, height);
    }


    protected void initWindow() {
        WindowManager.LayoutParams params = getDialog().getWindow()
                .getAttributes();
        params.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(params);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setDimAmount(0.5f);
    }
}
