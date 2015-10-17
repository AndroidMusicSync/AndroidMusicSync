package de.sync.androidmusicsync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMaster(View view) {
        Intent intent = new Intent(this, MasterActivity.class);
        startActivity(intent);
    }

    public void openServant(View view) {
        Intent intent = new Intent(this, ServantActivity.class);
        startActivity(intent);
    }
}
