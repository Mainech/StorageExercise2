package com.example.charmaine.storageexercise;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText filename;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }


    public void Next(View view) {

        filename = (EditText)findViewById(R.id.editText3);
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("file", filename.getText().toString());
        startActivity(intent);


    }

    public void SaveInSharedPreferences(View view){
        String text=editText.getText().toString();
        filename = (EditText)findViewById(R.id.editText3);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", text);
        //editor.putString("filename", filename.getText().toString());
        editor.commit();
        editor.apply();

        File dir = getFilesDir();
        File file = new File(dir, filename.getText().toString() + ".txt");
        writeData(file, text);
    }

    public void SaveInInternalStorage(View view) {
        String text=editText.getText().toString();
        filename = (EditText)findViewById(R.id.editText3);
        File dir = getFilesDir();
        File file = new File(dir, filename.getText().toString() + ".txt");
        writeData(file, text);

    }

    public void SaveInInternalCacheStorage(View view) {
        String text=editText.getText().toString();
        filename = (EditText)findViewById(R.id.editText3);
        File dir = getCacheDir();
        File file = new File(dir,filename.getText().toString() + ".txt");
        writeData(file, text);

    }

    public void SaveInExternalCacheStorage(View view) {
        String text=editText.getText().toString();
        filename = (EditText)findViewById(R.id.editText3);
        File dir = getExternalCacheDir();
        File file = new File(dir,filename.getText().toString() + ".txt");
        writeData(file, text);

    }

    public void SaveInExternalPrivateStorage(View view) {
        String text=editText.getText().toString();
        filename = (EditText)findViewById(R.id.editText3);
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir,filename.getText().toString() + ".txt");
        writeData(file, text);

    }

    public void SaveInExternalPublicStorage(View view) {


        String text=editText.getText().toString();
        filename = (EditText)findViewById(R.id.editText3);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = new File(dir,filename.getText().toString() + ".txt");
        writeData(file, text);

    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void writeData(File file,String text)
    {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();

    }

}

