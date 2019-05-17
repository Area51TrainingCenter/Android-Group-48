package com.area51.clase01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("clase01", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("clase01", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("clase01", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("clase01", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("clase01", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("clase01", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("clase01", "onRestart");
    }
}
