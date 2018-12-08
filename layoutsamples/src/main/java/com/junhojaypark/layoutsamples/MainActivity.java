package com.junhojaypark.layoutsamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long initTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // 뒤로 가기 버튼을 눌렀을 때
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime < 3000) {
                finish();
            }
            Toast.makeText(this, "종료 하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show();
            initTime = System.currentTimeMillis();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
