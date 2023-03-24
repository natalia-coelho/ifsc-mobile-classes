package br.com.ifsc.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SimplePaint extends View {
    Paint mPaint;
    Path mPath;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPath = new Path();

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float lx, ly;
        lx=event.getX();
        ly=event.getY();
        switch (event.getAction()){
            case (MotionEvent.ACTION_DOWN):
                mPath.moveTo(lx, ly);
                mPath.lineTo(lx, ly);
                break;
            case (MotionEvent.ACTION_MOVE):
                mPath.lineTo(lx, ly);
                break;
            case (MotionEvent.ACTION_UP):
                mPath.lineTo(lx, ly);
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }
}
