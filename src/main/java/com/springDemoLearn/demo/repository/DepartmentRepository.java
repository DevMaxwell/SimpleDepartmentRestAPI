package com.springDemoLearn.demo.repository;
import com.springDemoLearn.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Since there is no explicit method for (findByName)
    // We are going to have to create one ourselves
    // using the proper naming convention

    public Department findByDepartmentName(String departmentName);

    public Department findByDepartmentNameIgnoreCase(String departmentName);


}