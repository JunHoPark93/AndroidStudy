package com.junhojaypark.sqlitesamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.junhojaypark.sqlitesamples.domain.MemoVO;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText titleView;
    EditText contentView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleView = findViewById(R.id.add_title);
        contentView = findViewById(R.id.add_content);
        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 일반 sqlite
//        String title = titleView.getText().toString();
//        String content = contentView.getText().toString();
//
//        DBHelper helper = new DBHelper(this);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        db.execSQL("insert into tb_memo (title, content) values (?,?)", new String[] {title, content});
//        db.close();
//
//        Intent intent = new Intent(this, ReadDBActivity.class);
//        startActivity(intent);

        // realm 적용
        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemoVO vo = realm.createObject(MemoVO.class);
                vo.setTitle(title);
                vo.setMemo(content);
            }
        });

        Intent intent = new Intent(this, RealmReadActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
