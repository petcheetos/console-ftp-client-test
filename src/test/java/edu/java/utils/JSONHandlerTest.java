package edu.java.utils;

import edu.java.entities.Student;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JSONHandlerTest {
    private JSONHandler jsonHandler;
    private HashSet<Student> students;

    @BeforeMethod
    public void setUp() {
        jsonHandler = new JSONHandler();
        students = new HashSet<>();
        students.add(new Student(1, "Student1"));
        students.add(new Student(2, "Student2"));
    }

    @Test
    public void testSerializeStudents() {
        String expected = "{\n" +
                "  \"students\": [\n" +
                "\t{\"id\": 1, \"name\": \"Student1\"},\n" +
                "\t{\"id\": 2, \"name\": \"Student2\"}\n" +
                "  ]\n}";
        String actual = jsonHandler.serializeStudents(students);
        assertEquals(actual, expected);
    }

    @Test
    public void testParseData() {
        String jsonData = "{\n" +
                "  \"students\": [\n" +
                "    {\"id\": 1, \"name\": \"Student1\"},\n" +
                "    {\"id\": 2, \"name\": \"Student2\"}\n" +
                "  ]\n}";
        HashSet<Student> students = jsonHandler.parseData(jsonData);

        assertEquals(students.size(), 2);

        assertTrue(students.contains(new Student(1, "Student1")));
        assertTrue(students.contains(new Student(2, "Student2")));
    }
}