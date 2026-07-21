# 일정 관리 앱

## API 명세서

### 일정 (Schedule)

| 기능     | Method | URL | Request | Response | 상태코드 |
|--------|---|---|---|---|---|
| 일정 생성  | POST | /schedules | 요청 body | 등록 정보 | 201 Created |
| 페이징 조회 | GET | /schedules | - | 목록 | 200 OK |
| 단건 조회  | GET | /schedules/{id} | - | 단건 정보 | 200 OK |
| 일정 수정  | PATCH | /schedules/{id} | 요청 body | 수정 정보 | 200 OK |
| 일정 삭제  | DELETE | /schedules/{id} | - | - | 204 No Content |


### 유저 (User)

| 기능 | Method | URL | Request | Response | 상태코드 |
|---|---|---|---|---|---|
| 회원가입 | POST | /users | 요청 body | 등록 정보 | 201 Created |
| 전체 조회 | GET | /users | - | 목록 | 200 OK |
| 단건 조회 | GET | /users/{id} | - | 단건 정보 | 200 OK |
| 유저 수정 | PATCH | /users/{id} | 요청 body | 수정 정보 | 200 OK |
| 유저 삭제 | DELETE | /users/{id} | - | - | 204 No Content |


### 댓글 (Comment)

| 기능 | Method | URL | Request | Response | 상태코드 |
|---|---|---|---|---|---|
| 댓글 생성 | POST | /schedules/{scheduleId}/comments | 요청 body | 등록 정보 | 201 Created |
| 전체 조회 | GET | /schedules/{scheduleId}/comments | - | 목록 | 200 OK |



## ERD

### 테이블 관계

- `users` 1 : N `schedules` — 유저 한 명은 여러 일정을 작성할 수 있다
- `users` 1 : N `comments` — 유저 한 명은 여러 댓글을 작성할 수 있다
- `schedules` 1 : N `comments` — 일정 하나에 여러 댓글이 달릴 수 있다
- JPA 연관관계는 모두 **단방향** (`Schedule → User`, `Comment → User`, `Comment → Schedule` 방향의 `@ManyToOne`)

### users (유저)

| 컬럼 | 타입 | 제약조건 | 설명 |
|---|---|---|---|
| id | BIGINT | PK, AUTO_INCREMENT | 유저 고유 식별자 |
| username | VARCHAR(4) | NOT NULL | 유저명 (4글자 이내) |
| email | VARCHAR(255) | NOT NULL, UNIQUE | 이메일 |
| password | VARCHAR(255) | NOT NULL | 비밀번호 (8글자 이상) |
| created_at | DATETIME | NOT NULL | 작성일 (JPA Auditing) |
| modified_at | DATETIME | NOT NULL | 수정일 (JPA Auditing) |

### schedules (일정)

| 컬럼 | 타입 | 제약조건 | 설명 |
|---|---|---|---|
| id | BIGINT | PK, AUTO_INCREMENT | 일정 고유 식별자 |
| title | VARCHAR(10) | NOT NULL | 할일 제목 (10글자 이내) |
| content | VARCHAR(255) | NOT NULL | 할일 내용 |
| user_id | BIGINT | FK → users.id, NOT NULL | 작성 유저 |
| created_at | DATETIME | NOT NULL | 작성일 (JPA Auditing) |
| modified_at | DATETIME | NOT NULL | 수정일 (JPA Auditing) |

### comments (댓글)

| 컬럼 | 타입 | 제약조건 | 설명 |
|---|---|---|---|
| id | BIGINT | PK, AUTO_INCREMENT | 댓글 고유 식별자 |
| content | VARCHAR(255) | NOT NULL | 댓글 내용 |
| user_id | BIGINT | FK → users.id, NOT NULL | 작성 유저 |
| schedule_id | BIGINT | FK → schedules.id, NOT NULL | 대상 일정 |
| created_at | DATETIME | NOT NULL | 작성일 (JPA Auditing) |
| modified_at | DATETIME | NOT NULL | 수정일 (JPA Auditing) |


## FetchType LAZY vs EAGER
### LAZY
Select문 2번

![LAZY 실행 결과](./resources/images/lazy.png)

### EAGER
Select문 1번 

![EAGER 실행 결과](./resources/images/eager.png)
