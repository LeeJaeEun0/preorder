# 예약 구매하기 프로젝트

## 1. 예약 구매

- 사용자간 커뮤니티를 할 수 있는 소셜미디어 서비스를 제공
    - 게시글 작성, 댓글 작성, 좋아요, 팔로우, 뉴스피드 가능
- 상품을 구매할 수 있는 서비스 제공
    - 일반 상품과 정해진 시간에 상품을 구매할 수 있는 예약 상품 구매 가능

## 2. 프로젝트 요구사항

- 소셜미디어 서비스
  - jwt 토큰을 사용해 로그인, 로그아웃
  - 구글 smtp를 사용해 사용자 인증
  - 프로필 이미지를 업로드, 수정
  - 게시글, 댓글, 좋아요, 팔로우 기능
  - 뉴스피드 기능
    - 친구의 소식을 알 수 있음(팔로잉, 팔로워의 소식)
    - 내가 작성한 글의 좋아요, 댓글 알림
    
  <br> 
- 예약 구매 서비스
  - 일반 상품과 예약 구매 상품을 구분
    - 예약 상품은 정해진 시간에 판매하는 상품을 지칭
  - 상품을 구매하는 단계
    - 상품에서 주문하기 버튼 - 주문 - 결제하기 
    - 주문과 결제 단계에서 구매자의 단순 변심으로 전체의 20%가 취소한다고 가정
  - redis를 사용한 실시간 재고 관리
    - 예약 상품의 판매 시간이 되면 먆은 트래픽이 발생한다고 가정

## 3. ERD 및 프로젝트 아키텍처
### 소셜미디어 서비스 ERD
![erd1](/images/erd1.png)

### 예약 구매 서비스 ERD
![erd2](/images/erd2.png)

### 소셜미디어 서비스 아키텍처
![erd1](/images/architecture1.png)

### 예약 구매 서비스 아키텍처
![erd1](/images/architecture2.png)

### 모듈
|     서비스 이름      |  포트  |    역할   |
|:---------------:|:----:|:-------:|
|   UserService   | 8080 | 유저 정보 저장 |
| ActivityService | 8081 | 유저 활동 저장 |
| NewsfeedService | 8082 | 뉴스피드 저장 |
| GatewayService  | 8083 |  게이트웨이  |
| ProductService  | 8084 | 상품 정보 저장 |
|  OrderService   | 8085 | 주문 정보 저장 |
| PaymentService  | 8086 | 결제 정보 저장 |
|  StockService   | 8087 | 재고 정보 저장 |

## 4. 프로젝트 사용 기술

![Java Badge](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot Badge](https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL Badge](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis Badge](https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker Badge](https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Badge](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)
![Git Badge](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white)

java: 17<br>
mysql: 8.0


## 5. 도커파일 실행 방법

```bash
docker-compose up -d
```
docker compose 환경에서 mysql, redis 사용

## 6. API 설계
#### 회원가입
```http request
Post /users/register
```
Request Body
```http request
{
  "username": "user123",
  "email": "user@example.com",
  "password": "securePassword123"
}
```
Response Body
```http request
{
  "username": "user123",
  "email": "user@example.com",
}
```
[API주소 전체 보기](https://leejaeeun-portfolio.notion.site/API-238a416466824d669335d932cbfbb012?pvs=4)
