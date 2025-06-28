package junesoft.appl.constant;

/**
 * 코드 타입 상수 정의 클래스
 */
public class CodeConstants {
    
    // 교육기관 관련 코드
    public static final String ORG_TYPE_CODE = "EDU_ORG_CODE";
    public static final String SCHOOL_SYSTEM_CODE = "SCHOOL_SYSTEM_CODE";
    
    // 교육과정 코드
    public static final String COURSE_CODE = "INSTITUTE_COURSE_CODE";
    
    // 시도교육청 코드
    public static final String EDU_OFFICE_CD = "DOCTOR_SCHOOL_CODE";
    
    // 학교 설립 구분 코드
    public static final String FOUND_TYPE_CODE = "SCHOOL_FOUND_CODE";
    
    // 개인 정보 관련 코드
    public static final String GENDER_CODE = "GENDER_CODE";
    
    // 신청 상태 관련 코드
    public static final String APPLY_STATUS_CODE = "APPLY_STATUS_CODE";
    
    // 첫번째 시험 코드
    public static final String FIRST_EXAM_CODE = "FIRST_EXAM_CODE";
    
    // 플래그 상수
    public static final String FLAG_YES = "Y";
    public static final String FLAG_NO = "N";
    
    // 삭제 여부 플래그용 상수
    public static final String DEL_FLAG = "del_flag";
    
    // 생성자를 private으로 선언하여 인스턴스화 방지
    private CodeConstants() {
        throw new IllegalStateException("error");
    }
} 