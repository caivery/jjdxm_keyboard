package com.dou361.jjdxm_keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dou361.baseutils.utils.StringUtils;
import com.dou361.jjdxm_keyboard.activity.CustomSimpleActivity;
import com.dou361.jjdxm_keyboard.activity.KeyBoardActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_emoji;
    private Button btn_customkeyboard;
    private Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_emoji = (Button) findViewById(R.id.btn_emoji);
        btn_customkeyboard = (Button) findViewById(R.id.btn_customkeyboard);
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_emoji.setOnClickListener(this);
        btn_customkeyboard.setOnClickListener(this);
        btn_test.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_emoji:
                startActivity(CustomSimpleActivity.class);
                break;
            case R.id.btn_customkeyboard:
                startActivity(KeyBoardActivity.class);
                break;
            case R.id.btn_test:
                double money = 24.569;

//                Toast.makeText(MainActivity.this,money + "-------------getMenoy----" + StringUtils.changeNumber(money),Toast.LENGTH_LONG).show();
//                Toast.makeText(MainActivity.this,money + "-------------getMenoy----" + StringUtils.getMenoy(money),Toast.LENGTH_LONG).show();
Log.e("dou361",money + "-------------getMenoy----" + StringUtils.changeNumber(money));
Log.e("dou361",money + "-------------getMenoy----" + StringUtils.getMenoy(money));
                break;
        }
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }


}
