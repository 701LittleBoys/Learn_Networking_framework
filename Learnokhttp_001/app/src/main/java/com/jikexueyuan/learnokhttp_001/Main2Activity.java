package com.jikexueyuan.learnokhttp_001;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.button2 = (Button) findViewById(R.id.button2);
        this.button2.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable drawable = (AnimationDrawable) this.button2.getBackground();
        drawable.start();
    }
}
