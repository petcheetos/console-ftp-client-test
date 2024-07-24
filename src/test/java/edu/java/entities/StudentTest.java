package edu.java.entities;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StudentTest {

    @Test
    public void testConstructor() {
        Student student = new Student(1, "Student");
        assertEquals(student.getId(), 1);
        assertEquals(student.getName(), "Student");
    }

    @Test
    public void testEqualsAndHashCode() {
        Student student1 = new Student(1, "Student");
        Student student2 = new Student(1, "Student");
        Student student3 = new Student(2, "Student2");

        assertEquals(student1, student2);
        assertNotEquals(student1, student3);

        assertEquals(student1.hashCode(), student2.hashCode());
        assertNotEquals(student1.hashCode(), student3.hashCode());
    }

    @Test
    public void testCompareTo() {
        Student student1 = new Student(1, "AStudent");
        Student student2 = new Student(2, "BStudent");
        Student student3 = new Student(3, "AStudent");

        assertTrue(student1.compareTo(student2) < 0);
        assertTrue(student2.compareTo(student1) > 0);
        assertEquals(student1.compareTo(student3), 0);
    }

    @Test
    public void testToJSONObject() {
        Student student = new Student(1, "Student");
        String expectedJson = "{\"id\": 1, \"name\": \"Student\"}";
        assertEquals(student.toJSONObject(), expectedJson);
    }

    @Test
    public void testToString() {
        Student student = new Student(1, "Student");
        String expectedString = "Student (id = 1, name = 'Student')";
        assertEquals(student.toString(), expectedString);
    }
}