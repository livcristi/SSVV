package ssvv.example.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Pair;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest
{
    @Mock
    StudentXMLRepository studentXmlRepo;
    @Mock
    TemaXMLRepository temaXmlRepo;
    @Mock
    NotaXMLRepository notaXmlRepo;

    @InjectMocks
    private Service service;

    @Test
    void testSaveStudent()
    {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 120;
        Student resultStudent = new Student(id, nume, grupa);

        // when
        when(studentXmlRepo.save(any(Student.class))).thenReturn(resultStudent);
        var serviceResult = service.saveStudent(id, nume, grupa);

        // then
        assertEquals(serviceResult, 1);
    }

    @Test
    void testSaveAssignment()
    {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 120;
        String descriere = "description";
        int deadline = 10;
        int startline = 8;
        Student resultStudent = new Student(id, nume, grupa);
        Tema resultAssignment = new Tema(id, descriere, deadline, startline);

        // when
        when(studentXmlRepo.save(any(Student.class))).thenReturn(resultStudent);
        when(temaXmlRepo.save(any(Tema.class))).thenReturn(resultAssignment);
        var studentResult = service.saveStudent(id, nume, grupa);
        var temaResult = service.saveTema(id, descriere, deadline, startline);

        // then
        assertEquals(studentResult, 1);
        assertEquals(temaResult, 1);
    }

    @Test
    void testSaveGrade()
    {
        // given
        String id = "id";
        String nume = "name";
        int grupa = 120;
        String descriere = "description";
        int deadline = 10;
        int startline = 8;
        int predat = 13;
        String feedback = "feedback";
        Student resultStudent = new Student(id, nume, grupa);
        Tema resultAssignment = new Tema(id, descriere, deadline, startline);
        Nota resultGrade = new Nota(new Pair<>(id, id), 1, predat, feedback);

        // when
        when(studentXmlRepo.save(any(Student.class))).thenReturn(resultStudent);
        when(temaXmlRepo.save(any(Tema.class))).thenReturn(resultAssignment);
        when(studentXmlRepo.findOne(any(String.class))).thenReturn(resultStudent);
        when(temaXmlRepo.findOne(any(String.class))).thenReturn(resultAssignment);
        when(notaXmlRepo.save(any(Nota.class))).thenReturn(resultGrade);

        var studentResult = service.saveStudent(id, nume, grupa);
        var temaResult = service.saveTema(id, descriere, deadline, startline);
        var gradeResult = service.saveNota(id, id, 10, predat, feedback);

        // then
        assertEquals(studentResult, 1);
        assertEquals(temaResult, 1);
        assertEquals(gradeResult, 0);
    }
}
