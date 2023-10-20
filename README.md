# ⏰ buy-now

### 1. 프로젝트 개요

특정 상품을 한정된 시간에만 할인된 가격으로 판매하는 `타임딜` 프로젝트

### 2. 프로젝트 구성

<img alt="image" src="https://github.com/f-lab-edu/buy-now/assets/121920173/06152564-b89c-40dd-b941-94d4babbf12b">

1. Application 서버와 DB 서버는 별도의 서버로 구성
2. git `push`를 실행하였을 때 `CI`가 실행, `master branch`에 `push`될 때 Docker를 이용하여 자동 배포
3. `nGrinder`를 통해서는 한 제품에 대해 주문이 몰렸을 때의 재고 문제에 대해 테스트

### 3. 프로젝트 목표

1.  서버 세팅부터 배포(CI/CD)까지 구축하기
2.  동시성 문제를 해결할 수 있는 해결 방법을 알아보기
3. myBatis를 JPA로 마이그레이션 하기

### 4. 주요기능

1. 물품의 가격은 정가와 할인가가 있으며 주문 시, 할인 기간내라면 할인가로 주문한다.
2. 여러 명의 사용자에게 주문 요청이 들어와도 물품의 재고에는 오차가 없어야 한다.

### 5. DB 설계
![image](https://github.com/f-lab-edu/buy-now/assets/121920173/99368867-5a48-49e5-a0c8-0f1f45aa1279)

### 6. 화면 구성

#### 6.1 일반 회원 (구매자)

![image](https://github.com/f-lab-edu/buy-now/assets/121920173/bb0c1f11-a062-49a2-b96d-a0ebb1f20ae3)

#### 6.2 관리자 (판매자)

![image](https://github.com/f-lab-edu/buy-now/assets/121920173/11c62a82-2ce1-4c73-bdf7-5ce75e5dedcf)

### 7. 기술적 이슈와 해결 방안

1. 페이징 처리
	: 초기 페이지에서는 하단에 페이지 번호를 두고 이동하는 방식의 offset 방식을 사용하였다. 하지만, 요즘 대부분의 웹페이지에서는 페이지 번호보다는 스크롤을 내리면서 목록을 조회하는 방식을 많이 사용하고 있다. 그리고 offset 방식의 성능 issue가 있기 때문에 no-offset 방식을 사용하여 페이징 처리를 하기로 하였다.  
	페이징 처리를 적용한 방식과 offset/no-offset 방식의 성능 차이는 아래의 블로그 링크를 확인해보자.  
	<a href="https://j-jeongeun.github.io/posts/Paging">(페이징을 처리하는 2가지 방법)</a>
3. 동시성 처리
	: 여러 사용자에게 동시에 주문이 들어올 경우, 상품의 재고가 맞지 않는 경우가 발생하였다. 예를 들어, 100명의 사용자가 동시에 1개씩 100건을 요청하였는데 물품의 최종 재고는 0개가 아닌 n개의 결과가 나왔다. 그래서 해당 문제를 해결할 수 있는 방법에 대하여 고민 해본 결과 `비관적 락`을 이용하기로 하였다. 실제 상품의 재고를 조회해오는 `SELECT`절에 `FOR UPDATE`를 추가하여 해당 상품의 조회에 `Lock`을 걸어 `주문`이라는 하나의 트랜잭션이 완료될 때까지 다른 요청은 대기를 시키는 방식으로 해결하였다.  
	`비관적 락`을 이용한 방법 이외의 다른 방법들과 테스트 결과는 아래의 블로그 링크를 확인해보자.  
	<a href="https://j-jeongeun.github.io/posts/Concurrency">(동시성 처리는 어떻게 하는게 좋을까?)</a>

### 8. 그 외 프로젝트 관련 블로그 글
<a href="https://j-jeongeun.github.io/posts/login(cookie-and-session)">로그인 기능 구현(1) - Cookie & Session</a><br>
<a href="https://j-jeongeun.github.io/posts/login(JWT)">로그인 기능 구현(2) - JWT(Json Web Token)</a>
