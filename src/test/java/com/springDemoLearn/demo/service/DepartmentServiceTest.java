package com.springDemoLearn.demo.service;

import com.springDemoLearn.demo.entity.Department;
import com.springDemoLearn.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        Department department = Department.builder().departmentId(1L)
                .departmentCode("MGT201")
                .departmentAddress("LAGOS")
                .departmentName("ADMIN")
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("ADMIN")).thenReturn(department); // ARRANGE
    }

    @Test
    @DisplayName("Get data based on valid department name")
    public void whenvalidDepartmentName_thenDepartmentIsFound(){
        String departmentName = "ADMIN";
        Department found = departmentService.fetchDepartmentByName(departmentName); // ACT
        // comparing set departmentName with found {mocked} DepartmentName
        assertEquals(departmentName, found.getDepartmentName()); // ASSERT
    }
}


// Annotate your test class with @SpringBootTest
// Autowire in the DepartmentService layer as the implementation is dependent on it.
// Annotate the function for testing with @Test

// The service layer still calls the repository layer.
// Since we just want to test our service layer, and we are not interested in how the repository works yet.
// We need to mock the repository layer to be able to test the service layer without calling the actual repository layer

// The @BeforeEach annotation on the setUp method runs before each function test function is executed.
// we would be mocking the department repository before each test function.

// create an instance of the DepartmentRepository to be mocked and annotate with @MockBean
// create an instance of the Department class and use the builder method to set Mock values of Department
// to represent repository response
// Use Mockito.when to specify when the response should be returned
// in this case when departmentRepository.findByDepartmentByNameIgnoreCase is called then return(department).