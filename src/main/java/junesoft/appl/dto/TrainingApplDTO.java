package junesoft.appl.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TrainingApplDTO {
    private Long trainingSeq;
    private Long ipmsTrainingSeq;
    private String ipmsTrainingCode;
    private String ipmsTrainingSession;
    private String kipaId;
    private String userName;
    private String orgTypeCode;
    private String orgTypeEtcTxt;
    private String courseCode;
    private String schoolCode;
    private String schoolSystemCode;
    private String eduOfficeCd;
    private String localEduOfficeName;
    private String foundTypeCode;
    private String dispatchFlag;
    private String dispatchOrgName;
    private String position;
    private String genderCode;
    private String birthDate;
    private String nicePin;
    private String mobile;
    private String email;
    private String applyStatusCode;
    private Date applyTime;
    private String firstExamCode;
    private String firstExamFailDesc;
    private String trainingDesc;
    private String finishDesc;
    private String finishPrefixDesc;
    private Integer finishSeq;
    private String finishDoneDate;
    private String zipcode;
    private String addr1;
    private String addr2;
    private Date writeTime;
    private Date modifyTime;
    private String delFlag;
    
    // 추가 필드 - 코드 테이블에서 가져온 이름들
    private String orgTypeName;
    private String schoolSystemName;
    private String eduOfficeName;
    private String foundTypeName;
    private String genderName;
    private String applyStatusName;
    
    // 날짜 포맷팅을 위한 편의 메서드
    public String getFormattedApplyTime() {
        return applyTime != null ? String.format("%tF %tT", applyTime, applyTime) : "";
    }
    
    public String getFormattedBirthDate() {
        if (birthDate == null || birthDate.length() < 8) return "";
        return birthDate.substring(0, 4) + "-" + birthDate.substring(4, 6) + "-" + birthDate.substring(6, 8);
    }
    
    public boolean hasOrgType(String code) {
        return orgTypeCode != null && (orgTypeCode.equals(code) || orgTypeCode.contains(code));
    }
} 