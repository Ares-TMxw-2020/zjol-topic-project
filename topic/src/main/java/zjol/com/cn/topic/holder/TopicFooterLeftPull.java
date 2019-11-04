package zjol.com.cn.topic.holder;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.zjrb.core.recycleView.PageItem;

import zjol.com.cn.topic.R;


public class TopicFooterLeftPull extends PageItem implements RecyclerView.OnItemTouchListener, View
        .OnAttachStateChangeListener {

    private RecyclerView recycler;
    private PullCallback callback;

    public static final int LEFT_MAX_THRESHOLD = 65; // 左拉触发阈值， 单位：dp

    public TopicFooterLeftPull(RecyclerView recycler, PullCallback callback) {
        super(recycler, R.layout.module_news_layout_footer_left_pull);
        this.recycler = recycler;
        this.callback = callback;
        itemView.addOnAttachStateChangeListener(this);
    }

    private boolean isOnTouching, isTrigger;
    private int lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isOnTouching = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnTouching) {
                    //判断是否滑动到了最右边
                    if (!isTrigger && !ViewCompat.canScrollHorizontally(recycler, 1)) {
                        isTrigger = true;
                    }

                    if (isTrigger) { // 已经触发左拉
                        int dy = lastY - y;
                        int dx = lastX - x;

                        if (Math.abs(dy) < Math.abs(dx)) {
                            changeHeight(dx);
                        }
                    }
                } else {
                    isOnTouching = true; // 此时没有经过Down事件，后期拦截过来的
                }
                break;
            case MotionEvent.ACTION_UP:
                if (itemView.getLayoutParams().width > dp2px(LEFT_MAX_THRESHOLD)) {
                    if (callback != null) {
                        callback.leftPull();
                    }
                }
            case MotionEvent.ACTION_CANCEL:
                isOnTouching = false;
                isTrigger = false;
                autoSize();
                break;
        }
        lastX = x;
        lastY = y;
        return false;
    }

    protected int dp2px(float dp) {
        return (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().getDisplayMetrics()
        ) + 0.5f);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private void changeHeight(int dx) {
        if ((itemView.getLayoutParams().width += dx) < 1) {
            itemView.getLayoutParams().width = 1;
            isTrigger = false;
        }
        itemView.requestLayout();
    }

    /**
     * 手势UP 回弹动画
     */
    private void autoSize() {
        ValueAnimator objectAnimator = ValueAnimator.ofInt(itemView.getWidth(), 1);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                itemView.getLayoutParams().width = (int) animation.getAnimatedValue();
                itemView.requestLayout();
            }
        });
        objectAnimator.start();
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        recycler.removeOnItemTouchListener(this);
        recycler.addOnItemTouchListener(this);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        recycler.removeOnItemTouchListener(this);
    }

    public interface PullCallback {

        /**
         * 左拉触发回调
         */
        void leftPull();

    }

}
