package com.qual1ty.yashi_git.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.qual1ty.yashi_git.R;

/**
 * Created by Tianci on 16/4/12.
 */
public class ChangeColorIconWithTextView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private int mColor = 0xFF45C01A;

    private float mAlpha = 0.0F;
    private Bitmap mIconBitmap;
    private Rect mIconRect;
    private String mText = "微信";

    private int mTextSize = (int) TypedValue.applyDimension(2, 10.0F, getResources().getDisplayMetrics());
    private Paint mTextPaint;
    private Rect mTextBound = new Rect();
    private int mTextColor;
    private static final String INSTANCE_STATE = "instance_state";
    private static final String STATE_ALPHA = "state_alpha";

    public ChangeColorIconWithTextView(Context context) {
        super(context);
    }

    public ChangeColorIconWithTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ChangeColorIconView);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorIconView_view_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    this.mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconView_view_color:
                    this.mColor = a.getColor(attr, 0x45C01A);
                    break;
                case R.styleable.ChangeColorIconView_text_color:
                    this.mTextColor = a.getColor(attr, 0xff555555);
                    break;
                case R.styleable.ChangeColorIconView_view_text:
                    this.mText = a.getString(attr);
                    break;
                case R.styleable.ChangeColorIconView_text_size:
                    this.mTextSize = ((int) a.getDimension(attr,
                            TypedValue.applyDimension(2, 10.0F,
                                    getResources().getDisplayMetrics())));

            }

        }

        a.recycle();

        this.mTextPaint = new Paint();
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setColor(0xff555555);
        this.mTextPaint.setColor(mTextColor);
        this.mTextPaint.getTextBounds(this.mText, 0, this.mText.length(), this.mTextBound);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int bitmapWidth = Math.min(getMeasuredWidth() - getPaddingLeft() -
                getPaddingRight(), getMeasuredHeight() - getPaddingTop() -
                getPaddingBottom() - this.mTextBound.height());

        int left = getMeasuredWidth() / 2 - bitmapWidth / 2;
        int top = (getMeasuredHeight() - this.mTextBound.height()) / 2 - bitmapWidth /
                2;

        this.mIconRect = new Rect(left, top, left + bitmapWidth, top + bitmapWidth);
    }

    protected void onDraw(Canvas canvas) {
        int alpha = (int) Math.ceil(255.0F * this.mAlpha);
        canvas.drawBitmap(this.mIconBitmap, null, this.mIconRect, null);
        setupTargetBitmap(alpha);
        drawSourceText(canvas, alpha);
        drawTargetText(canvas, alpha);
        canvas.drawBitmap(this.mBitmap, 0.0F, 0.0F, null);
    }

    private void setupTargetBitmap(int alpha) {
        this.mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        this.mCanvas = new Canvas(this.mBitmap);
        this.mPaint = new Paint();
        this.mPaint.setColor(this.mColor);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setAlpha(alpha);
        this.mCanvas.drawRect(this.mIconRect, this.mPaint);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.mPaint.setAlpha(255);
        this.mCanvas.drawBitmap(this.mIconBitmap, null, this.mIconRect, this.mPaint);
    }

    private void drawSourceText(Canvas canvas, int alpha) {
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setColor(0xff555555);
        this.mTextPaint.setAlpha(255 - alpha);
        canvas.drawText(this.mText, this.mIconRect.left + this.mIconRect.width() / 2 -
                        this.mTextBound.width() / 2,
                this.mIconRect.bottom + this.mTextBound.height(), this.mTextPaint);
    }

    private void drawTargetText(Canvas canvas, int alpha) {
        this.mTextPaint.setColor(this.mColor);
        this.mTextPaint.setAlpha(alpha);
        canvas.drawText(this.mText, this.mIconRect.left + this.mIconRect.width() / 2 -
                        this.mTextBound.width() / 2,
                this.mIconRect.bottom + this.mTextBound.height(), this.mTextPaint);
    }

    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else
            postInvalidate();
    }

    public void setIconColor(int color) {
        this.mColor = color;
    }

    public void setIcon(int resId) {
        this.mIconBitmap = BitmapFactory.decodeResource(getResources(), resId);
        if (this.mIconRect != null)
            invalidateView();
    }

    public void setIcon(Bitmap iconBitmap) {
        this.mIconBitmap = iconBitmap;
        if (this.mIconRect != null)
            invalidateView();
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instance_state", super.onSaveInstanceState());
        bundle.putFloat("state_alpha", this.mAlpha);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if ((state instanceof Bundle)) {
            Bundle bundle = (Bundle) state;
            this.mAlpha = bundle.getFloat("state_alpha");
            super.onRestoreInstanceState(bundle.getParcelable("instance_state"));
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
