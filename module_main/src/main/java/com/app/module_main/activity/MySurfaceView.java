package com.app.module_main.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.app.module_main.R;
import com.shao.app.utils.Logger;


/**
 * Created by user on 2018/5/11.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Paint paint;
    private int left;
    private int right;
    private int top;
    private int bottom;
    public boolean runFlag = true;
    private Bitmap bitmapDefault;
    private Bitmap bitmapTake;
    private Rect rectSrc;
    private Rect rect;
    float ratio;
    int lwidth;
    int lstart;

    public DrawThread drawThread;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = getHolder();
        holder.addCallback(this);
        bitmapDefault = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        rect = new Rect(0, 0, bitmapDefault.getWidth(), bitmapDefault.getHeight());
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        runFlag = true;
        left = getLeft();
        right = getRight();
        top = getTop();
        bottom = getBottom();
        int start = (right - bottom) / 2;
        rect = new Rect(start, top, start + bottom, bottom);
        rectSrc = new Rect();
        Logger.d("qtt", "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (drawThread == null || !drawThread.isAlive()) {
            drawThread = new DrawThread();
            drawThread.start();
        }
        Logger.d("qtt", "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        runFlag = false;
//        if (drawThread.isAlive()) {
//            drawThread=null;
//        }
        Logger.d("qtt", "surfaceChanged");

    }

    public class DrawThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {
                if (runFlag) {
                    try {
                        if (holder == null) {
                            return;
                        }
                        Canvas canvas = holder.lockCanvas();
                        if (canvas == null) {
                            return;
                        }
                        canvas.drawColor(Color.WHITE);
                        if (bitmapTake != null && !bitmapTake.isRecycled()) {
                            rectSrc.set(0, 0, bitmapTake.getWidth(), bitmapTake.getHeight());
                            ratio = (float) rectSrc.width() / (float) rectSrc.height();
                            lwidth = (int) (rect.height() * ratio);
                            lstart = (right - lwidth) / 2;
                            rect.set(lstart, 0, lstart + lwidth, bottom);
                            canvas.drawBitmap(bitmapTake, rectSrc, rect, paint);
                        } else if (bitmapDefault != null && !bitmapDefault.isRecycled()) {
                            rectSrc.set(0, 0, bitmapDefault.getWidth(), bitmapDefault.getHeight());
                            canvas.drawBitmap(bitmapDefault, rectSrc, rect, paint);
                        } else {
                            canvas.drawText("未能正确显示影像", 0f, 0, paint);
                        }
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void setBitmapTake(Bitmap bitmap) {
        bitmapTake = bitmap;
    }

}
