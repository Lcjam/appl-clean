package junesoft.appl.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import junesoft.appl.dto.TrainingApplDTO;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrainingMapperTest {

    @Autowired
    private TrainingMapper trainingMapper;

    @Test
    @Sql("/sql/training-test-data.sql")
    void selectAllTrainings_ShouldReturnAllTrainings() {
        // When
        List<TrainingApplDTO> trainings = trainingMapper.selectAllTrainings();

        // Then
        assertNotNull(trainings);
        assertFalse(trainings.isEmpty());
    }

    @Test
    @Sql("/sql/training-test-data.sql")
    void selectTrainingById_ShouldReturnTraining() {
        // Given
        Long trainingSeq = 1L;

        // When
        TrainingApplDTO training = trainingMapper.selectTrainingById(trainingSeq);

        // Then
        assertNotNull(training);
        assertEquals(trainingSeq, training.getTrainingSeq());
    }

    @Test
    @Sql("/sql/training-test-data.sql")
    void selectTrainingsWithPaging_ShouldReturnPagedResult() {
        // Given
        int offset = 0;
        int pageSize = 10;

        // When
        List<TrainingApplDTO> trainings = trainingMapper.selectTrainingsWithPaging(offset, pageSize);

        // Then
        assertNotNull(trainings);
        assertTrue(trainings.size() <= pageSize);
    }

    @Test
    @Sql("/sql/training-test-data.sql")
    void countTotalTrainings_ShouldReturnTotalCount() {
        // When
        int totalCount = trainingMapper.countTotalTrainings();

        // Then
        assertTrue(totalCount > 0);
    }
} 