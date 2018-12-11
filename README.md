# AndroidStudy
repository for android study :)


## 기본 기능들의 주요 함수 정리 (참고용)
### 1. Dialog

1.1 기본 Alert Dialog
다양한 설정에 따라 많은 dialog가 나오기 때문에 Builder class로 생성해 준다. 만든 후 맞는 dialog에 builder.create()를 해준다. 
마지막에 dialog.show()로 띄워준다.

주요 함수 : setIcon, setTitle (다이얼로그 제목), setMessage, setPositiveButton, setNegativeButton

1.2 List Dialog
resource에 arrays를 정의 해주고 setSingleChoiceItems, setMultiChoiceItems에서 불러와준다. 

1.3 Progress Dialog
setProgressStyle로 스타일을 지정할 수 있다.

1.4 Custom Dialog
dialog layout을 만든 뒤 LayoutInflater 생성 (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)
View 객체 생성 후 inflater 객체의 inflate함수로 layout불러 옴 
builder의 setView에 view를 세팅


### 2. Event

2.1 Delegation Event Model
뷰에서 발생하는 이벤트를 처리한다.
Event Source(View) 에 Event Handler(Event 처리코드)를 Listener로 연결시켜주는 작업

```java
button.setOnClickListener(new EventHandler());

```
이런식으로 이벤트 핸들러 객체를 리스너 안에 넣어준다. 

2.2 Hierarchy Event Model
키 이벤트와 화면 터치 이벤트를 처리하는 모델이다.
```java
@Override
public boolean onTouchEvent(MotionEvent event) {
    return super.onTouchEvent(event);
}
```
### 3. Resource

3.1 폴더종류
drawable, layout, values, menu, xml, anim, raw, mipmap

3.2 values폴더 내용 (권장 파일명)
string.xml, colors.xml, styles.xml, arrays.xml, dimens.xml

```java
<resources>
    <dimen name="my_margin">16dp</dimen>
    <dimen name="my_padding">16dp</dimen>
</resources>

<Button 
    android:padding="@dimen/my_padding"/>

```
이런식으로 리소스 폴더에서 가져올 수 있다. xml에서의 컴포넌트들의 속성에 대한 중복성 감소
style과 theme도 마찬가지이다.


### 4. Display
4.1 크기
dp : Density Independent Pixels 스크린의 물리적 밀도에 기초한 단위 (권장)
sp : Scale Independent Pixels dp와 유사하고 폰트 크기에 적용 (권장)


### 5. Layout
layoutsamples 폴더 안
```java
<ScrollView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">
</ScrollView>
<LinearLayout>
</LinearLayout>
```
이렇게 weight 1 로 두고 밑에 레이아웃 하나정의해주면 LinearLayout은 여기서 저 바닥 끝까지밀린다.

```java
android:adjustViewBounds="true"
```
Set this to true if you want the ImageView to adjust its bounds to preserve the aspect ratio of its drawable. 


### 6. SQLITE
6.1 DB Helper 클래스의 정의

```java
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "filename", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
        }
    }
}
```
SQLiteOpenHelper추상클래스를 상속하여 메소드 구현을 해준다. OnCreate는 앱이 설치되고 한 번 호출되고 나중에 db수정을 할 때에는 onUpgrade를 호출해 준다.


```java
DBHelper helper = new DBHelper(this);
SQLiteDatabase db = helper.getWritableDatabase();

Cursor cursor = db.rawQuery("", null);

while(cursor.moveToNext()) {
    // cursor 조작
}

db.close();

```
받는 곳에서는 이런 식으로 db 헬퍼를 가져와 커서 객체를 사용하여 데이터를 조회한다.


### 7. REALM
7.1 REALM 환경 설정

Root에 있는 build.gradle에 dependencies에 
```java
classpath 'io.realm:realm-gradle-plugin:5.8.0'

```
를 추가해 주고

사용하는 module의 build.gradle에는
```java
apply plugin: 'realm-android' // 상단에추가
compileOptions {
     sourceCompatibility JavaVersion.VERSION_1_8
     targetCompatibility JavaVersion.VERSION_1_8
 }
```
를 추가해준다. 저기 compileOptions에는 소스와 타겟의 자바 버젼을 명시해주는데 이 내용이 없으면 오류가 발생한다.
https://developer.android.com/studio/write/java8-support#disable_jack 에서 참고를 해 보았다. 참조하는 써드 파티 모듈이 java 8 버젼을 사용한다면 명시해주라고 한다. 초기 프로젝트 설정할 때 소스와 타겟을 정한다면 상관없다.
내용을 덧붙이자면 javac(자바 컴파일러)가 class파일을 만들고 그 class 파일을 desugar하는 작업이 필요한데 이 작업을 가능케 해주는 것이 저 설정이다. desugar된 클래스파일을 dex컴파일러가 컴파일해 .dex파일로 만들어준다.

7.2 REALM 활용
안드로이드에서도 ORM을 쓸 수 있게 해준다. 
VO 객체에 RealmObject를 상속하게 한다.

```java
public class MemoVO extends RealmObject {}
```

간단한 접근 방식을 보자.

```java
Realm.init(this);
Realm realm = Realm.getDefaultInstance();
realm.executeTransaction(new Realm.Transaction() {
    @Override
    public void execute(Realm realm) {
       // vo 작업
    }
});

Realm realm = Realm.getDefaultInstance();
MemoVO vo = realm.where(MemoVO.class)
        .equalTo("title", title)
        .findFirst();
```

위에는 저장하는 작업 밑에는 반환하는 작업이다. 저장할때 executeTransaction으로 매핑작업을 해준 후 반환 할 때에는 저런 방식으로 반환하게 된다. 가시적으로 잘 보이니 넘어간다.


### SharedPreferences
앱의 데이터를 영속적으로 저장하기 위한 클래스. key-value형태로 저장되는데 자동으로 activity 이름의 파일내에 저장한다. 
getPreferences(int mode) : 다른 액티비티에서는 데이터를 이용을 못함.
getSharedPreferences(String name, int mode) : 다른곳에서 사용 가능


#### git ignore 설정 : github 공식 repository에서의 android gitignore 참조
#### 기본 내용정리출처 : 1. 깡샘의 안드로이드 프로그래밍 - 강성윤 저