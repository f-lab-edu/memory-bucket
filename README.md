# memory-bucket

추억을 타임 라인 형태로 저장할 수 있는 공유 앨범 서비스입니다.

# 앨범

## 엔티티

### 필드

- 앨범 아이디
- 앨범 소유자
- 앨범 제목
- 앨범 설명 (선택)
- 앨범 커버 사진 URL (선택)
- 앨범 구독자 컬렉션
- 앨범 생성 일자
- 앨범 수정 일자

## DTO

### 앨범 응답

- 앨범 아이디
- 앨범 소유자
- 앨범 제목
- 앨범 설명
- 앨범 커버 사진
- 앨범 생성 일자
- 앨범 구독자 컬렉션
    - 사용자 프로필 사진
    - 사용자 닉네임
    - 사용자 초대용 아이디
    - 사용자 권한
    - 사용자 앨범 참여일
- 앨범 메모리 개수

### 앨범 생성 요청

- 앨범 제목
- 앨범 간단 설명 (선택)
- 앨범 커버 사진 (선택)

### 앨범 수정 요청

- 앨범 제목
- 앨범 간단 설명 (선택)
- 앨범 커버 사진 (선택)

## 비즈니스 로직

### 앨범 생성

- 현재 로그인한 회원의 아이디를 소유자로 하는 앨범을 생성한다.
- `앨범 생성 요청`에 앨범 커버 사진이 있으면 이미지를 저장하고 URL을 반환받는다.
- 커버 사진이나 앨범 간단 설명이 없으면 빈값으로 한다.
- 커버 사진 URL과 앨범 정보를 저장한다.
- 저장된 정보를 `앨범 응답`으로 만들어 반환한다.

### 앨범 단건 조회

- 앨범 ID를 사용하여 앨범 정보를 조회한다.
- 조회할 수 있는 앨범은 현재 로그인한 사용자가 소유한 앨범이나 구독 중인 앨범이다.
- 조회한 앨범 정보를 `앨범 응답`으로 만들어 반환한다.

### 앨범 목록 조회

- 현재 로그인한 사용자가 소유한 앨범이나 구독 중인 앨범 목록을 조회한다.
- 앨범 목록은 정렬 방법 `사용자가 앨범을 구독한 일자`, `앨범 이름` 으로 정렬하여 조회할 수 있다.
- 조회한 앨범 목록을 `앨범 응답` 목록으로 만들어 반환한다.

### 앨범 수정

- 현재 로그인한 사용자의 아이디와 앨범 소유자의 아이디가 같은지 확인한다.
- 아이디가 같다면 `앨범 수정 요청`을 보고 소유한 앨범 정보를 수정한다.
- 수정된 결과를 `앨범 응답` 으로 만들어 반환한다.

### 앨범 삭제

- 앨범 ID를 사용하여 현재 로그인한 사용자의 아이디와 앨범 소유자의 아이디가 같은지 확인한다.
- 아이디가 같다면 앨범을 삭제한다.
- 앨범에 속한 기억을 삭제한다.

### 앨범에 사용자 초대

- 구독자 목록에 추가할 사용자 아이디를 받는다.
- 사용자를 구독자 목록에 초대 상태로 저장한다.

### 앨범 구독자 제거

- 구독자 ID를 받는다.
- 해당 ID를 구독자 목록에서 제거한다.

# 메모리

## 엔티티

### 필드

- 메모리 아이디
- 메모리 제목
- 메모리 작성자
- 메모리 썸네일
- 메모리 키워드 컬렉션
- 컨텐츠 컬렉션
    - 이미지 or 영상
    - 메모 제목
    - 메모 내용
- 위치
- 메모리 시작일
- 메모리 종료일
- 메모리 생성 일자
- 메모리 수정 일자

## DTO

### 메모리 생성 요청

- 메모리 제목
- 메모리 썸네일
- 메모리 키워드 컬렉션
- 컨텐츠 컬렉션
    - 이미지 or 영상
    - 메모 제목
    - 메모 내용
- 위치
- 메모리 시작일
- 메모리 종료일

### 메모리 수정 요청

- 메모리 제목
- 메모리 썸네일
- 메모리 키워드 컬렉션
- 컨텐츠 컬렉션
    - 메모 제목
    - 메모 내용
- 위치
- 메모리 시작일
- 메모리 종료일

### 메모리 응답

- 메모리 아이디
- 메모리 제목
- 메모리 작성자
- 메모리 썸네일
- 메모리 키워드 컬렉션
- 컨텐츠 컬렉션
    - 이미지 or 영상
    - 메모 제목
    - 메모 내용
- 위치
- 메모리 시작일
- 메모리 종료일
- 메모리 생성 일자
- 메모리 수정 일자

## 비즈니스 로직

### 메모리 생성

- `메모리 생성 요청`을 받아서 현재 로그인한 회원의 아이디를 소유자로 하는 메모리를 생성한다.
- 컨텐츠 컬렉션에 있는 이미지나 영상을 s3에 저장하고 url을 반환받는다.
- 메모리 썸네일이 없으면 컨텐츠 컬렉션의 첫번째 이미지의 url을 저장한다.
- 메모리를 저장한다.
- 저장된 결과를 `메모리 응답`으로 만들어서 반환한다.

### 메모리 단건 조회

- 메모리 ID를 사용하여 메모리를 조회한다.
- 조회한 메모리를 `메모리 응답`으로 만들어서 반환한다.

### 메모리 목록 조회

- 현재 조회중인 앨범에 속해 있는 메모리의 목록을 조회한다.
- 메모리는 `제목`, `작성자`, `키워드`, `위치`, `시작일`, `종료일`로 검색할 수 있다.
- 메모리는 1페이지에 10개씩 보여준다.
- 메모리는 `생성일`, `편집일`, `시작일`로 정렬할 수 있다.
- 조회한 목록을 `메모리 응답` 으로 만들어서 반환한다.

### 메모리 수정

- 현재 로그인한 사용자의 아이디와 메모리 작성자의 아이디가 같은지 확인한다.
- 아이디가 같다면 `메모리 수정 요청`을 보고 소유한 메모리 정보를 수정한다.
- 수정된 결과를 `메모리 응답` 으로 만들어 반환한다.

### 메모리 삭제

- 메모리 ID와 사용자 ID를 사용하여 메모리를 삭제한다.
- 앨범 사용자는 앨범 내의 모든 메모리를 삭제 할 수 있다. 메모리 작성자는 작성한 메모리만 삭제할 수 있다.
- 현재 로그인한 사용자가 앨범 소유자이거나 메모리 작성자인지 확인한다.
- 조건을 만족하면 메모리를 삭제한다.
- 메모리에 속한 컨텐츠를 삭제한다.



