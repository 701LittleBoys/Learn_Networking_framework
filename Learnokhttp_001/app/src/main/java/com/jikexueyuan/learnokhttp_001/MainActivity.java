package com.jikexueyuan.learnokhttp_001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.button1 = (Button) findViewById(R.id.button1);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_test);
        button1.startAnimation(animation);
    }

}
