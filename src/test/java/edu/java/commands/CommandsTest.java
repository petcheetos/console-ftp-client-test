package edu.java.commands;

import edu.java.console.ConsoleMessages;
import edu.java.entities.Student;
import edu.java.services.StudentService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;

import static org.testng.Assert.*;

public class CommandsTest {
    private Commands commands;
    private StudentService studentService;

    @BeforeMethod
    public void setUp() {
        studentService = new StudentService(new HashSet<>());
        commands = new Commands(studentService);
    }

    @Test
    public void testHelp() {
        String expected = "Команды:\n" +
                "help - показать список команд\n" +
                "show - показать всех студентов\n" +
                "list - показать студентов по имени\n" +
                "info - показать информацию о студенте по id\n" +
                "add - добавить студента\n" +
                "delete - удалить студента\n" +
                "exit - завершение работы программы";
        assertEquals(commands.help(), expected);
    }

    @Test
    public void testShowEmptyList() {
        assertEquals(commands.show(), ConsoleMessages.EMPTY_LIST);
    }

    @Test
    public void testShowNonEmptyList() {
        studentService.addStudent("Student1");
        studentService.addStudent("Student2");

        String result = commands.show();
        assertTrue(result.contains("Student1"));
        assertTrue(result.contains("Student2"));
    }

    @Test
    public void testListEmpty() {
        assertEquals(commands.list("Student1"), ConsoleMessages.EMPTY_LIST);
    }

    @Test
    public void testListNonEmpty() {
        studentService.addStudent("Student1");
        studentService.addStudent("Student2");

        String result = commands.list("Student1");
        assertTrue(result.contains("Student1"));
        assertFalse(result.contains("Student2"));
    }

    @Test
    public void testInfoNonExistentStudent() {
        assertEquals(commands.info(1), ConsoleMessages.NO_STUDENT_WITH_ID + "1");
    }

    @Test
    public void testInfoExistentStudent() {
        Student student = studentService.addStudent("Student");
        String result = commands.info(student.getId());
        assertTrue(result.contains("Student"));
    }

    @Test
    public void testAddStudent() {
        String result = commands.add("Student");
        assertTrue(result.contains("Student"));
    }

    @Test
    public void testDeleteStudent() {
        Student student = studentService.addStudent("Student");
        String result = commands.delete(student.getId());
        assertEquals(result, ConsoleMessages.STUDENT_DELETED);
    }
}
