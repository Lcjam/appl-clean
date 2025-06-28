package junesoft.appl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import junesoft.appl.dto.CodeDTO;

@Mapper
public interface CodeMapper {
    
    // 특정 코드 유형의 모든 코드 조회
    List<CodeDTO> selectCodesByType(@Param("codeType") String codeType);
    
    // 특정 코드 유형 및 코드 값으로 코드 조회
    CodeDTO selectCodeByTypeAndCode(@Param("codeType") String codeType, @Param("code") String code);
    
    // 복합 코드 처리를 위한 메서드
    List<CodeDTO> selectCodesByTypeAndCodeList(@Param("codeType") String codeType, @Param("codeList") List<String> codeList);
} 