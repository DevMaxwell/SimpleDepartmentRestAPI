package com.springDemoLearn.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/// To mark this class as an entity that can communicate to the database we need to annotate it with
// @Entity and add a @Id -> for the primary key of the database
// GeneratedValue
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Please Add a Departnemt Nane") // Hibernate validation to make sure a value is always provided for departmentName
    private String departmentName;

    private String departmentAddress;
    private String departmentCode;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

//    public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode) {
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//        this.departmentAddress = departmentAddress;
//        this.departmentCode = departmentCode;
//    }

//    public Department(){
//
//    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentAddress='" + departmentAddress + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
}

// To add a column name explicitly to the database.
// You annotate with @Column(
//      name= "email",
//      nullable = false,
//      columnDefinition = "VARCHAR",
//      unique = true )

// To change the constraint name
// You annotate with @Table(
//  name = "departments"
//  uniqueConstarints = {
//      @UniqueConstraint(name = "student_email_unique", columnNames = "email")
//      }
// )

// JPA VALIDATION
// You can add other annotations like @Length(max = 5, min =1) -> specifies that property should be at least 1 char minimun and 5 char maximum in lengh
// @Size
// @Email
// @Positive
// NegativeOrZero

// HANDLING EXCEPTIONS for fetch departmentById
// So we create a package named errows and create a class called DepartmentNotFoundException that extends Exceptions,
// We store the previous return value in an Optional generic that may or may not contain a null value
// Then we throw a new exception i.e DepartmentNotFoundException from error package
