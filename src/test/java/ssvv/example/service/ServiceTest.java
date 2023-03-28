package ssvv.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ssvv.example.domain.Student;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.Validator;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest
{
    private static Service service;

    @BeforeAll
    static void setUp()
    {
        Validator<Student> studentValidator = new StudentValidator();

        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "test_student.xml");
        TemaXMLRepository temaRepository = null;
        NotaXMLRepository notaRepository = null;

        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @AfterEach
    void tearDown()
    {
        try (FileWriter writer = new FileWriter("test_student.xml"))
        {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + "<Entitati>\n" + "</Entitati>\n");
        } catch (IOException e)
        {
        }
    }

    @Test
    void saveStudent_shouldReturn1WhenTheInputIsValid()
    {
        // given
        String id = "1";
        String nume = "Uga";
        int grupa = 222;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenTheNameIsEmpty()
    {
        // given
        String id = "1";
        String nume = "";
        int grupa = 222;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }
}