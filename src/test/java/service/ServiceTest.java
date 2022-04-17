package service;

import domain.Nota;
import domain.Pair;
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
    StudentXMLRepository studentXMLRepo;
    TemaXMLRepository temaXMLRepo;
    NotaXMLRepository notaXMLRepo;

    Service service;

    @Before
    public void setUp() throws Exception {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        studentXMLRepo = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        temaXMLRepo = new TemaXMLRepository(temaValidator, "teme_test.xml");
        notaXMLRepo = new NotaXMLRepository(notaValidator, "note_test.xml");

        service = new Service(studentXMLRepo, temaXMLRepo, notaXMLRepo);
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
    public void testSaveStudentidIsNull() {

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

    @Test
    public void test_addStudent(){
        assertEquals(service.saveStudent("1006", "name2", 100),0);
        service.deleteStudent("1006");
        assertEquals(0,service.saveStudent(null, "name932", 932));
        assertEquals(0,service.saveStudent("", "name932", 932));
        assertEquals(0,service.saveStudent("1009", "", 932));
        service.deleteStudent("1009");
        service.saveStudent("1008", "Duplicate", 937);
        assertEquals(0,service.saveStudent("1008", "name932", 936));
        service.deleteStudent("1008");
        assertEquals(1, service.saveStudent("1009", "name932", 111));
        service.deleteStudent("1009");
        assertEquals(0,service.saveStudent("1009", "name932", 939));
        service.deleteStudent("1009");
        service.saveStudent("1008", "Duplicate", 937);
        assertEquals(0,service.saveStudent("1008", "name932", 936));
        service.deleteStudent("1008");
        assertEquals(1,service.saveStudent("1010", "Nume", 900));
        service.deleteStudent("1010");


    }

    @Test
    public void test_addTema() {
        assertEquals(0, service.saveTema(null, "desc", 10, 7));
        assertEquals(0, service.saveTema("111", "", 10, 7));
        assertEquals(0, service.saveTema("112", "desc", 0, 7));
        assertEquals(0, service.saveTema("113", "desc", 7, 15));
        assertEquals(1, service.saveTema("114", "descriere", 7, 4));
    }

    @Test
    public void test_addNota(){
        service.saveStudent("222", "NumeTest", 898);
        service.saveTema("121", "descr", 10, 7);
        assertEquals(-1,service.saveNota("333","121", 7, 8,"bun"));
//        assertEquals(-1,service.saveNota("222","222", 7, 8,"bun"));
        assertEquals(1, service.saveNota("222","121", 8.5, 8,"bun"));
        notaXMLRepo.delete(new Pair<>("222","121"));
//        assertEquals(7, notaXMLRepo.findOne(new Pair<>("222", "121")).getNota());

        assertEquals(1, service.saveNota("222","121", 8.5, 11,"bun"));
        notaXMLRepo.delete(new Pair<>("222","121"));
        assertEquals(0, service.saveNota("222","121", 11, 11,"bun"));
        notaXMLRepo.delete(new Pair<>("222","121"));
        assertEquals(0, service.saveNota("222","121", 8, -2,"bun"));
        notaXMLRepo.delete(new Pair<>("222","121"));
        assertEquals(1, service.saveNota("222","121", 8.5, 14,"bun"));
        notaXMLRepo.delete(new Pair<>("222","121"));

    }

    @Test
    public void integrationTest(){
        Student student = new Student("10001", "Ana", 922);
        Tema tema = new Tema("10001", "tema", 8, 7);

        assertEquals(1, service.saveStudent(student.getID(), student.getNume(), student.getGrupa()));
        assertEquals(1, service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()));
        assertEquals(0, service.saveNota(student.getID(),tema.getID(), 8, 8,"bun"));

        service.deleteStudent(student.getID());
        service.deleteTema(tema.getID());
    }

    @Test
    public void test_addStudent_integration(){
        assertEquals(0,service.saveStudent("1006", "Test1", 20));
        service.deleteStudent("1006");
        assertEquals(1,service.saveStudent("1010", "Test2", 813));
        service.deleteStudent("1010");
        assertEquals(0,service.saveStudent("1009", "", 932));
        service.deleteStudent("1009");
        service.saveStudent("110", "Duplicate1", 932);
        assertEquals(0,service.saveStudent("110", "Duplicate2", 932));
        service.deleteStudent("110");

    }


    @Test
    public void test_addAssignment_integration(){
        Student student = new Student("900", "Ionel", 5);
        Tema tema = new Tema("900", "tema", 19, 7);

        assertEquals(0, service.saveStudent(student.getID(), student.getNume(), student.getGrupa()));

        student.setGrupa(911);
        assertEquals(1, service.saveStudent(student.getID(), student.getNume(), student.getGrupa()));

        assertEquals(0, service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()));

        tema.setDeadline(10);
        assertEquals(1, service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()));

        service.deleteStudent(student.getID());
        service.deleteTema(tema.getID());
    }

    @Test
    public void test_addGrade_integration(){
        Student student = new Student("", "Dorel", 912);
        Tema tema = new Tema("800", "", 11, 7);

        assertEquals(0, service.saveStudent(student.getID(), student.getNume(), student.getGrupa()));

        student.setID("800");
        assertEquals(1, service.saveStudent(student.getID(), student.getNume(), student.getGrupa()));

        assertEquals(0, service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()));

        tema.setDescriere("new homework");
        assertEquals(1, service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(), tema.getStartline()));

        Nota nota = new Nota(new Pair<>(student.getID(), tema.getID()), 100, 10, "feedback");

        assertEquals(0, service.saveNota(student.getID(), tema.getID(), nota.getNota(), nota.getSaptamanaPredare(), nota.getFeedback()));

        nota.setNota(9);
        assertEquals(1, service.saveNota(student.getID(), tema.getID(), nota.getNota(), nota.getSaptamanaPredare(), nota.getFeedback()));

        service.deleteStudent(student.getID());
        service.deleteTema(tema.getID());
    }

}
