package repository;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class TemaRepositoryTest extends TestCase {
    TemaRepository temaRepository;

    @Before
    public void setUp() throws Exception {
        Validator<Tema> temaValidator = new TemaValidator();

        temaRepository = new TemaRepository(temaValidator);

    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveTema_temaIdIsNull() {
        //assertEquals(service.saveStudent("1000", "name", 221),1);
        Tema testTema = new Tema(null, "desc", 10, 7);
        assertEquals(testTema, temaRepository.save(testTema));
    }

    @Test
    public void testSaveTema_temaDescriereIsEmptyString() {
        //assertEquals(service.saveStudent("1000", "name", 221),1);
        Tema testTema = new Tema("111", "", 10, 7);
        assertEquals(testTema, temaRepository.save(testTema));
    }


}