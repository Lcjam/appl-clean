package junesoft.appl.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import junesoft.appl.dto.CodeDTO;
import junesoft.appl.dto.TrainingApplDTO;
import junesoft.appl.service.CodeService;
import junesoft.appl.service.TrainingService;

@WebMvcTest(TrainingController.class)
public class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingService trainingService;

    @MockBean
    private CodeService codeService;

    private TrainingApplDTO sampleTraining;
    private CodeDTO sampleCode;

    @BeforeEach
    void setUp() {
        sampleTraining = new TrainingApplDTO();
        sampleTraining.setTrainingSeq(1L);
        sampleTraining.setApplicantName("홍길동");
        sampleTraining.setSchoolSystemCode("E");
        sampleTraining.setEduOfficeCode("7530000");
        sampleTraining.setBirthDate("19900101");

        sampleCode = new CodeDTO();
        sampleCode.setCodeType("SCHOOL_SYSTEM_CODE");
        sampleCode.setCode("E");
        sampleCode.setCodeName("초등학교");
    }

    @Test
    void listPage_ShouldReturnListView() throws Exception {
        // Given
        Map<String, Object> pageResult = new HashMap<>();
        pageResult.put("trainings", Arrays.asList(sampleTraining));
        pageResult.put("totalCount", 1);
        pageResult.put("totalPages", 1);
        pageResult.put("currentPage", 1);
        pageResult.put("pageSize", 50);

        when(trainingService.getTrainingsWithPaging(1, 50)).thenReturn(pageResult);

        // When & Then
        mockMvc.perform(get("/trainings/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("training/list"))
                .andExpect(model().attributeExists("trainings"))
                .andExpect(model().attributeExists("totalCount"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("pageSize"));

        verify(trainingService).getTrainingsWithPaging(1, 50);
    }

    @Test
    void viewPage_ShouldReturnViewWithTraining() throws Exception {
        // Given
        when(trainingService.getTrainingById(1L)).thenReturn(sampleTraining);
        when(codeService.getCodeByTypeAndCode(anyString(), anyString()))
                .thenReturn(sampleCode);

        // When & Then
        mockMvc.perform(get("/trainings/view/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("training/view"))
                .andExpect(model().attributeExists("training"))
                .andExpect(model().attributeExists("schoolSystemName"))
                .andExpect(model().attributeExists("formattedBirthDate"));

        verify(trainingService).getTrainingById(1L);
        verify(codeService, atLeastOnce()).getCodeByTypeAndCode(anyString(), anyString());
    }

    @Test
    void viewPage_WithNonExistentTraining_ShouldReturnViewWithoutTraining() throws Exception {
        // Given
        when(trainingService.getTrainingById(999L)).thenReturn(null);

        // When & Then
        mockMvc.perform(get("/trainings/view/999"))
                .andExpect(status().isOk())
                .andExpect(view().name("training/view"))
                .andExpect(model().attribute("training", (Object) null));

        verify(trainingService).getTrainingById(999L);
    }
} 