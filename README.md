# AndroidStudy
repository for android study :)


## 기본 기능들의 주요 함수 정리 (참고용)
#### 1. Dialog

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


#### 2. Event

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
#### 3. Resource

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

#### git ignore 설정 : github 공식 repository에서의 android gitignore 참조
#### 기본 내용정리출처 : 1. 깡샘의 안드로이드 프로그래밍 - 강성윤 저