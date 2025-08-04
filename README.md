# programmers

## 프로젝트 개요
 - 프로젝트 목적: JPA를 통해 데이터 베이스를 연동한 게시판 글의 생성, 조회, 수정, 삭제(CRUD) 구현
 - 주요 학습 목표: MVC(Model-View-Controller) 구조 각각의 책임에 대해 집중
 
### 기술 스택
 - Language: Java 17
 - Framework: Spring Boot
 - IDE: IntelliJ IDEA
 - JDK : corretto-17
 - Dependencies : Spring Web, Spring Data JPA, MySQL Driver, Lombok
 
## 주요 기능
 - 일정 등록
 - 일정 조회 (전체 조회 가능, 작성자 명을 기준 조회 가능, 일정 Id를 기준 조회 가능)
 - 일정 수정 (일정 등록 시 입력한 비밀번호가 맞지 않다면 수정 불가)
 - 일정 삭제 (일정 등록 시 입력한 비밀번호가 맞지 않다면 수정 불가)
 - 댓글 등록
 
### 패키지 구조
<pre>
src/main/java/org.example.memo
├── controller
  ├── CommentController
  ├── MemoController
├── dto
  ├── CommentRequestDto
  ├── CommentResponseDto
  ├── MemoRequestDto
  ├── MemoResponseDto
├── entity
  ├── BaseEntity
  ├── Comment 
  ├── Memo
├── repository
  ├── CommentService  
  ├── MemoService  
├── service
  ├── CommentService 
  ├── MemoService  
├── MemoApplication
</pre>
 
## 트러블 슈팅
 - [프로젝트 TIL](https://dinga87.tistory.com/category/SPARTA%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)
 
#### 미구현 목표
- 댓글 갯수 제한
- 예외처리를 통한 데이터 무결성 보장
  
## API and ERD
 - [API and ERD 링크](https://dinga87.tistory.com/111)
