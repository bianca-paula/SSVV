package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class ServiceTest extends TestCase {
    Service service;

    @Before
    public void setUp() throws Exception {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveStudentGroupOutOfBounds() {
        //assertEquals(service.saveStudent("1000", "name", 221),1);
        assertEquals(service.saveStudent("1006", "name2", 100),1);
        service.deleteStudent("1006");
    }

    @Test
    public void testSaveStudentInBounds() {
        //assertEquals(service.saveStudent("1000", "name", 221),1);
        assertEquals(service.saveStudent("1009", "name932", 932),0);
        service.deleteStudent("1009");
    }
}