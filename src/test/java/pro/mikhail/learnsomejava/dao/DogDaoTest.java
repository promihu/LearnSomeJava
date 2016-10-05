package pro.mikhail.learnsomejava.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import pro.mikhail.learnsomejava.model.Dog;

import static org.testng.Assert.assertEquals;
//import static org.testng.AssertJUnit.*;


//import static io.restassured.RestAssured.given;
//import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by Mikhail_Prosuntsov on 9/14/2016.
 */

@ContextConfiguration(locations = "classpath:/context.xml")
public class DogDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DogDaoInMemory dogDao;

    @Test
    public void shouldReturnDog(){

        int dogIndex = 1;
        dogDao.getDog(1);
        System.out.println("It works :)");
    }

    @Test
    public void shoudlReturnFirstDog(){

        Dog dog1 = new Dog(0, "Dog1", "10/01/1981", 11, 21);
        Dog dog = dogDao.getDog(0);
        assertEquals(dog.getHeight() /*actual*/, dog1.getHeight() /*expected*/);
        assertEquals(dog.getWeight() /*actual*/, dog1.getWeight() /*expected*/);
        assertEquals(dog.getDateOfBirth() /*actual*/, dog1.getDateOfBirth() /*expected*/);
        assertEquals(dog.getId() /*actual*/, dog1.getId() /*expected*/);
        assertEquals(dog.getName() /*actual*/, dog1.getName() /*expected*/);

    }



}
