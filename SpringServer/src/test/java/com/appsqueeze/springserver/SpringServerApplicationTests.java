package com.appsqueeze.springserver;

import com.appsqueeze.springserver.model.employee.Employee;
import com.appsqueeze.springserver.model.employee.EmployeeDao;
import java.util.List;

import com.appsqueeze.springserver.model.student.Student;
import com.appsqueeze.springserver.model.student.StudentDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SpringServerApplicationTests {

  @Autowired
  private EmployeeDao employeeDao;
  @Autowired
  private StudentDao studentDao;

  @BeforeAll
  public void clear() {
    List<Employee> employees = employeeDao.getAllEmployees();
    for (Employee employee : employees) {
      employeeDao.delete(employee.getId());
    }
  }
  @Test
  void addStudent(){
    long id = 0;
    addStudent(id,"student1","password");
  }
  private void addStudent(long id, String username, String password) {
    Student student = new Student();
    student.setId(id);
    student.setUsername(username);
    student.setPassword(password);
    studentDao.save(student);
  }
  @Test
  void addEmployeeTest() {
    addEmployee("Bruce Wayne", "Building-X", "Security");
    addEmployee("Harvey Dent", "Building-2", "Police");
    addEmployee("Rachel", "Building-11", "IT");
  }

  private void addEmployee(String name, String location, String branch) {
    Employee employee = new Employee();
    employee.setName(name);
    employee.setLocation(location);
    employee.setBranch(branch);
    employeeDao.save(employee);
  }

}
