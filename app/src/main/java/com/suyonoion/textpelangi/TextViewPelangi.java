package com.suyonoion.textpelangi;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Suyono on 6/23/2015.
 * Copyright (c) 2015 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */
public class TextViewPelangi extends TextView {

    public TextViewPelangi(Context context) {
        super(context);
        if (!isInEditMode()) {
            KodingTextPelangi();
        }
    }

    public TextViewPelangi(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            KodingTextPelangi();
        }
    }

    private void KodingTextPelangi()
    {
        UbahJadiPelangi(getText().toString());
    }

    private void UbahJadiPelangi(String query) {
        String kalimat = getText().toString();
        SpannableString sp_s = new SpannableString(kalimat);
        Pattern pattern = Pattern.compile(query.toLowerCase());
        Matcher cocokkan = pattern.matcher(kalimat.toLowerCase());
        while (cocokkan.find()) {
            sp_s.setSpan(new PelangiSpan(getContext()),cocokkan.start(),cocokkan.end(),0);
        }
        setText(sp_s);
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
