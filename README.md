# 연수 신청 시스템 (Training Application System)

Spring Boot + MyBatis + MySQL 기반의 연수 신청 관리 시스템입니다.

## 📋 요구사항

- Java 17 이상
- MySQL 8.0 이상
- Gradle 7.0 이상

## 🚀 시작하기

### 1. MySQL 설정

#### MySQL 설치 및 데이터베이스 생성
```sql
-- MySQL에 로그인 후 데이터베이스 생성
CREATE DATABASE appl_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 사용자 생성 (선택사항)
CREATE USER 'appl_user'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON appl_db.* TO 'appl_user'@'localhost';
FLUSH PRIVILEGES;
```

#### 테이블 생성
프로젝트의 `src/main/resources/schema.sql` 파일을 실행하여 테이블을 생성합니다.

```bash
# MySQL 명령행에서 실행
mysql -u root -p appl_db < src/main/resources/schema.sql
```

### 2. 환경변수 설정 (보안)

데이터베이스 연결 정보는 환경변수로 관리됩니다. 다음 방법 중 하나를 선택하세요:

#### 방법 1: 터미널에서 환경변수 설정
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_mysql_password
export DB_URL=jdbc:mysql://localhost:3306/appl_db?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
```

#### 방법 2: IntelliJ IDEA에서 환경변수 설정
1. Run/Debug Configurations 열기
2. Environment variables에 다음 추가:
   - `DB_USERNAME=root`
   - `DB_PASSWORD=your_mysql_password`
   - `DB_URL=jdbc:mysql://localhost:3306/appl_db?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8`

#### 방법 3: 로컬 설정 파일 생성 (개발용)
`src/main/resources/application-local.properties` 파일을 생성하고 다음 내용 추가:
```properties
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
DB_URL=jdbc:mysql://localhost:3306/appl_db?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
```
⚠️ **주의**: 이 파일은 Git에 업로드되지 않도록 .gitignore에 추가되어 있습니다.

### 3. 프로젝트 실행

```bash
# 프로젝트 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun
```

또는 IDE에서 `ApplApplication.java` 파일을 직접 실행하세요.

### 4. 접속

브라우저에서 `http://localhost:8080`으로 접속합니다.

#### 주요 페이지
- 연수 신청: `http://localhost:8080/trainings/apply`
- 신청 목록: `http://localhost:8080/trainings/list`

## 🏗️ 프로젝트 구조

```
src/
├── main/
│   ├── java/junesoft/appl/
│   │   ├── controller/     # 컨트롤러
│   │   ├── service/        # 서비스 레이어
│   │   ├── mapper/         # MyBatis 매퍼 인터페이스
│   │   ├── dto/           # 데이터 전송 객체
│   │   ├── config/        # 설정 클래스
│   │   └── constant/      # 상수 정의
│   ├── resources/
│   │   ├── mapper/        # MyBatis XML 매퍼
│   │   ├── mybatis/       # MyBatis 설정
│   │   ├── static/        # 정적 리소스 (CSS, JS)
│   │   └── templates/     # (사용 안 함)
│   └── webapp/
│       └── WEB-INF/views/ # JSP 뷰 파일
└── test/                  # 테스트 코드
```

## 📊 데이터베이스 스키마

### 주요 테이블
- `TB25_991_CODE`: 공통 코드 테이블
- `temp_training_data`: 연수 신청 데이터 테이블

### 코드 유형
- `EDU_ORG_CODE`: 교육기관 구분
- `SCHOOL_SYSTEM_CODE`: 학교급
- `DOCTOR_SCHOOL_CODE`: 교육청
- `SCHOOL_FOUND_CODE`: 설립 유형
- `GENDER_CODE`: 성별
- `APPLY_STATUS_CODE`: 신청 상태

## 🔧 개발 정보

- **프레임워크**: Spring Boot 3.4.4
- **뷰 엔진**: JSP + JSTL
- **ORM**: MyBatis 3.0.4
- **데이터베이스**: MySQL 8.0
- **빌드 도구**: Gradle
- **Java 버전**: 17

## 📝 주요 기능

1. **연수 신청 관리**
   - 연수 신청서 작성
   - 신청 목록 조회 (페이징)
   - 신청서 상세 조회

2. **코드 관리**
   - 공통 코드 조회
   - 드롭다운 목록 제공

3. **반응형 UI**
   - 모바일/태블릿/데스크톱 대응
   - 현대적인 CSS 디자인

## 🐛 문제 해결

### 데이터베이스 연결 오류
1. MySQL 서버가 실행 중인지 확인
2. 데이터베이스 이름, 사용자명, 비밀번호 확인
3. 방화벽 설정 확인

### 한글 깨짐 현상
1. MySQL 데이터베이스가 `utf8mb4` 문자셋으로 생성되었는지 확인
2. 연결 URL에 `characterEncoding=UTF-8` 옵션이 있는지 확인

### 포트 충돌
`application.properties`에서 `server.port`를 다른 포트로 변경하세요.

## 📞 문의

프로젝트 관련 문의사항이 있으시면 이슈를 등록해주세요. 