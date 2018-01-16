package widgets;

/**
 * Created by Elisa Capololo on 16-01-2018.
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Alexandre Juca on 10/12/2017.
 */

public class PlayPauseButton extends View {


    private final static double SQRT_3 = Math.sqrt(3);
    private final static int SPEED = 1;
    private final Point mPoint;
    private Paint mPaint;
    private Path mLeftPath;
    private Path mRightPath;
    private ValueAnimator mCenterEdgeAnimator;
    private ValueAnimator mLeftEdgeAnimator;
    private ValueAnimator mRightEdgeAnimator;
    private boolean mPlayed;
    private int mBackgroundColor = Color.BLACK;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener =
            new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    invalidate();
                }
            };

    private OnControlStatusChangeListener mListener;

    public PlayPauseButton(Context context) {
        this(context, null, 0);
    }

    public PlayPauseButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayPauseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPoint = new Point();
        initView();
    }

    private void initView() {
        setUpPaint();
        setUpPath();
        setUpAnimator();
    }

    private void setUpAnimator() {
        if (mPlayed) {
            mCenterEdgeAnimator = ValueAnimator.ofFloat(1.f, 1.f);
            mLeftEdgeAnimator = ValueAnimator.ofFloat((float) (-0.2f * SQRT_3), (float) (-0.2f * SQRT_3));
            mRightEdgeAnimator = ValueAnimator.ofFloat(1.f, 1.f);
        } else {
            mCenterEdgeAnimator = ValueAnimator.ofFloat(0.5f, 0.5f);
            mLeftEdgeAnimator = ValueAnimator.ofFloat(0.f, 0.f);
            mRightEdgeAnimator = ValueAnimator.ofFloat(0.f, 0.f);
        }

        mCenterEdgeAnimator.start();
        mLeftEdgeAnimator.start();
        mRightEdgeAnimator.start();
    }

    private void setUpPaint() {
        mPaint = new Paint();
        mPaint.setColor(mBackgroundColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void setUpPath() {
        mLeftPath = new Path();
        mRightPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPoint.setHeight(canvas.getHeight());
        mPoint.setWidth(canvas.getWidth());

        mLeftPath.reset();
        mRightPath.reset();

        mLeftPath.moveTo(mPoint.getX(-0.5 * SQRT_3), mPoint.getY(1.f));
        mLeftPath.lineTo(mPoint.getY((Float) mLeftEdgeAnimator.getAnimatedValue()) + 0.7f,
                mPoint.getY((Float) mCenterEdgeAnimator.getAnimatedValue()));
        mLeftPath.lineTo(mPoint.getY((Float) mLeftEdgeAnimator.getAnimatedValue()) + 0.7f,
                mPoint.getY(-1 * (Float) mCenterEdgeAnimator.getAnimatedValue()));
        mLeftPath.lineTo(mPoint.getX(-0.5 * SQRT_3), mPoint.getY(-1.f));

        mRightPath.moveTo(mPoint.getY(-1 * (Float) mLeftEdgeAnimator.getAnimatedValue()),
                mPoint.getY((Float) mCenterEdgeAnimator.getAnimatedValue()));
        mRightPath.lineTo(mPoint.getX(0.5 * SQRT_3),
                mPoint.getY((Float) mRightEdgeAnimator.getAnimatedValue()));
        mRightPath.lineTo(mPoint.getX(0.5 * SQRT_3),
                mPoint.getY(-1 * (Float) mRightEdgeAnimator.getAnimatedValue()));
        mRightPath.lineTo(mPoint.getY(-1 * (Float) mLeftEdgeAnimator.getAnimatedValue()),
                mPoint.getY(-1 * (Float) mCenterEdgeAnimator.getAnimatedValue()));

        canvas.drawPath(mLeftPath, mPaint);
        canvas.drawPath(mRightPath, mPaint);
    }

    public void startAnimation() {
        mCenterEdgeAnimator = ValueAnimator.ofFloat(1.f, 0.5f);
        mCenterEdgeAnimator.setDuration(100 * SPEED);
        mCenterEdgeAnimator.addUpdateListener(mAnimatorUpdateListener);

        mLeftEdgeAnimator = ValueAnimator.ofFloat((float) (-0.2 * SQRT_3), 0.f);
        mLeftEdgeAnimator.setDuration(100 * SPEED);
        mLeftEdgeAnimator.addUpdateListener(mAnimatorUpdateListener);

        mRightEdgeAnimator = ValueAnimator.ofFloat(1.f, 0.f);
        mRightEdgeAnimator.setDuration(150 * SPEED);
        mRightEdgeAnimator.addUpdateListener(mAnimatorUpdateListener);

        if (!mPlayed) {
            mCenterEdgeAnimator.start();
            mLeftEdgeAnimator.start();
            mRightEdgeAnimator.start();
        } else {
            mCenterEdgeAnimator.reverse();
            mLeftEdgeAnimator.reverse();
            mRightEdgeAnimator.reverse();
        }
    }

    public void setOnControlStatusChangeListener(OnControlStatusChangeListener listener) {
        mListener = listener;
    }


    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.played = isPlayed();
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        setPlayed(savedState.played);
        setUpAnimator();
        invalidate();
    }

    @Override
    public void setBackground(Drawable background) {
    }


    public boolean isPlayed() {
        return mPlayed;
    }

    public void setPlayed(boolean played) {
        if (mPlayed != played) {
            mPlayed = played;
            invalidate();
        }
    }


    public void setColor(int color) {
        mBackgroundColor = color;
        mPaint.setColor(mBackgroundColor);
        invalidate();
    }

    public interface OnControlStatusChangeListener {
        void onStatusChange(View view, boolean state);
    }


    static class SavedState extends BaseSavedState {

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
        boolean played;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            played = (Boolean) in.readValue(null);
        }

        @Override
        public void writeToParcel(@NonNull Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(played);
        }
    }

    static class Point {

        private int width;

        private int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public float getX(float x) {
            return (width / 2) * (x + 1);
        }

        public float getY(float y) {
            return (height / 2) * (y + 1);
        }

        public float getX(double x) {
            return getX((float) x);
        }

        public float getY(double y) {
            return getY((float) y);
        }
    }
}
