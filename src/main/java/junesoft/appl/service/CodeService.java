package junesoft.appl.service;

import java.util.List;
import java.util.Map;

import junesoft.appl.dto.CodeDTO;

public interface CodeService {
    
    // 특정 코드 유형의 모든 코드 조회
    List<CodeDTO> getCodesByType(String codeType);
    
    // 특정 코드 유형 및 코드 값으로 코드 조회
    CodeDTO getCodeByTypeAndCode(String codeType, String code);
    
    // 여러 코드 유형의 모든 코드 조회
    Map<String, List<CodeDTO>> getCodesByMultipleTypes(List<String> codeTypes);
    
    // 복합 코드 처리 및 코드명 가져오기
    List<CodeDTO> getCodesByTypeAndComplexCode(String codeType, String complexCode);
} 