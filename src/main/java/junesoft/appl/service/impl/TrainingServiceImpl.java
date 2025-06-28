package junesoft.appl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import junesoft.appl.dto.TrainingApplDTO;
import junesoft.appl.mapper.TrainingMapper;
import junesoft.appl.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingMapper trainingMapper;
    
    @Override
    public List<TrainingApplDTO> getAllTrainings() {
        return trainingMapper.selectAllTrainings();
    }

    @Override
    public TrainingApplDTO getTrainingById(Long trainingSeq) {
        return trainingMapper.selectTrainingById(trainingSeq);
    }
    
    @Override
    public Map<String, Object> getTrainingsWithPaging(int page, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이지 번호는 1부터 시작하지만, offset은 0부터 시작
        int offset = (page - 1) * pageSize;
        
        // 페이징된 목록 조회
        List<TrainingApplDTO> trainings = trainingMapper.selectTrainingsWithPaging(offset, pageSize);
        
        // 전체 데이터 개수 조회
        int totalCount = trainingMapper.countTotalTrainings();
        
        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        // 결과 맵에 데이터 담기
        result.put("trainings", trainings);
        result.put("totalCount", totalCount);
        result.put("totalPages", totalPages);
        result.put("currentPage", page);
        result.put("pageSize", pageSize);
        
        return result;
    }

    @Override
    public TrainingApplDTO saveTraining(TrainingApplDTO training) {
        // 현재 시간 설정
        java.util.Date currentTime = new java.util.Date();
        training.setWriteTime(currentTime);
        training.setModifyTime(currentTime);
        training.setApplyTime(currentTime);
        
        // 삭제 플래그 기본값 설정
        training.setDelFlag("N");
        
        // 매퍼를 통해 저장
        trainingMapper.insertTraining(training);
        
        return training;
    }
} 