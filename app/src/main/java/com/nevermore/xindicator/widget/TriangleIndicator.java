package com.nevermore.xindicator.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/9/23.
 */

public class TriangleIndicator extends XIndicator {


    private Path path;

    public TriangleIndicator(Context context) {
        super(context);
    }

    public TriangleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        int indicatorHeigh = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        setIndicatorHeight(indicatorHeigh);


    }


    private static final String TAG = "TriangleIndicator";

    @Override
    protected void drawIdicator(Canvas canvas, Rect rect, Paint paint) {
        path = new Path();

        int halfWidth = rect.height()/2;

        Log.i(TAG, "drawIdicator: "+rect.width());
        Log.i(TAG, "drawIdicator: "+rect.height());


        path.moveTo(rect.centerX(),rect.top);
        path.lineTo(rect.centerX()-halfWidth,rect.bottom);
        path.lineTo(rect.centerX()+halfWidth,rect.bottom);
        path.close();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawPath(path,paint);


    }

    @Override
    protected void drawTabBg(Canvas canvas, Rect rect, Paint paint) {
        super.drawTabBg(canvas, rect, paint);

        paint.setColor(Color.GREEN);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF,rect.height()/2,rect.height()/2,paint);
    }
}
