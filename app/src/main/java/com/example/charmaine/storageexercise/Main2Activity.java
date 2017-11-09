package com.example.charmaine.storageexercise;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Environment;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
public class Main2Activity extends AppCompatActivity {

    private TextView text;
    private String filename;
    private String output;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (TextView)findViewById(R.id.text);
        filename = getIntent().getStringExtra("file");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

    }


    public void LoadFromSharedPreferences(View view){
//        String data = ;
        text = (TextView)findViewById(R.id.text);
        text.setText(preferences.getString("data",""));
    }

    public void LoadFromInternalStorage(View view) {
        File dir = getFilesDir();
        File file = new File(dir,filename + ".txt");
        readData(file);
    }

    public void LoadFromInternalCacheStorage(View view) {
        File dir = getCacheDir();
        File file = new File(dir,filename + ".txt");
        readData(file);
    }

    public void LoadFromExternalCacheStorage(View view) {
        File dir = getExternalCacheDir();
        File file = new File(dir,filename + ".txt");
        readData(file);
    }

    public void LoadFromExternalPrivateStorage(View view) {
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir,filename + ".txt");
        readData(file);
    }

    public void LoadFromExternalPublicStorage(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = new File(dir,filename + ".txt");
        readData(file);
    }


    public void Back(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void readData(File file)
    {
        FileInputStream fileInputStream=null;

        try {
            fileInputStream = new FileInputStream(file);
            int read=-1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read=fileInputStream.read())!=-1)
            {
                stringBuffer.append((char)read);
            }
            text.setText(stringBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}