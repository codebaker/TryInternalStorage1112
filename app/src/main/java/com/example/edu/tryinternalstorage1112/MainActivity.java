package com.example.edu.tryinternalstorage1112;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.buttonRead)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonWrite)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editTextFileName = findViewById(R.id.editText);
        EditText editTextFileContents = findViewById(R.id.editText3);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            switch (v.getId()){
                case R.id.buttonRead:
                    fileInputStream = openFileInput(editTextFileName.getText().toString());
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    editTextFileContents.setText(new String(buffer));
                    break;
                case R.id.buttonWrite:
                    fileOutputStream = openFileOutput(editTextFileName.getText().toString(),Context.MODE_PRIVATE);
                    fileOutputStream.write(editTextFileContents.getText().toString().getBytes());
                    editTextFileContents.setText("");
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //예외가 발생했어도 반듯이 실행해야 하는 코드.
            //예를 들어... file이나 db의 close()문
            try{
                if(fileInputStream!=null){
                    fileInputStream.close();
                }
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
