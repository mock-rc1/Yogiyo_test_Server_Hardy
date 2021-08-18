#라이징캠프 1기 요기요 A조 서버 진행 상황

##2021-07-31 진행상황

---
- EC2 서버 구축
- RDS 데이터베이스 구축 및 서버와 연결
- SSL 구축
- ~~로컬에서 API 테스트 성공~~
- 서버에서 테스트 성공 (ssl 적용)
- ERD 설계 시작
  - 내일 목표: ~~prod / prod 폴더 나눠서 서브 도메인 적용~~, (Node js -> Spring Boot 변경)
  - ERD 설계 완성 후 회원가입 API 구현

##2021-08-01 진행상황

---
- 캡쳐한 어플 화면별로 ERD 설계 진행 및 수정 작업 중
- 각 화면에 대한 한방쿼리 구상 및 작성 중
  - 내일 목표: ERD 완성 후 설명 영상 촬영
  - API 3개 완성 목표

##2021-08-02 진행상황

---
- ERD 설계 완료 및 수정 진행
  - 쿠폰/포인트 추후 추가 예정
- 내일 목표: 로그인/회원가입/메인 페이지 API 구현 및 서버 적용
  - 위도/경도 값은 일단 null로 설정하고 e추후에 가능하다면 기능 추가 예
- 내일 목표: 로그인/회원가입/메인 페이지 API 결구현 및 서버 적용
- (가능하면 포털 로그인까지 완성)
- ~~데이터베이스에 정보를 insert 하던 도중 한글 꺠짐 문제 발생~~
  - ~~구글링해서 utf-8 모든 설정에 적용~~ -> 해결
  
##2021-08-03 진행상황

---
- 서버에 따로 mysql를 설치할 필요 없음 -> rds에서 설정한걸 그대로 사용
- erd 일부 값들 null 값 설정 -> 쿼리 불러올 때 비는 값들이 있어서 4000번 에러가 뜸
- 회원가입, 로그인 API 완성 후 명세서 업데이트
- ~~회원가입 약관 동의, 자동-로그인 추가 구현 예정~~
  - 회원가입 약관 동의 validation 추가 적용(명세서 업데이트)
  - 자동 로그인 API 추가 후 서버 적용(명세서 업데이트)
- 내일 목표: 메인화면 API 구현(피드백 전까지) 

##2021-08-04 진행상황

---
- aws s3에 이미지 업로드해서 사용
- 장바구니 화면은 클라이언트에서 구현 가능 
- 페이지 넘어가는 기능은 모두 클라이언트에서 구현

##2021-08-05 진행상황

---
- 메인화면, 전체 카테고리, 카테고리 별 가게 조회 API 구현 및 명세서에 반영
- 이미지는 s3를 사용해서 url을 만들었다
- 클라이언트가 필요한 이미지 다 업로드 할 예정

##2021-08-06 진행상황

---
- s3 사용해서 데이터베이스에 이밎 url 다 채워넣음
- auto-login 기능은 클라이언트에서 구현 가능하다
- 포털 로그인 API 구현 시도 중

##2021-08-07 진행상황

---
- 포털 로그인 API 진행하던 중에 요청이 들어와서 사용자 정보 불러오는 API 먼저 작성 중이다
- 유저 정보 조회 / 유저 닉네임 변경 API 완성, 명세서 업데이트 완료
- 내일 포털 로그인 API 완성 예정
- db에 비어있는 값들 채워넣어야 한다

##2021-08-08 진행상황

---
- 포털 로그인 구현하기 위해 json 파싱과 오브젝트 jar파일 다운로드 및 적용
- 포털 로그인(카카오) api 구현
- 가게 별 정보 조회 api 구현 및 명세서 업데이트 완료
- Menu Category 테이블 추가 생성 필요
- 이메일 로그인 response 파라미터 값으로 비밀번호 문자 개수만큼 별표로 가져왔다

##2021-08-09 진행상황

---
- 음식점 메뉴 페이지 한방쿼리 수정
- 음식점 메뉴 조회, 특정 메뉴 정보 조회 api 구현 및 명세서 업데이트 완료

##2021-08-10 진행상황

---
- 정보 조회 response 값 오류 수정 후 명세서 업데이트 완료
- 명세서 앱 캡쳐 이미지 수정
- s3 이미지 액세스 권한을 풀어줘서 url 접근 가능
- 사용자 정보 조회 API 편의상 POST방식으로 변경
  - 클라이언트 분이 POST방식 이메일/패스워드를 불러오는 로직을 다시 짜야된다고 하셔서 기존의 GET 방식을 수정

##2021-08-11 진행상황

---
- db에 더미 데이터 추가
- ~~**이틀 간 감기몸살때문에 진행을 더 이상 못 했다**~~
- 13일 새벽부터 작업 재개

##2021-08-12 진행상황

---
- 주문하기 api 구현 및 명세서 업데이트 완료
- 주문내역 조회/삭제 API 구현 및 업데이트 완료
- 리뷰 조회/삭제 API 구현 및 업데이트 완료
- ~~13일 오전에 인스턴스 종료를 실수로 눌러 서버 재구축~~
- 한 시간만에 복구
- 정오 12시에 영상 제출

##2021-08-15 진행상황

---
- 이벤트 관련 API 3개 구현 및 명세서 업데이트 완료

##2021-08-16 진행상황

---
- 로그아웃 API 및 공지사항 관련 API 2개 추가 구현

##2021-08-17 진행상황

---
- 모든 API에 validation 추가적용

##2021-08-18 진행상황

---
- 주문하기 API에 body값으로 받는 모든 요소들에 대한 validation 추가 적용
- 명세서 추가 업데이트
