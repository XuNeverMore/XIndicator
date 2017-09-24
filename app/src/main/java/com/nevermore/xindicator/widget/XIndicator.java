package com.nevermore.xindicator.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/23.
 */

public abstract class XIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {

    protected ViewPager mViewPager;
    protected int position;
    protected float positionOffset;
    protected int positionOffsetPixels;
    protected int indicatorHeight = 10;
    protected Rect rect = new Rect();
    private Paint indicatorPaint;
    protected TabViewDelegate tabViewDelegate;
    private View lastSelectedTabView;

    public XIndicator(Context context) {
        this(context, null);
    }

    public XIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        indicatorPaint = new Paint();
        setOrientation(HORIZONTAL);
    }

    public TabViewDelegate getTabViewDelegate() {
        return tabViewDelegate;
    }

    public void setTabViewDelegate(TabViewDelegate tabViewDelegate) {
        this.tabViewDelegate = tabViewDelegate;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        View child = getChildAt(position);

        int childMeasuredHeight = child.getMeasuredHeight();
        int measuredWidth = child.getMeasuredWidth();

        int left = (int) (child.getLeft() + measuredWidth * positionOffset);
        int top = childMeasuredHeight - indicatorHeight;
        int right = left + measuredWidth;
        int bottom = childMeasuredHeight;

        rect.set(left, 0, right, bottom);
        drawTabBg(canvas, rect, indicatorPaint);
        super.dispatchDraw(canvas);
        rect.set(left, top, right, bottom);

        drawIdicator(canvas, rect, indicatorPaint);
        canvas.drawLine(left, bottom, right, bottom, indicatorPaint);

    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }

    protected abstract void drawIdicator(Canvas canvas, Rect rect, Paint paint);

    protected void drawTabBg(Canvas canvas, Rect rect, Paint paint) {

    }

    public void setUpWithViewPager(ViewPager viewPager) {

        if (viewPager != null) {
            mViewPager = viewPager;
            mViewPager.addOnPageChangeListener(this);

            PagerAdapter adapter = mViewPager.getAdapter();
            if (adapter != null && tabViewDelegate != null) {

                removeAllViews();
                for (int i = 0; i < adapter.getCount(); i++) {


                    final int positon = i;
                    View tabView = tabViewDelegate.getTabView(i);
                    TextView tabTextView = (TextView) tabView.findViewById(tabViewDelegate.getTabTextViewId());
                    tabTextView.setText(adapter.getPageTitle(i));
                    tabView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(positon);
                        }
                    });

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                    addView(tabView,params);

                }
                if (getChildAt(0) != null) {
                    View tabView = getChildAt(0);
                    tabView.setSelected(true);
                    lastSelectedTabView = tabView;
                }

            }
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.position = position;
        this.positionOffset = positionOffset;
        this.positionOffsetPixels = positionOffsetPixels;
        invalidate();

    }

    @Override
    public void onPageSelected(int position) {

        View childAt = getChildAt(position);
        if(childAt!=null){
            childAt.setSelected(true);
            if(lastSelectedTabView!=null){
                lastSelectedTabView.setSelected(false);
            }
            lastSelectedTabView= childAt;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public interface TabViewDelegate {

        View getTabView(int position);

        int getTabTextViewId();
    }


}
