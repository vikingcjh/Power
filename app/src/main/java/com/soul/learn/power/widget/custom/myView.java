package com.soul.learn.power.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.soul.learn.power.R;


/**
 * Created by chenjianhua on 2017/6/1.
 */

public class myView extends View {
    //定义画笔和初始位置
    Paint p = new Paint();
    public float currentX = 50;
    public float currentY = 50;
    public int textColor;


    public myView(Context context) {
        super(context);
    }

    public myView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.testView);
        String text = a.getString(R.styleable.testView_text);
        textColor = a.getColor(R.styleable.testView_testtextColor, Color.BLACK);
        a.recycle();
    }

    public myView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个蓝色的圆形
        p.setColor(Color.BLUE);
        canvas.drawColor(Color.GREEN);
        canvas.drawCircle(currentX,currentY,30,p);
        //设置文字和颜色，这里的颜色是资源文件values里面的值
        p.setColor(textColor);
        canvas.drawText("BY finch",currentX-30,currentY+50,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        currentX = event.getX();
        currentY = event.getY();
        invalidate();//重新绘制图形
        return true;
    }

}
