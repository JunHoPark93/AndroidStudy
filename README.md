# AndroidStudy
repository for android study :)

### git ignore 설정 : github 공식 repository에서의 android gitignore 참조

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