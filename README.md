# ⏰ buy-now

### 1. 프로젝트 개요
특정 상품을 한정된 시간에만 할인된 가격으로 판매하는 `타임딜` 프로젝트

---
### 2. Skills
<img alt="image" src="https://github.com/f-lab-edu/buy-now/assets/121920173/49141596-e5f3-4459-8ef4-9e988a09a737">

---
### 3. 기능 정의
1. 회원
    1. 회원 로그인, 로그아웃
    2. 회원가입 / 수정
    3. 판매자 계정(관리자)의 경우 정해진 계정이 별도로 존재

2. 상품
    1. 상품의 타임딜 기간은 날짜+시간으로 정의한다.
    2. 상품의 할인금액에 따라, 상품가격 = (원래의 상품가격 - 할인금액) 으로 책정된다.
    3. 타임딜 기간 동안은 상품의 모든 정보는 수정이 가능하다.

3. 주문
    1. 현재 시간/날짜가 상품의 타임딜 시작~종료 기간 내일 경우, 주문 가능하며 그 이외의 기간에는 주문할 수 없다.
    2. 타임딜 기간이 종료되기 이전에 모든 재고가 소진되면 남은 타임딜 기간 동안은 해당 상품을 주문할 수 없다. 하지만, 상품 수정으로 재고가 수정될 경우(1개 이상), 주문 가능하다.
    3. 현재 남은 재고가 2개이며 구매자가 그 이상의 재고를 구매 시(2개 초과), 구매할 수 없다.
    4. 상품 가격에 따른 구매자의 잔액 체크는 하지 않는다.
    5. 타임딜 기간동안 1명의 구매자에 대한 최대 구매 수량, 구매 횟수는 상관없다.
    6. 주문은 취소할 수도 있다. 취소된 주문 수량만큼 재고가 +된다.
---
### 4. DB 설계
![image](https://github.com/f-lab-edu/buy-now/assets/121920173/f4fd97d1-70c6-44a6-aa6e-464409559b4e)
---
### 5. 화면구성
<details>
<summary>1. 일반회원(구매자)</summary>
<div markdown="1">
    ![image](https://github.com/f-lab-edu/buy-now/assets/121920173/bb0c1f11-a062-49a2-b96d-a0ebb1f20ae3)
</div>
</details>

<details>
<summary>2. 관리자(판매자)</summary>
<div markdown="1">
    ![image](https://github.com/f-lab-edu/buy-now/assets/121920173/11c62a82-2ce1-4c73-bdf7-5ce75e5dedcf)
</div>
</details>

---
### 6. 후기
서버세팅과 CI/CD 구축, 부하테스트까지 프로젝트의 처음과 끝을 모두 경험해본 나의 첫번째 프로젝트이다.  
`타임딜` 프로젝트에 맞게 동시성 처리에 대해 생각해보고, 현재 나의 프로젝트에 가장 적합한 동시성 처리 방법에 대해 알아보기도 하였다.  
[나는 어떤 동시성 처리 방법을 선택했는가?](https://j-jeongeun.github.io/posts/Concurrency)

`어떤` 기술/기능을 도입할 때, `왜` 그 방법을 선택했고 비슷한 다른 방법에는 어떤게 있는지에 대해 알아보고 비교하는게 중요하다는 것도 알게 되었다.

---
### 7. 프로젝트 관련 블로그 글
<a href="https://j-jeongeun.github.io/posts/login(cookie-and-session)">로그인 기능 구현(1) - Cookie & Session</a><br>
<a href="https://j-jeongeun.github.io/posts/login(JWT)">로그인 기능 구현(2) - JWT(Json Web Token)</a><br>
<a href="https://j-jeongeun.github.io/posts/Paging">페이징을 처리하는 2가지 방법</a><br>
<a href="https://j-jeongeun.github.io/posts/Concurrency">동시성 처리는 어떻게 하는게 좋을까?</a>
