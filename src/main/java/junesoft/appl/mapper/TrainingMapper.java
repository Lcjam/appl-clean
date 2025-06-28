package junesoft.appl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import junesoft.appl.dto.TrainingApplDTO;

@Mapper
public interface TrainingMapper {
    
    // 모든 연수 신청 목록 조회
    List<TrainingApplDTO> selectAllTrainings();
    
    // 특정 연수 신청 상세 조회
    TrainingApplDTO selectTrainingById(@Param("trainingSeq") Long trainingSeq);
    
    // 페이징된 연수 신청 목록 조회
    List<TrainingApplDTO> selectTrainingsWithPaging(@Param("offset") int offset, @Param("pageSize") int pageSize);
    
    // 전체 연수 신청 건수 조회
    int countTotalTrainings();

    /**
     * 연수 신청 정보를 저장합니다.
     *
     * @param training 저장할 연수 신청 정보
     * @return 영향받은 행의 수
     */
    int insertTraining(TrainingApplDTO training);
} 