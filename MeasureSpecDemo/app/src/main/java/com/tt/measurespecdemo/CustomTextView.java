package com.tt.measurespecdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by tuozhaobing on 15-11-19.
 */
public class CustomTextView extends TextView {
    //Measure the view and its content to determine the measured width and the measured height.
    // This method is invoked by measure(int, int) and should be overridden by subclasses to provide accurate and efficient measurement of their contents.
    //CONTRACT: When overriding this method, you must call setMeasuredDimension(int, int) to store the measured width and height of this view.
    // Failure to do so will trigger an IllegalStateException, thrown by measure(int, int).
    // Calling the superclass' onMeasure(int, int) is a valid use.

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY){
            Log.e("SpecMode","MeasureSpec.EXACTLY");
            result = specSize;
        }else {
            result = 100;
            if (specMode == MeasureSpec.AT_MOST){
                Log.e("SpecMode","MeasureSpec.AT_MOST");
                result = Math.min(result,specSize);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else {
            result = 100;
            if (specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return result;
    }
}
