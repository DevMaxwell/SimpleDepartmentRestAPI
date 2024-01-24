package com.springDemoLearn.demo.repository;

import com.springDemoLearn.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // to fix
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder() // ARRANGE
                .departmentName("Tech")
                .departmentCode("CS122")
                .departmentAddress("Ibadan")
                .build();

        entityManager.persist(department);
    }

    @Test
    public void whenFindByIdThenReturnDepartment(){
        Department department = departmentRepository.findById(1L).get(); // ACT
        assertEquals("Tech", department.getDepartmentName()); // ASSERT
    }
}

// To test the repository
// first we use the builder to create a build of entity
// then we create a persist with entityManager
// in the test class, we create an instance of the Department entity
// and an assertEquals to verify if the findbyId() functions Name is same as the name in the Department Builder