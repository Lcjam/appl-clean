-- 테스트 데이터 초기화
DELETE FROM training_appl;

-- 테스트용 연수 신청 데이터 추가
INSERT INTO training_appl (
    training_seq,
    applicant_name,
    school_system_code,
    edu_office_code,
    found_type_code,
    gender_code,
    birth_date,
    org_type_code,
    created_at
) VALUES 
(1, '홍길동', 'E', '7530000', 'N', 'M', '19900101', '1', NOW()),
(2, '김철수', 'M', '7530000', 'P', 'M', '19910202', '2', NOW()),
(3, '이영희', 'H', '7530000', 'N', 'F', '19920303', '3', NOW());

-- 테스트용 코드 데이터 초기화
DELETE FROM common_code;

-- 테스트용 코드 데이터 추가
INSERT INTO common_code (
    code_type,
    code,
    code_name,
    sort_order,
    use_yn
) VALUES 
('SCHOOL_SYSTEM_CODE', 'E', '초등학교', 1, 'Y'),
('SCHOOL_SYSTEM_CODE', 'M', '중학교', 2, 'Y'),
('SCHOOL_SYSTEM_CODE', 'H', '고등학교', 3, 'Y'),
('EDU_OFFICE_CODE', '7530000', '서울특별시교육청', 1, 'Y'),
('SCHOOL_FOUND_CODE', 'N', '국립', 1, 'Y'),
('SCHOOL_FOUND_CODE', 'P', '사립', 2, 'Y'),
('GENDER_CODE', 'M', '남', 1, 'Y'),
('GENDER_CODE', 'F', '여', 2, 'Y'); 