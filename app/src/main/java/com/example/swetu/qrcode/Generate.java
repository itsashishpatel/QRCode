package com.example.swetu.qrcode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import net.glxn.qrgen.android.QRCode;

import org.json.JSONException;
import org.json.JSONObject;




public class Generate extends AppCompatActivity {

    EditText edtname,edtemail,edtphone;
    Button btngen,btnscan;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate);

        edtname = (EditText)findViewById(R.id.edtname);
        edtemail = (EditText)findViewById(R.id.edtemail);
        edtphone = (EditText)findViewById(R.id.edtphone);
        imageView =(ImageView)findViewById(R.id.imageView);

        btngen = (Button)findViewById(R.id.btngen);
        btnscan = (Button)findViewById(R.id.btnscan);

        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Generate.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btngen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname = edtname.getText().toString();
                String stremail = edtemail.getText().toString();
                String strphone = edtphone.getText().toString();

                if (strname.length()==0 || stremail.length()==0 || strphone.length()==0){
                    Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_LONG).show();
                }else{
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name",strname);
                        jsonObject.put("email",stremail);
                        jsonObject.put("phone",strphone);

                        Bitmap myBitmap = QRCode.from(jsonObject.toString()).bitmap();
                        imageView.setImageBitmap(myBitmap);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
