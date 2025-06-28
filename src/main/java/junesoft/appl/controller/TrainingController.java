package junesoft.appl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import junesoft.appl.constant.CodeConstants;
import junesoft.appl.dto.CodeDTO;
import junesoft.appl.dto.TrainingApplDTO;
import junesoft.appl.service.CodeService;
import junesoft.appl.service.TrainingService;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    
    @Autowired
    private CodeService codeService;
    
    // 연수 신청 목록 조회 페이지 (페이징 적용)
    @GetMapping("/list")
    public String listPage(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            Model model) {
        
        // 페이징된 연수 신청 목록 조회
        Map<String, Object> pageResult = trainingService.getTrainingsWithPaging(page, size);
        
        // 모델에 페이징 결과 추가
        model.addAttribute("trainings", pageResult.get("trainings"));
        model.addAttribute("totalCount", pageResult.get("totalCount"));
        model.addAttribute("totalPages", pageResult.get("totalPages"));
        model.addAttribute("currentPage", pageResult.get("currentPage"));
        model.addAttribute("pageSize", pageResult.get("pageSize"));
        
        return "training/list";
    }
    
    // 연수 신청 상세 조회 페이지
    @GetMapping("/view/{trainingSeq}")
    public String viewPage(@PathVariable Long trainingSeq, Model model) {
        TrainingApplDTO training = trainingService.getTrainingById(trainingSeq);
        
        if (training != null) {
            // 복합 코드(다중값) 처리
            mapComplexCodeToModel(model, CodeConstants.ORG_TYPE_CODE, training.getOrgTypeCode(), "orgTypeNames");
            
            // 일반 코드 매핑
            mapCodeToModel(model, CodeConstants.SCHOOL_SYSTEM_CODE, training.getSchoolSystemCode(), "schoolSystemName");
            mapCodeToModel(model, CodeConstants.EDU_OFFICE_CD, training.getEduOfficeCd(), "eduOfficeName");
            mapCodeToModel(model, CodeConstants.FOUND_TYPE_CODE, training.getFoundTypeCode(), "foundTypeName");
            mapCodeToModel(model, CodeConstants.GENDER_CODE, training.getGenderCode(), "genderName");
            
            // 생년월일 포맷팅
            if (training.getBirthDate() != null) {
                model.addAttribute("formattedBirthDate", formatBirthDate(training.getBirthDate()));
            }
        }
        
        model.addAttribute("training", training);
        
        return "training/view";
    }
    
    // 연수 신청 폼 페이지
    @GetMapping("/apply")
    public String applyForm(Model model) {
        // 공통 코드 조회
        loadCommonCodes(model);
        
        // 빈 DTO 생성
        model.addAttribute("training", new TrainingApplDTO());
        
        return "training/apply";
    }
    
    // 연수 신청 처리
    @PostMapping("/apply")
    public String applySubmit(@ModelAttribute TrainingApplDTO training, 
                            BindingResult result, 
                            Model model) {
        // 유효성 검사
        if (result.hasErrors()) {
            loadCommonCodes(model);
            return "training/apply";
        }
        
        // 신청 상태 코드 설정
        training.setApplyStatusCode("S10"); // 신청 상태
        
        // 서비스 호출하여 저장
        trainingService.saveTraining(training);
        
        return "redirect:/trainings/list";
    }
    
    /**
     * 일반 코드 매핑 유틸리티 메서드
     */
    private void mapCodeToModel(Model model, String codeType, String codeValue, String attributeName) {
        if (codeValue != null) {
            CodeDTO code = codeService.getCodeByTypeAndCode(codeType, codeValue);
            if (code != null) {
                model.addAttribute(attributeName, code.getCodeName());
            }
        }
    }
    
    /**
     * 복합 코드 매핑 유틸리티 메서드
     */
    private void mapComplexCodeToModel(Model model, String codeType, String complexCode, String attributeName) {
        if (complexCode != null) {
            List<CodeDTO> codes = codeService.getCodesByTypeAndComplexCode(codeType, complexCode);
            if (codes != null && !codes.isEmpty()) {
                model.addAttribute(attributeName, codes.stream().map(CodeDTO::getCodeName).toList());
            }
        }
    }
    
    /**
     * 생년월일 포맷팅 유틸리티 메서드
     */
    private String formatBirthDate(String birthDate) {
        if (birthDate == null || birthDate.length() < 8) {
            return null;
        }
        return birthDate.substring(0, 4) + "-" + birthDate.substring(4, 6) + "-" + birthDate.substring(6, 8);
    }
    
    /**
     * 공통 코드 로딩 유틸리티 메서드
     */
    private void loadCommonCodes(Model model) {
        // 기관 유형 코드
        model.addAttribute("orgTypeCodes", 
            codeService.getCodesByType(CodeConstants.ORG_TYPE_CODE));
        
        // 과정 코드
        model.addAttribute("courseCodes", 
            codeService.getCodesByType(CodeConstants.COURSE_CODE));
        
        // 학교급 코드
        model.addAttribute("schoolSystemCodes", 
            codeService.getCodesByType(CodeConstants.SCHOOL_SYSTEM_CODE));
        
        // 교육청 코드
        model.addAttribute("eduOfficeCodes", 
            codeService.getCodesByType(CodeConstants.EDU_OFFICE_CD));
        
        // 설립 유형 코드
        model.addAttribute("foundTypeCodes", 
            codeService.getCodesByType(CodeConstants.FOUND_TYPE_CODE));
        
        // 성별 코드
        model.addAttribute("genderCodes", 
            codeService.getCodesByType(CodeConstants.GENDER_CODE));
    }
} 