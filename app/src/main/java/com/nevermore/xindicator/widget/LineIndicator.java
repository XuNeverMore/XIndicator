package com.nevermore.xindicator.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/9/23.
 */

public class LineIndicator extends XIndicator {
    public LineIndicator(Context context) {
        super(context);
    }

    public LineIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawIdicator(Canvas canvas, Rect rect, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect,paint);

    }
}
