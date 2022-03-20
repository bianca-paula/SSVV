package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTest extends TestCase {
    Service service;

    @Before
    public void setUp() throws Exception {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note_test.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveStudentGroupOutOfBounds() {
        //assertEquals(service.saveStudent("1000", "name", 221),1);
        assertEquals(service.saveStudent("1006", "name2", 100),0);
        service.deleteStudent("1006");
    }

    @Test
    public void testSaveStudentInBounds() {
        //assertEquals(service.saveStudent("1000", "name", 221),1);
        assertEquals(service.saveStudent("1009", "name932", 932),1);
        service.deleteStudent("1009");
    }


    //Take Home

//    @Test
//    public void testSaveStudent_idIsNumber() {
//        assertThrows(Exception.class, () -> service.saveStudent(109, "name932", 932));
//        //service.deleteStudent("1009");
//    }

    @Test
    public void testSaveStudent_idIsNull() {
        assertEquals(0,service.saveStudent(null, "name932", 932));
    }

    @Test
    public void testSaveStudent_idIsEmptyString() {
        assertEquals(0,service.saveStudent("", "name932", 932));
    }

//    @Test
//    public void testSaveStudent_numeIsNumber() {
//        assertEquals(service.saveStudent("1009", 1, 932),1);
//    }

    @Test
    public void testSaveStudent_numeIsNull() {
        assertEquals(0,service.saveStudent("1009", null, 932));
        service.deleteStudent("1009");

    }

    @Test
    public void testSaveStudent_numeIsEmptyString() {
        assertEquals(0,service.saveStudent("1009", "", 932));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_idIsString() {
        assertEquals(1,service.saveStudent("111", "name932", 932));
        service.deleteStudent("111");
    }

    @Test
    public void testSaveStudent_numeIsString() {
        assertEquals(1,service.saveStudent("1009", "Test", 932));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_grupaIsLowerBound() {
        assertEquals(0,service.saveStudent("1009", "name932", 110));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_grupaIsSmallerThanLowerBound() {
        assertEquals(0,service.saveStudent("1009", "name932", 109));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_grupaIsGreaterThanLowerBound() {
        assertEquals(1, service.saveStudent("1009", "name932", 111));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_grupaIsUpperBound() {
        assertEquals(0,service.saveStudent("1009", "name932", 938));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_grupaIsLowerThanUpperBound() {
        assertEquals(1, service.saveStudent("1009", "name932", 937));
        service.deleteStudent("1009");
    }

    @Test
    public void testSaveStudent_grupaIsGreaterThanUpperBound() {
        assertEquals(0,service.saveStudent("1009", "name932", 939));
        service.deleteStudent("1009");
    }


    @Test
    public void testSaveStudent_idAlreadyExists() {
        service.saveStudent("1008", "Duplicate", 937);
        assertEquals(0,service.saveStudent("1008", "name932", 936));
        service.deleteStudent("1008");
    }

//    @Test
//    public void testSaveStudent_grupaIsDecimal() {
//        assertEquals(service.saveStudent("1009", "name932", 110.1),1);
//    }

//    @Test
//    public void testSaveStudent_grupaIsString() {
//        assertEquals(service.saveStudent("1009", "name932", "test"),1);
//    }

}