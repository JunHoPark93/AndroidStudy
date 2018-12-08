package com.junhojaypark.sqlitesamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.junhojaypark.sqlitesamples.domain.MemoVO;

import io.realm.Realm;

public class RealmReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_read);

        TextView titleView = findViewById(R.id.realm_read_title);
        TextView contentView = findViewById(R.id.realm_read_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        Realm realm = Realm.getDefaultInstance();
        MemoVO vo = realm.where(MemoVO.class)
                    .equalTo("title", title)
                    .findFirst();

        contentView.setText(vo.getTitle());
        titleView.setText(vo.getMemo());

    }
}
