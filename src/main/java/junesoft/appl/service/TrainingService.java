package junesoft.appl.service;

import java.util.List;
import java.util.Map;

import junesoft.appl.dto.TrainingApplDTO;

public interface TrainingService {
    
    // 모든 연수 신청 목록 조회
    List<TrainingApplDTO> getAllTrainings();
    
    // 특정 연수 신청 상세 조회
    TrainingApplDTO getTrainingById(Long trainingSeq);
    
    // 페이징된 연수 신청 목록 조회
    Map<String, Object> getTrainingsWithPaging(int page, int pageSize);

    /**
     * 연수 신청 정보를 저장합니다.
     *
     * @param training 저장할 연수 신청 정보
     * @return 저장된 연수 신청 정보
     */
    TrainingApplDTO saveTraining(TrainingApplDTO training);
} 