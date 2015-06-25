package com.suyonoion.textpelangi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class KodePelangi extends Activity {
    Button copy,copy1;
    EditText copyTxt,copyTxt1;
    int sdk = android.os.Build.VERSION.SDK_INT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kode);
        copy = (Button) findViewById(R.id.bt_copy1);
        copy1 = (Button) findViewById(R.id.bt_copy2);
        copyTxt = (EditText) findViewById(R.id.kode_textview_pelangi);
        copyTxt1 = (EditText) findViewById(R.id.kode_edittext_pelangi);
        copy.setOnClickListener(new View.OnClickListener() {
            String CopyText;
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                CopyText = copyTxt.getText().toString();
                if (CopyText.length() != 0) {
                    if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(CopyText);
                        Toast.makeText(getApplicationContext(), "kode TEXTVIEW PELANGI ke Clipboard", Toast.LENGTH_SHORT).show();
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("Clip", CopyText);
                        Toast.makeText(getApplicationContext(), "kode TEXTVIEW PELANGI ke Clipboard", Toast.LENGTH_SHORT).show();
                        clipboard.setPrimaryClip(clip);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Text Kosong, Tidak ada apapun yang ter-Copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
        copy1.setOnClickListener(new View.OnClickListener() {
            String CopyText1;
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                CopyText1 = copyTxt1.getText().toString();
                if (CopyText1.length() != 0) {
                    if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(CopyText1);
                        Toast.makeText(getApplicationContext(), "kode EDITTEXT PELANGI ke Clipboard", Toast.LENGTH_SHORT).show();
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("Clip", CopyText1);
                        Toast.makeText(getApplicationContext(), "kode EDITTEXT PELANGI ke Clipboard", Toast.LENGTH_SHORT).show();
                        clipboard.setPrimaryClip(clip);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Text Kosong, Tidak ada apapun yang ter-Copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}