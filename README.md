# LOL
LOL 앱은 Retrofit을 사용하여 가져온 데이터를 Recyclerview에 적용하고 여러 라이브러리를 적용해보기 위한 목적으로 만들었습니다.
<br>
<br>
<br>

### 전체적인 순서도
----------
![image](https://user-images.githubusercontent.com/72846127/229264807-b921d36c-2fc2-40d0-a381-3c86d1a939bf.png)

### 주요 기술
---------
- Retrofit을 사용해 API 통신을 하고 있습니다.
- DataBinding으로 UI와 데이터를 선언적 형식으로 결합하였습니다.
- LiveData를 이용해 Observer 패턴을 적용했습니다.
- MVVM 패턴으로 작성되었습니다.
<br>
<br>
<br>
### UI
---------
![image](https://user-images.githubusercontent.com/72846127/229265112-0c24abaf-78ba-43a8-9fb2-1d4d087c4532.gif)
-  실행하고 데이터를 가져오는 데 성공하면 “데이터 가져오기 성공!” 이라는 Toast 메세지를 출력합니다.
-  데이터를 가져오는 데 실패하면 “가져오기 실패” 라는 Toast 메세지를 출력합니다.
