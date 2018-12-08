package com.junhojaypark.sqlitesamples.domain;

import io.realm.RealmObject;

public class MemoVO extends RealmObject {
    private String title;
    private String memo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
