package com.springDemoLearn.demo.service;
import com.springDemoLearn.demo.entity.Department;
import com.springDemoLearn.demo.error.DepartmentNotFoundException;
import com.springDemoLearn.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll().stream().toList();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        // return departmentRepository.findById(departmentId).get();

        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return department.get();

    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
//        return departmentRepository.findByDepartmentName(departmentName);

        // return a response ignoring alphabet case
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {

        //Hold object found from repo {by departmentId passed} in Department Object
        Department depDB = departmentRepository.findById(departmentId).get();

        // If department passed is not null and empty set its departmentName to department passed
        if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());
        }

        // If department passed is not null and empty set its departmentAddress to department passed
        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        // If department passed is not null and empty set its departmentName to department passed
        if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        // reach the departmentRepository and update to new object
        return departmentRepository.save(depDB);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

}
