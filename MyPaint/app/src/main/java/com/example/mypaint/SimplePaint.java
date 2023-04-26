package com.example.mypaint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {
    private final List<Paint> mPaintList;
    private final List<Path> mPathList;
    private Paint currentPaint;
    private Path currentPath;
    private final ColorDrawable currentColor;
    private Shape currentShape = Shape.LINE;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintList=new ArrayList<Paint>();
        mPathList=new ArrayList<Path>();
        currentColor = new ColorDrawable();
        currentColor.setColor(Color.WHITE);
        initLayerDraw();
    }
    public void  initLayerDraw(){
        currentPaint=new Paint();
        currentPath = new Path();
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(30);
        currentPaint.setColor(currentColor.getColor());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i<mPaintList.size();i++){
            canvas.drawPath(mPathList.get(i), mPaintList.get(i));
        }
        canvas.drawPath(currentPath, currentPaint);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float ly,lx, startX, startY;
        lx=event.getX();
        ly=event.getY();
        startX = 0;
        startY = 0;

        switch(event.getAction()){
            case (MotionEvent.ACTION_DOWN):
                currentPath.moveTo(lx,ly);
                currentPath.lineTo(lx,ly);
                startX = lx;
                startY = ly;
                break;
            case (MotionEvent.ACTION_MOVE):
                currentPath.lineTo(lx,ly);
                break;
            case (MotionEvent.ACTION_UP):
                currentPath.lineTo(lx,ly);
                mPaintList.add(currentPaint);
                mPathList.add(currentPath);
                initLayerDraw();
                currentPaint.clearShadowLayer();
                break;
            default: break;
        }

        switch(currentShape) {
            case CIRCLE:
                float radius = (float) Math.sqrt(Math.pow((lx - startX), 2) + Math.pow((ly - startY), 2));
                currentPath.reset();
                currentPath.addCircle(startX, startY, radius, Path.Direction.CW);
                break;
            case SQUARE:
                float side = Math.abs(startX - lx) > Math.abs(startY - ly) ? Math.abs(startX - lx) : Math.abs(startY - ly);
                float left = Math.min(startX, lx);
                float top = Math.min(startY, ly);
                float right = left + side;
                float bottom = top + side;
                currentPath.reset();
                currentPath.addRect(left, top, right, bottom, Path.Direction.CW);
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }
    public void setColor(Color color) {
        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
    }
    public void setShape (Shape shape){
        currentShape = shape;
    }
    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);

    }
    public void clearCanvas() {
        mPaintList.clear();
        mPathList.clear();
        currentPath.reset();
        initLayerDraw();
        invalidate();
    }
    public void undoLastPath() {
        if (!mPathList.isEmpty()) {
            int lastIndex = mPathList.size() - 1;
            mPathList.remove(lastIndex);
            mPaintList.remove(lastIndex);
            invalidate();
        }
    }
}