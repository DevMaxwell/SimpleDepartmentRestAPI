package com.springDemoLearn.demo.controller;
import com.springDemoLearn.demo.entity.Department;
import com.springDemoLearn.demo.error.DepartmentNotFoundException;
import com.springDemoLearn.demo.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// We would create our rest apis here

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    // ADDING LOGGERS TO BUILD
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // CREATE
    @PostMapping("/departments") // @Valid for JPA validation, @RequestBody for Json to be received
    public Department saveDepartment(@Valid @RequestBody Department department){
        // Log info
        LOGGER.info("Inside saveDepartment of Department Controller");
        return departmentService.saveDepartment(department);
    }

    // READ
    @GetMapping("/departments/all")
    public List<Department> fetchDepartmentList(){
        // Log info
        LOGGER.info("Inside fetchDepartment of Department Controller");
        return departmentService.fetchDepartmentList();
    }

    // To be able to pick parameter passed in the url we use path variable
    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        // Log info
        LOGGER.info("Inside fetchDepartmentById of Department Controller");
        return departmentService.fetchDepartmentById(departmentId);
    }

    // fetch department by name
    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }

    // UPDATE
    // We pass both the id  from Path Variable for the search value
    // and the Request body for the update value
    @PutMapping("departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        // Log info
        LOGGER.info("Inside updateDepartment of Department Controller");
        return departmentService.updateDepartment(departmentId, department);
    }

    // DELETE
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        // Log info
        LOGGER.info("Inside deleteDepartment of Department Controller");
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully";
    }

}
