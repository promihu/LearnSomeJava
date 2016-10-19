package pro.mikhail.learnsomejava.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import pro.mikhail.learnsomejava.model.Dog;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import static io.qala.datagen.RandomValue.*;
import static io.qala.datagen.StringModifier.Impls.*;
import static io.qala.datagen.RandomShortApi.*;
import static io.qala.datagen.RandomDate.*;


/**
 * Created by Mikhail_Prosuntsov on 10/15/2016.
 */


@ContextConfiguration(locations = "classpath:/HibernateContext.xml")

public class DogDaoHibernateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DogDaoHibernate dogDao;

    @Test
    public void shouldReturnDog(){

        int dogIndex = 1;
        dogDao.getDog(1);
        System.out.println("It works :)");
    }

    @Test
    public void shouldSaveReturnDog(){

        Dog dog1 = new Dog("Dog1", "10/01/1981", 11, 21);

        dogDao.saveDog(dog1);

        Dog dog = dogDao.getDog(dog1.getId());

        assertEquals(dog.getHeight() /*actual*/, dog1.getHeight() /*expected*/);
        assertEquals(dog.getWeight() /*actual*/, dog1.getWeight() /*expected*/);
        assertEquals(dog.getDateOfBirth() /*actual*/, dog1.getDateOfBirth() /*expected*/);
        assertEquals(dog.getId() /*actual*/, dog1.getId() /*expected*/);
        assertEquals(dog.getName() /*actual*/, dog1.getName() /*expected*/);

    }

    @Test
    public void shouldUpdateDog(){

        Dog dog1 = new Dog("Dog1", "10/01/1981", 11, 21);
        Dog dog2 = new Dog("Dog2", "10/01/1981", 31, 41);

        int dog1id = dogDao.saveDog(dog1);

        dogDao.updateDog(dog1id, dog2);

        Dog dog = dogDao.getDog(dog1id);

        assertEquals(dog.getHeight() /*actual*/, dog2.getHeight() /*expected*/);
        assertEquals(dog.getWeight() /*actual*/, dog2.getWeight() /*expected*/);
        assertEquals(dog.getDateOfBirth() /*actual*/, dog2.getDateOfBirth() /*expected*/);
        assertEquals(dog.getId() /*actual*/, dog1id /*expected*/);
        assertEquals(dog.getName() /*actual*/, dog2.getName() /*expected*/);

    }


    @Test
    public void shouldDeleteDog(){

        Dog dog1 = new Dog("Dog1", "10/01/1981", 11, 21);

        int dog1id = dogDao.saveDog(dog1);
        Dog dog = dogDao.getDog(dog1id);

        //check if the dog is in
        assertEquals(dog.getId() /*actual*/, dog1id /*expected*/);

        //remove the dog
        Dog removedDog = dogDao.removeDoge(dog.getId());


        //test that removed dog is what we have removed
        assertEquals(removedDog.getHeight() /*actual*/, dog.getHeight() /*expected*/);
        assertEquals(removedDog.getWeight() /*actual*/, dog.getWeight() /*expected*/);
        assertEquals(removedDog.getDateOfBirth() /*actual*/, dog.getDateOfBirth() /*expected*/);
        assertEquals(removedDog.getId() /*actual*/, dog.getId() /*expected*/);
        assertEquals(removedDog.getName() /*actual*/, dog.getName() /*expected*/);


        //get removed dog
        Dog noDog = dogDao.getDog(dog.getId());

        //test if there is really no dog
        assertNull(noDog);

    }

    //try some randomization


    @Test
    public void shouldSaveReturnRandomDog() throws ParseException {

        Dog dog1 = createRandomDog();

        dogDao.saveDog(dog1);

        Dog dog = dogDao.getDog(dog1.getId());

        assertEquals(dog.getHeight() /*actual*/, dog1.getHeight() /*expected*/);
        assertEquals(dog.getWeight() /*actual*/, dog1.getWeight() /*expected*/);
        assertEquals(dog.getDateOfBirth() /*actual*/, dog1.getDateOfBirth() /*expected*/);
        assertEquals(dog.getId() /*actual*/, dog1.getId() /*expected*/);
        assertEquals(dog.getName() /*actual*/, dog1.getName() /*expected*/);

    }

    @Test
    public void shouldUpdateRandomDog(){

        Dog dog1 = createRandomDog();
        Dog dog2 = createRandomDog();

        int dog1id = dogDao.saveDog(dog1);

        dogDao.updateDog(dog1id, dog2);

        Dog dog = dogDao.getDog(dog1id);

        assertEquals(dog.getHeight() /*actual*/, dog2.getHeight() /*expected*/);
        assertEquals(dog.getWeight() /*actual*/, dog2.getWeight() /*expected*/);
        assertEquals(dog.getDateOfBirth() /*actual*/, dog2.getDateOfBirth() /*expected*/);
        assertEquals(dog.getId() /*actual*/, dog1id /*expected*/);
        assertEquals(dog.getName() /*actual*/, dog2.getName() /*expected*/);

    }


    @Test
    public void shouldDeleteRandomDog(){

        Dog dog1 = createRandomDog();

        int dog1id = dogDao.saveDog(dog1);
        Dog dog = dogDao.getDog(dog1id);

        //check if the dog is in
        assertEquals(dog.getId() /*actual*/, dog1id /*expected*/);

        //remove the dog
        Dog removedDog = dogDao.removeDoge(dog.getId());


        //test that removed dog is what we have removed
        assertEquals(removedDog.getHeight() /*actual*/, dog.getHeight() /*expected*/);
        assertEquals(removedDog.getWeight() /*actual*/, dog.getWeight() /*expected*/);
        assertEquals(removedDog.getDateOfBirth() /*actual*/, dog.getDateOfBirth() /*expected*/);
        assertEquals(removedDog.getId() /*actual*/, dog.getId() /*expected*/);
        assertEquals(removedDog.getName() /*actual*/, dog.getName() /*expected*/);


        //get removed dog
        Dog noDog = dogDao.getDog(dog.getId());

        //test if there is really no dog
        assertNull(noDog);

    }

    private Dog createRandomDog(){

        return new Dog(alphanumeric(1, 100), (between(yearsAgo(30), startOfMonth()).localDate()).toString(), integer(1, 100), integer(1, 100));
    }


}
