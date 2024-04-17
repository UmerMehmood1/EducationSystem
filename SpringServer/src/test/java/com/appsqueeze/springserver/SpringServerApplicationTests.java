package com.appsqueeze.springserver;

import com.appsqueeze.springserver.model.course.Course;
import com.appsqueeze.springserver.model.course.CourseDao;
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
  @Autowired
  private CourseDao courseDao;

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
  @Test
  public void addDummyCourses(){
    long id = 0;
    addCourse(new Course(id,"Introduction To Programming","Alex Smith","Mon & Wed, 10:00 - 11:30 AM", 12, 45, "English","Programming Skills, C++ etc"));
    addCourse(new Course(id,"Advanced Mathematics","John Doe","Tue & Thu, 2:00 - 3:30 PM", 8, 45, "English","Maths, Calculus"));
    addCourse(new Course(id,"Modern Art History","Michael Johnson","Mon & Wed, 1:00 - 4:00 PM", 5, 45, "English","Arts, Creativity"));
    addCourse(new Course(id,"Business Economics","Emily Davis","Mon & Wed, 3:00 - 4:30 PM", 10, 45, "English","Business Flow"));
    addCourse(new Course(id,"Philosophy and Ethics","John Miller","Tue & Thu, 11:00 - 12:30 PM", 15, 45, "English","Thinking, Manners"));
  }
  private void addStudent(long id, String username, String password) {
    Student student = new Student();
    student.setId(id);
    student.setUsername(username);
    student.setPassword(password);
    studentDao.save(student);
  }
  private void addCourse(Course course) {
    courseDao.save(course);
  }

}
