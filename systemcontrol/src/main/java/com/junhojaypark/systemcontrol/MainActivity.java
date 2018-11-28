package com.junhojaypark.systemcontrol;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button vibrationBtn;
    private Button systemBeepBtn;
    private Button customBeepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrationBtn = findViewById(R.id.btn_vibration);
        systemBeepBtn = findViewById(R.id.btn_systembeep);
        customBeepBtn = findViewById(R.id.btn_customsound);

        // Activity가 리스너를 구현했으므로 Activity자신이 구현한 것을 전달하기 위해 리스너에 this를 전달한 것!
        vibrationBtn.setOnClickListener(this);
        systemBeepBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);

        // button에 이벤트리스너를 각각 구현하는 방법 ~  익명 클래스
//        customBeepBtn.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(v == vibrationBtn) {
//                    // 수행
//                }
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vibration :
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                break;

            case R.id.btn_systembeep :
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
                ringtone.play();
                break;

            case R.id.btn_customsound :
                //MediaPlayer player = MediaPlayer.create(this, R.raw.음원명);
                //player.start();
                break;
        }
    }
}
