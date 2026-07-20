# 일정 관리 앱

## API 명세서

### 일정 (Schedule)

| 기능 | Method | URL | Request | Response | 상태코드 |
|---|---|---|---|---|---|
| 일정 생성 | POST | /schedules | 요청 body | 등록 정보 | 201 Created |
| 전체 조회 | GET | /schedules | - | 목록 | 200 OK |
| 단건 조회 | GET | /schedules/{id} | - | 단건 정보 | 200 OK |
| 일정 수정 | PATCH | /schedules/{id} | 요청 body | 수정 정보 | 200 OK |
| 일정 삭제 | DELETE | /schedules/{id} | - | - | 204 No Content |


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



## ERD# schedule
