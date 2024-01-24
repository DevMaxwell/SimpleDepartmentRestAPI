package com.springDemoLearn.demo.controller;

import com.springDemoLearn.demo.entity.Department;
import com.springDemoLearn.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = department.builder()
                .departmentAddress("Lagos")
                .departmentCode("CS122")
                .departmentName("Tech")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentAddress("Lagos")
                .departmentCode("CS122")
                .departmentName("Tech")
                .departmentId(1L)
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"departmentName\":\"Tech\",\n" +
                "    \"departmentAddress\":\"Lagos\",\n" +
                "    \"departmentCode\":\"CS122\"\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}



// Remember when testing a particular layer, the layer it calls is autowired in
// For controller layer, it would be service, for service it would be repository
// For repository both the repository(it extends JPA) and entityManager are autowired

// SAVE METHOD
// Since we are testing the controller which is literally the endpoint
// We have to use the @WebMvcTest annotation
// We create an object of type Department using builder() and also create another object of type Department in the test method
// We ask mockito to return the object declared outside class when the save method, passing in the object build() inside the method
// them we perform a mock passing the MockMvcRequestBuilders. post, since we are testing an endpoint that uses the POST method.
// passing in the url template, contentType, content(JSON to be passed when calling endpoint) and what we expect(status().isOk().


// FETCH BY ID METHOD
// literally the same thing, but we add the jsonPath to the ResultMatcher and what we expect