package com.suyonoion.textpelangi;

/**
 * Modified by Suyono on 4/11/2015.
 */

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTextPelangi extends EditText
{
    public interface OnTextChangedListener
    {
        public void onTextChanged(String text);
    }

    public OnTextChangedListener onTextChangedListener = null;
    public int updateDelay = 1000;

    private final Handler updateHandler = new Handler();
    private final Runnable updateRunnable =
            new Runnable()
            {
                @Override
                public void run()
                {
                    Editable e = getText();

                    if( onTextChangedListener != null )
                        onTextChangedListener.onTextChanged( e.toString() );

                    UbahJadiPelangi(e);
                }
            };
    private boolean modified = true;

    public EditTextPelangi(Context context)
    {
        super( context );
        init();
    }

    public EditTextPelangi(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        init();
    }

    private void init()
    {
        setHorizontallyScrolling( false );
        updateHandler.postDelayed(
                updateRunnable,
                updateDelay);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable e) {

                if( !modified )
                    return;


                updateHandler.postDelayed(
                        updateRunnable,
                        updateDelay);
            }
        });

    }

    private Editable UbahJadiPelangi(Editable e)
     {

         Pattern pattern = Pattern.compile(e.toString());
         for( Matcher m = pattern.matcher( e );
              m.find(); )
             e.setSpan(
                     new PelangiSpan(getContext()),m.start(),m.end(),0);
         return e;
    }

    private class PelangiSpan extends CharacterStyle implements UpdateAppearance {
        private final int[] colors;
        public PelangiSpan(Context context){
            colors = context.getResources().getIntArray(setResource("daftar_warna_pelangi","array"));
        }
        @Override
        public void updateDrawState(TextPaint paint){
            paint.setStyle(Paint.Style.FILL);
            Shader shader = new LinearGradient(0,0,0,paint.getTextSize()*colors.length,colors,null,Shader.TileMode.MIRROR);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
        }
    }
    public int setResource(String name, String Type)
    {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }
}
