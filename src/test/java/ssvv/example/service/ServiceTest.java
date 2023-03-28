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

class ServiceTest {
    private static Service service;

    @BeforeAll
    static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();

        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "test_student.xml");
        TemaXMLRepository temaRepository = null;
        NotaXMLRepository notaRepository = null;

        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @AfterEach
    void tearDown() {
        try (FileWriter writer = new FileWriter("test_student.xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + "<Entitati>\n" +
                    "</Entitati>\n");
        } catch (IOException e) {
        }
    }

    @Test
    void saveStudent_shouldReturn1WhenTheInputIsValid() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 120;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenGroupLessThanBoundary() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 110;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenGroupGreaterThanBoundary() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 938;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenIdNull() {
        // given
        String id = null;
        String nume = "name";
        int grupa = 120;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenIdBlank() {
        // given
        String id = "";
        String nume = "name";
        int grupa = 120;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenNameNull() {
        // given
        String id = "id";
        String nume = null;
        int grupa = 120;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }

    @Test
    void saveStudent_shouldReturn0WhenNameBlank() {
        // given
        String id = "id";
        String nume = "";
        int grupa = 120;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(0, result);
    }

    @Test
    void saveStudent_shouldReturn1WhenGroupIsLowerBoundary() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 111;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn1WhenGroupIsHigherBoundary() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 937;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn1WhenGroupIsHigherBoundaryNeighbour() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 936;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn1WhenGroupIsLowerBoundaryNeighbour() {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 112;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn1WhenLenName1() {
        // given
        String id = "id";
        String nume = "n";
        int grupa = 937;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }

    @Test
    void saveStudent_shouldReturn1WhenLenId1() {
        // given
        String id = "i";
        String nume = "name";
        int grupa = 937;

        // when
        int result = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(1, result);
    }
}