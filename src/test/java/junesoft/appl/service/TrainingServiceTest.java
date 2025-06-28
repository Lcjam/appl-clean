package junesoft.appl.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import junesoft.appl.dto.TrainingApplDTO;
import junesoft.appl.mapper.TrainingMapper;
import junesoft.appl.service.impl.TrainingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

    @Mock
    private TrainingMapper trainingMapper;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private TrainingApplDTO sampleTraining;

    @BeforeEach
    void setUp() {
        sampleTraining = new TrainingApplDTO();
        sampleTraining.setTrainingSeq(1L);
        sampleTraining.setApplicantName("홍길동");
        sampleTraining.setSchoolSystemCode("E");
        sampleTraining.setEduOfficeCode("7530000");
    }

    @Test
    void getAllTrainings_ShouldReturnListOfTrainings() {
        // Given
        List<TrainingApplDTO> expectedTrainings = Arrays.asList(sampleTraining);
        when(trainingMapper.selectAllTrainings()).thenReturn(expectedTrainings);

        // When
        List<TrainingApplDTO> actualTrainings = trainingService.getAllTrainings();

        // Then
        assertNotNull(actualTrainings);
        assertEquals(expectedTrainings.size(), actualTrainings.size());
        assertEquals(expectedTrainings.get(0).getTrainingSeq(), actualTrainings.get(0).getTrainingSeq());
        verify(trainingMapper).selectAllTrainings();
    }

    @Test
    void getTrainingById_ShouldReturnTraining() {
        // Given
        when(trainingMapper.selectTrainingById(1L)).thenReturn(sampleTraining);

        // When
        TrainingApplDTO actualTraining = trainingService.getTrainingById(1L);

        // Then
        assertNotNull(actualTraining);
        assertEquals(sampleTraining.getTrainingSeq(), actualTraining.getTrainingSeq());
        assertEquals(sampleTraining.getApplicantName(), actualTraining.getApplicantName());
        verify(trainingMapper).selectTrainingById(1L);
    }

    @Test
    void getTrainingsWithPaging_ShouldReturnPagedResult() {
        // Given
        int page = 1;
        int pageSize = 10;
        int offset = (page - 1) * pageSize;
        List<TrainingApplDTO> expectedTrainings = Arrays.asList(sampleTraining);
        int totalCount = 1;

        when(trainingMapper.selectTrainingsWithPaging(offset, pageSize)).thenReturn(expectedTrainings);
        when(trainingMapper.countTotalTrainings()).thenReturn(totalCount);

        // When
        Map<String, Object> result = trainingService.getTrainingsWithPaging(page, pageSize);

        // Then
        assertNotNull(result);
        assertEquals(expectedTrainings, result.get("trainings"));
        assertEquals(totalCount, result.get("totalCount"));
        assertEquals(1, result.get("totalPages"));
        assertEquals(page, result.get("currentPage"));
        assertEquals(pageSize, result.get("pageSize"));
        
        verify(trainingMapper).selectTrainingsWithPaging(offset, pageSize);
        verify(trainingMapper).countTotalTrainings();
    }
} 