package com.example.test0223;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.test0223.mvp.ui.activity.MainActivity;
import com.example.test0223.util.CountdownUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.guild_num)
    TextView guildNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        CountdownUtils.countdown(3).subscribe(integer -> {
           guildNum.setText(integer+"S");
           if (integer == 0){
               startActivity(new Intent(GuideActivity.this,MainActivity.class));
           }
        });
    }
    }
