package ssvv.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.Validator;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhiteBoxServiceTest {

    private static Service service;

    @BeforeAll
    static void setUp() {
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Student> studentValidator = new StudentValidator();

        StudentXMLRepository studentRepository = null;
        TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "test_tema.xml");
        NotaXMLRepository notaRepository = null;

        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @AfterEach
    void tearDown() {
        try (FileWriter writer = new FileWriter("test_tema.xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + "<Entitati>\n" +
                    "</Entitati>\n");
        } catch (IOException e) {
        }
    }

    @Test
    void saveTema() {
        int actualResult = service.saveTema("1", "descriere", 5, 2);

        assertEquals(1, actualResult);
    }

    @Test
    void saveTema_shouldReturn0WhenIdNull() {
        int actualResult = service.saveTema(null, "descriere", 1, 1);

        assertEquals(0, actualResult);
    }

    @Test
    void saveTema_shouldReturn0WhenDescriereNull() {
        int actualResult = service.saveTema("1", null, 1, 1);

        assertEquals(0, actualResult);
    }

    @Test
    void saveTema_shouldReturn0WhenDeadlineLessThanStartline() {
        int actualResult = service.saveTema("1", "descriere", 1, 3);

        assertEquals(0, actualResult);
    }

    @Test
    void saveTema_shouldReturn0WhenStartlineOutOfBound() {
        int actualResult = service.saveTema("1", "descriere", 1, 16);

        assertEquals(0, actualResult);
    }
}
