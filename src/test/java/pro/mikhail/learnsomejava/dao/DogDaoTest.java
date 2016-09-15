package pro.mikhail.learnsomejava.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by Mikhail_Prosuntsov on 9/14/2016.
 */

//@ContextConfiguration(locations = "classpath:application-context-test.xml")

@ContextConfiguration(locations = "classpath:/context.xml")
//@ContextConfiguration(locations = "../../../../../webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DogDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DogDao dogDao;

    @Test
    public void shouldReturnDog(){


       // dogDao = new DogDao();
        dogDao.getAllDogs();
        System.out.println("It works :)");
    }



}
