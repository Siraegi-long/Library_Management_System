# 티니핑 대도서관 (도서 대여 관리 프로그램)

## 프로젝트 개요
티니핑 대도서관은 회원 관리, 도서 대여 및 반납, 연체 관리, 대여 연장 기능을 포함한 도서관 관리 시스템입니다. 이 시스템은 회원을 일반, 연체, 퇴출, 관리자 등으로 분류하고, 도서관 관리자가 도서와 회원을 효율적으로 관리할 수 있도록 지원합니다. 회원가입 시 유효성 검사, 연체 관리, 도서 검색 및 필터링, 통계 기능 등을 통해 도서관 운영을 체계적으로 지원합니다.

## 1. 데이터베이스 설계

### UserTbl (회원 정보 테이블)
| 필드명          | 데이터 타입                | 제약 조건                      |
|-----------------|---------------------------|------------------------------|
| name            | VARCHAR(100)              | NOT NULL                     |
| memberID        | INT                       | PRIMARY KEY AUTO_INCREMENT    |
| memberGrade     | ENUM('일반', '연체', '퇴출', '관리자') | NOT NULL                     |
| ID              | VARCHAR(50)               | UNIQUE NOT NULL              |
| PW              | VARCHAR(255)              | NOT NULL                     |
| phone           | VARCHAR(15)               | NOT NULL                     |
| overdueCount    | INT                       | DEFAULT 0                    |
| currentRentalCount | INT                    | DEFAULT 0                    |
| rentalDate      | DATE                      | DEFAULT NULL                 |

### BookTbl (도서 정보 테이블)
| 필드명          | 데이터 타입                | 제약 조건                      |
|-----------------|---------------------------|------------------------------|
| bookID          | INT                       | PRIMARY KEY AUTO_INCREMENT    |
| bookName        | VARCHAR(255)              | NOT NULL                     |
| author          | VARCHAR(100)              | NOT NULL                     |
| publisher       | VARCHAR(100)              | NOT NULL                     |
| category        | VARCHAR(50)               | NOT NULL                     |
| quantity        | INT                       | DEFAULT 1                    |
| rentalDate      | DATE                      | DEFAULT NULL                 |

## 2. 기능 설계

### 2.1. 회원 관리
**Member 클래스**
- `registerMember()`: 회원가입 (전화번호, ID 중복 확인, 비밀번호 강도 검사)
- `updateMemberInfo()`: 회원 정보 수정
- `deleteMember()`: 회원 삭제
- `viewMemberInfo()`: 회원 정보 조회
- `MemberLevel()`: 회원 등급 확인

**회원 등급**
- 일반 회원: 정상적으로 대여 및 반납을 진행하는 회원
- 연체 회원: 대여 기한을 초과한 회원
- 퇴출 회원: 연체가 누적되어 서비스 이용이 제한된 회원
- 관리자: 도서관 시스템을 관리하는 관리자

### 2.2. 도서 관리
**Book 클래스**
- `searchBook()`: 도서 검색 (카테고리, 저자, 출판사별 필터링, 복합 검색 가능)
- `rentBook()`: 도서 대여
- `returnBook()`: 도서 반납
- `extendRentalPeriod()`: 대여 연장 요청 (관리자 승인 필요)
- `checkExtensionStatus()`: 연장 승인 여부 확인

### 2.3. 연체 관리
- `sendOverdueAlert()`: 연체 회원에게 자동 알림 발송
- `convertMemberGrade()`: 연체 횟수가 일정 기준을 넘을 시 회원 등급 자동 변경
- 연체 도서는 대여 연장 불가

### 2.4. 관리자 기능
**Admin 클래스**
- `manageUsers()`: 전체 회원 목록 관리 (대여 현황 및 연체 상태 실시간 조회)
- `manageBooks()`: 전체 도서 목록 관리 (도서 추가 및 삭제 기능)
- `approveExtension()`: 대여 연장 요청 승인 또는 거부

## 3. 추가 기능

### 3.1. 로그인 시스템
- 회원 등급별 접근 권한
  - 일반 회원: 도서 검색, 대여, 반납, 연장 요청
  - 연체 회원: 대여 및 연장 제한, 연체 상태 확인
  - 퇴출 회원: 대여 불가, 도서 검색 및 조회만 가능
  - 관리자: 모든 기능에 접근 가능

### 3.2. 자동 알림 시스템
- 대여 기한 및 연체 알림 자동 발송
- 대여 연장 승인 알림 자동 발송

### 3.3. 통계 기능
- 도서 대여 통계: 도서별 대여 횟수, 인기도 통계 제공
- 회원 대여 통계: 회원별 대여 내역 및 연체 횟수 분석

## 4. 구현 구상도

### Class: Member
registerMember() : 회원가입
updateMemberInfo() : 회원 정보 수정
deleteMember() : 회원 삭제
viewMemberInfo() : 회원 정보 조회
sendOverdueAlert() : 연체 알림 발송



### Class: Book
searchBook() : 도서 검색 (필터 및 복합 검색)
rentBook() : 도서 대여
returnBook() : 도서 반납
extendRentalPeriod() : 대여 연장 요청
checkExtensionStatus() : 연장 승인 상태 확인



### Class: Admin
manageUsers() : 회원 목록 및 상태 관리
manageBooks() : 도서 목록 관리 (추가, 삭제)
approveExtension() : 대여 연장 승인


## 결론
티니핑 대도서관 프로젝트는 도서관 운영을 효율적으로 지원하고, 회원과 도서 관리의 체계적인 시스템을 제공합니다. 이 시스템을 통해 사용자 편의성을 높이고, 도서관 서비스의 품질을 향상시킬 수 있습니다.
