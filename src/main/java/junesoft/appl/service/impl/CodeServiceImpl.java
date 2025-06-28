package junesoft.appl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import junesoft.appl.dto.CodeDTO;
import junesoft.appl.mapper.CodeMapper;
import junesoft.appl.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;
    
    @Override
    public List<CodeDTO> getCodesByType(String codeType) {
        return codeMapper.selectCodesByType(codeType);
    }

    @Override
    public CodeDTO getCodeByTypeAndCode(String codeType, String code) {
        return codeMapper.selectCodeByTypeAndCode(codeType, code);
    }

    @Override
    public Map<String, List<CodeDTO>> getCodesByMultipleTypes(List<String> codeTypes) {
        Map<String, List<CodeDTO>> result = new HashMap<>();
        
        for (String codeType : codeTypes) {
            List<CodeDTO> codes = codeMapper.selectCodesByType(codeType);
            result.put(codeType, codes);
        }
        
        return result;
    }

    @Override
    public List<CodeDTO> getCodesByTypeAndComplexCode(String codeType, String complexCode) {
        if (complexCode == null || complexCode.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 복합 코드 처리 (예: "D003, D004")
        if (complexCode.contains(",")) {
            // 콤마로 분리하여 코드 목록 생성
            List<String> codeList = Arrays.stream(complexCode.split(","))
                                         .map(String::trim)
                                         .collect(Collectors.toList());
            
            return codeMapper.selectCodesByTypeAndCodeList(codeType, codeList);
        } else {
            // 단일 코드인 경우
            CodeDTO code = codeMapper.selectCodeByTypeAndCode(codeType, complexCode);
            if (code != null) {
                return List.of(code);
            }
            return new ArrayList<>();
        }
    }
} 