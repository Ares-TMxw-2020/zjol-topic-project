package zjol.com.cn.topic.other;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class OnLineTitleBehavior extends CoordinatorLayout.Behavior<View> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public OnLineTitleBehavior() {
    }

    public OnLineTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    //被观察的view发生改变时回调
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (child.getVisibility()!=View.VISIBLE){
            child.setVisibility(View.VISIBLE);
        }
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;

        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);

        float alpha = 1 - (dy / deltaY)*2;
        child.setAlpha(alpha);

        return true;
    }
}
