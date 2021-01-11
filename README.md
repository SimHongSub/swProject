# 소프트웨어공학 텀프로젝트

헌책 장터 시스템(used-book Marketplace)

### 유스케이스 다이어그램

<img width="1040" alt="스크린샷 2021-01-11 오후 3 29 55" src="https://user-images.githubusercontent.com/33109677/104152071-e9e9ce00-5421-11eb-831b-8441beb0908d.png">

### 클래스 다이어그램

<img width="1037" alt="스크린샷 2021-01-11 오후 3 32 48" src="https://user-images.githubusercontent.com/33109677/104152333-975ce180-5422-11eb-9166-3f9c83cabd5a.png">

### 시퀀스 다이어그램
<img width="1082" alt="스크린샷 2021-01-11 오후 3 33 03" src="https://user-images.githubusercontent.com/33109677/104152346-9f1c8600-5422-11eb-9751-9b45cb60528e.png">

## Implementation

### com.marketplace.model package
헌책 장터 시스템의 Model 관련 Class 들을 모아놓은 packcage 이다.  
Book Class : 책 정보를 담고 있는 model Class 이다.  
User Class : 사용자 정보를 담고 있는 model Class 이다.  

### com.marketplace.view package
헌책 장터 시스템의 View(시스템 화면) 관련 Class 들을 모아놓은 package 이다.  
Login Class : 로그인 화면을 담당하는 Class 이다.  
SignUp Class : 회원가입 화면을 담당하는 Class 이다.  
Main Class : 시스템 메인 화면을 담당하는 Class 이다.  
General Class : 사용자 페이지 화면을 담당하는 Class 이다.  
Admin Class : 관리자 페이지 화면을 담당하는 Class 이다.  

### com.marketplace.controller package
헌책 장터 시스템의 Controller(시스템 server side logic) 관련 Class 들을 모아놓은 package 이다.  
Controller Class : 각 Controller 의 공통 속성을 모아놓은 Class 이다(상속하여 사용).  
LoginController Class : 로그인과 관련된 logic 을 담당하는 Class 이다.  
SignUpController Class : 회원가입과 관련된 logic 을 담당하는 Class 이다.  
MainController Class : 시스템 메인 기능과 관련된 logic 을 담당하는 Class 이다.  
GeneralController Class : 사용자 페이지 기능과 관련된 logic 담당하을는 Class 이다.  
AdminController Class : 관리자 페이지 기능과 관련된 logic 을 담당하는 Class 이다.

### com.marketplace.exception package
각 Controller 의 Exception 관련 Class 들을 모아놓은 package 이다.  
LoginException Class : 로그인과 관련된 exception 을 담당하는 Class 이다.  
SignUpException Class : 회원가입과 관련된 exception 을 담당하는 Class 이다.  
MainException Class : 시스템 메인 기능과 관련된 exception 을 담당하는 Class 이다.  
GeneralException Class : 사용자 페이지 기능과 관련된 exception 담당하는 Class 이다.  
AdminException Class : 관리자 페이지 기능과 관련된 exception 을 담당하는 Class 이다.  

### com.marketplace.filesystem package
헌책 장터 시스템의 File I/O 관련 Class 들을 모아놓은 package 이다.  
FileProcessing Class : File I/O 를 담당하는 Class 이다.  

###com.marketplace.util package
헌책 장터 시스템의 외부 서비스 관련 Class 들을 모아놓은 package 이다.  
AES256Util Class : 비밀번호 암호화를 담당하는 Class 이다.  
EmailUtil Class : E-mail 서비스를 담당하는 Class 이다.  
ISBNUtil Class : ISBN 번호 생성을 담당하는 Class 이다.  

### com.marketplace.view.table package
각 화면(view)의 table 생성을 관련 Class 들을 모아놓은 package 이다.  
MainTableCell Class : 메인 페이지 table 생성을 담당하는 Class 이다.  
GeneralTableCell Class : 사용자 페이지 table 생성을 담당하는 Class 이다.  
AdminTableCell Class : 관리자 페이지 table 생성을 담당하는 Class 이다.

### com.marketplace.test package
각 Controller 의 단위 테스트 관련 Class 들을 모아놓은 package 이다.  
LoginControllerTest Class : 로그인과 관련된 logic test 를 담당하는 Class 이다.  
SignUpControllerTest Class : 회원가입과 관련된 logic test 를 담당하는 Class 이다.  
MainControllerTest Class : 시스템 메인 기능과 관련된 logic test 를 담당하는 Class 이다.  
GeneralControllerTest Class : 사용자 페이지 기능과 관련된 logic test 를 담당하는 Class 이다.  
AdminControllerTest Class : 관리자 페이지 기능과 관련된 logic test 를 담당하는 Class 이다.  

### com.marketplace.main package
헌책 장터 시스템의 시작 관련 Class 들을 모아놓은 package 이다.  
MarketPlace Class : main 함수를 통해 헌책 장터 시스템의 시작을 담당하는 Class 이다.  