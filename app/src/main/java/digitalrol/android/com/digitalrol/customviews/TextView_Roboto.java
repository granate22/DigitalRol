package digitalrol.android.com.digitalrol.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by diego.mazzitelli on 11/04/2015.
 */
public class TextView_Roboto extends TextView {

    public TextView_Roboto(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        createFont();
    }

    public TextView_Roboto(Context context, AttributeSet attrs) {
        super(context, attrs);
        createFont();
    }

    public TextView_Roboto(Context context) {
        super(context);
        createFont();
    }

    public void createFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Thin.ttf");
        setTypeface(font);
    }
}