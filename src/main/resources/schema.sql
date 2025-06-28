-- MySQL용 테이블 생성 스크립트

-- 데이터베이스 생성 (필요시)
CREATE DATABASE IF NOT EXISTS appl_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE appl_db;

-- 코드 테이블 생성
CREATE TABLE IF NOT EXISTS TB25_991_CODE (
    code_seq INT AUTO_INCREMENT PRIMARY KEY,
    code_type VARCHAR(50) NOT NULL COMMENT '코드 유형',
    code VARCHAR(20) NOT NULL COMMENT '코드',
    code_name VARCHAR(100) NOT NULL COMMENT '코드명',
    code_desc TEXT COMMENT '코드 설명',
    sort_order INT DEFAULT 0 COMMENT '정렬 순서',
    use_flag CHAR(1) DEFAULT 'Y' COMMENT '사용 여부',
    write_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    del_flag CHAR(1) DEFAULT 'N' COMMENT '삭제 여부',
    INDEX idx_code_type (code_type),
    INDEX idx_code_type_code (code_type, code),
    UNIQUE KEY uk_code_type_code (code_type, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='공통 코드 테이블';

-- 연수 신청 테이블 생성
CREATE TABLE IF NOT EXISTS temp_training_data (
    training_seq INT AUTO_INCREMENT PRIMARY KEY,
    ipms_training_seq INT COMMENT 'IPMS 연수 순번',
    ipms_training_code VARCHAR(50) COMMENT 'IPMS 연수 코드',
    ipms_training_session VARCHAR(50) COMMENT 'IPMS 연수 세션',
    kipa_id VARCHAR(50) COMMENT 'KIPA ID',
    user_name VARCHAR(100) NOT NULL COMMENT '사용자명',
    org_type_code VARCHAR(20) COMMENT '기관 유형 코드',
    org_type_etc_txt VARCHAR(200) COMMENT '기관 유형 기타 텍스트',
    course_code VARCHAR(50) COMMENT '과정 코드',
    school_code VARCHAR(50) COMMENT '학교 코드',
    school_system_code VARCHAR(20) COMMENT '학교 시스템 코드',
    edu_office_cd VARCHAR(20) COMMENT '교육청 코드',
    local_edu_office_name VARCHAR(100) COMMENT '지역 교육청명',
    found_type_code VARCHAR(20) COMMENT '설립 유형 코드',
    dispatch_flag CHAR(1) DEFAULT 'N' COMMENT '파견 여부',
    dispatch_org_name VARCHAR(200) COMMENT '파견 기관명',
    position VARCHAR(100) COMMENT '직위',
    gender_code VARCHAR(10) COMMENT '성별 코드',
    birth_date VARCHAR(8) COMMENT '생년월일',
    nice_pin VARCHAR(50) COMMENT 'NICE PIN',
    user_mobile VARCHAR(20) COMMENT '사용자 휴대폰',
    user_email VARCHAR(100) COMMENT '사용자 이메일',
    apply_status_code VARCHAR(20) COMMENT '신청 상태 코드',
    apply_time TIMESTAMP COMMENT '신청 시간',
    first_exam_code VARCHAR(20) COMMENT '1차 시험 코드',
    first_exam_fail_desc TEXT COMMENT '1차 시험 실패 설명',
    training_desc TEXT COMMENT '연수 설명',
    finish_desc VARCHAR(200) COMMENT '완료 설명',
    finish_prefix_desc VARCHAR(200) COMMENT '완료 접두 설명',
    finish_seq INT COMMENT '완료 순번',
    finish_done_date DATE COMMENT '완료일',
    zipcode VARCHAR(10) COMMENT '우편번호',
    addr1 VARCHAR(200) COMMENT '주소1',
    addr2 VARCHAR(200) COMMENT '주소2',
    write_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    del_flag CHAR(1) DEFAULT 'N' COMMENT '삭제 여부',
    INDEX idx_user_name (user_name),
    INDEX idx_apply_time (apply_time),
    INDEX idx_del_flag (del_flag)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='연수 신청 데이터 테이블';

-- 기본 코드 데이터 삽입
INSERT INTO TB25_991_CODE (code_type, code, code_name, sort_order, use_flag) VALUES
-- 기관 유형
('EDU_ORG_CODE', 'D001', '발명교육센터', 1, 'Y'),
('EDU_ORG_CODE', 'D002', '발명영재학급, 발명영재교육원', 2, 'Y'),
('EDU_ORG_CODE', 'D003', '발명동아리', 3, 'Y'),
('EDU_ORG_CODE', 'D099', '기타', 99, 'Y'),

-- 학교급
('SCHOOL_SYSTEM_CODE', 'E', '초등학교', 1, 'Y'),
('SCHOOL_SYSTEM_CODE', 'M', '중학교', 2, 'Y'),
('SCHOOL_SYSTEM_CODE', 'H', '고등학교', 3, 'Y'),

-- 교육청
('DOCTOR_SCHOOL_CODE', '7530000', '서울특별시교육청', 1, 'Y'),
('DOCTOR_SCHOOL_CODE', '7531000', '부산광역시교육청', 2, 'Y'),
('DOCTOR_SCHOOL_CODE', '7532000', '대구광역시교육청', 3, 'Y'),

-- 설립 유형
('SCHOOL_FOUND_CODE', 'N', '국립', 1, 'Y'),
('SCHOOL_FOUND_CODE', 'P', '사립', 2, 'Y'),
('SCHOOL_FOUND_CODE', 'U', '공립', 3, 'Y'),

-- 성별
('GENDER_CODE', 'M', '남', 1, 'Y'),
('GENDER_CODE', 'F', '여', 2, 'Y'),

-- 신청 상태
('APPLY_STATUS_CODE', 'A01', '신청', 1, 'Y'),
('APPLY_STATUS_CODE', 'A02', '승인', 2, 'Y'),
('APPLY_STATUS_CODE', 'A03', '반려', 3, 'Y')
ON DUPLICATE KEY UPDATE 
    code_name = VALUES(code_name),
    sort_order = VALUES(sort_order),
    use_flag = VALUES(use_flag),
    modify_time = CURRENT_TIMESTAMP; 