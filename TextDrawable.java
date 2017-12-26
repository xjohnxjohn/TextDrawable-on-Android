import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import static java.security.AccessController.getContext;

public class TextDrawable extends Drawable {

    private String text;
    private Paint paint;
    Context context;
    Bitmap mBackground = null;
    int mDrawable;


    public TextDrawable(String text, Context context, int mDrawable) {

        this.text = text;

        this.paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30f);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(6f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);

        this.context = context;
        this.mDrawable = mDrawable;

        mBackground = BitmapFactory.decodeResource(context.getResources(),
                mDrawable);

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, -10,100, paint);
        //canvas.drawBitmap(mBackground,0,0,paint);

        Drawable drawable = context.getDrawable(mDrawable);

        Bitmap icon = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas1 = new Canvas(icon);
        drawable.setBounds(0, 0, canvas1.getWidth(), canvas1.getHeight());
        drawable.draw(canvas1);
        canvas.drawBitmap(icon,0,0,paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
