package net.awpspace.customviewwithdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 28-Jul-15.
 */
public class MyView extends View {
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //default sColum = 5;
    private static int sColum = 5;

    float colum_height_ratio[] = {0.9f, 0.7f, 0.5f, 0.8f, 0.85f};

    private float mColumWeight = 25;
    private float mColumMargin = 5;
    private int mColumColor = 0xFFE64A19;
    private Paint mColumPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        mColumPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColumPaint.setStyle(Paint.Style.FILL);
        mColumPaint.setColor(mColumColor);
        mColumPaint.setShadowLayer(4, 2, 2, 0x80000000);

        float w = getWidth();
        float h = getHeight();
        float pl = getPaddingLeft();
        float pr = getPaddingRight();
        float pt = getPaddingTop();
        float pb = getPaddingBottom();

        float x0 = getPaddingLeft();
        float y0 = h - getPaddingBottom();

        float min_margin_colum = 5;
        float max_weight_colum = (w - pl - pr - min_margin_colum * (sColum - 1)) / sColum;

        if (mColumWeight > max_weight_colum) {
            mColumWeight = max_weight_colum;
            mColumMargin = min_margin_colum;
        } else {
            mColumMargin = (w - pl - pr - sColum * mColumWeight) / (sColum - 1);
        }

        for (int i = 0; i < sColum; i++) {
            float left = x0 + i * mColumWeight + i * mColumMargin;
            float right = x0 + i * mColumWeight + i * mColumMargin + mColumWeight;
            float top = y0 - colum_height_ratio[i] * (h - pt - pb);
            float bottom = y0;

            canvas.drawRect(left, top, right, bottom, mColumPaint);
        }
    }
}

