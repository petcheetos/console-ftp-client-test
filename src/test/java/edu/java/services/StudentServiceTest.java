package edu.java.services;

import edu.java.entities.Student;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.*;

public class StudentServiceTest {
    private StudentService studentService;

    @BeforeMethod
    public void setUp() {
        studentService = new StudentService(new HashSet<>());
    }

    @Test
    public void testAddStudent() {
        Student student = studentService.addStudent("Student");
        assertEquals(student.getName(), "Student");
        assertTrue(studentService.getStudents().contains(student));
    }

    @Test
    public void testGetStudents() {
        studentService.addStudent("Student1");
        studentService.addStudent("Student2");

        HashSet<Student> students = studentService.getStudents();
        assertEquals(students.size(), 2);
        assertTrue(students.contains(new Student(1, "Student1")));
        assertTrue(students.contains(new Student(2, "Student2")));
    }

    @Test
    public void testRemoveStudentById() {
        Student student = studentService.addStudent("Student");
        studentService.removeStudentById(student.getId());
        assertTrue(studentService.getStudents().isEmpty());
    }

    @Test
    public void testGetListStudentsSortedAlphabetically() {
        studentService.addStudent("Student");
        studentService.addStudent("AStudent");

        List<Student> sortedStudents = studentService.getListStudentsSortedAlphabetically();
        assertEquals(sortedStudents.get(0).getName(), "AStudent");
        assertEquals(sortedStudents.get(1).getName(), "Student");
    }

    @Test
    public void testGetListStudentsByName() {
        studentService.addStudent("Student");
        studentService.addStudent("Meow");
        studentService.addStudent("Student");

        List<Student> studentsByName = studentService.getListStudentsByName("Student");
        assertEquals(studentsByName.size(), 2);
        assertTrue(studentsByName.stream().allMatch(student -> student.getName().equals("Student")));
    }

    @Test
    public void testGetNameById() {
        Student student = studentService.addStudent("Student");
        String name = studentService.getNameById(student.getId());
        assertEquals(name, "Student");

        assertNull(studentService.getNameById(999));
    }

    @Test
    public void testGenerateId() {
        assertEquals(studentService.generateId(), 1);
        studentService.addStudent("Student");
        assertEquals(studentService.generateId(), 2);
    }
}
