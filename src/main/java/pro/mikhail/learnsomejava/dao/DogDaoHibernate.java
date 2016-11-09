package pro.mikhail.learnsomejava.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import pro.mikhail.learnsomejava.model.Dog;
import pro.mikhail.learnsomejava.util.HibernateUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mikhail_Prosuntsov on 9/23/2016.
 */
public class DogDaoHibernate implements DogDao {

    private Map<Integer, Dog> dogKeeper = new ConcurrentHashMap<>();
    private static Integer dogCounter = 0;

    public Map<Integer, Dog> getAllDogs(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Dog");
        List<Dog> dogList = query.list();

        Map<Integer, Dog> dogMap = new ConcurrentHashMap<>();
        for (Dog dog : dogList) {
            dogMap.put(dog.getId(), dog);
        }

        return dogMap;
    }

    @Transactional
    public Dog getDog(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Dog dog = (Dog) session.get(Dog.class, id);
        session.getTransaction().commit();

        return dog;
    }

    public int saveDog(Dog dog){


        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        int savedDogId = (int) session.save(dog);

        Dog savedDog = (Dog) session.get(Dog.class, savedDogId);

        session.getTransaction().commit();

        return savedDog.getId();
    }

    @Transactional
    public boolean updateDog(int id, Dog dog){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Dog dogUnderEdit = (Dog) session.get(Dog.class, id);
        dogUnderEdit.setName(dog.getName());
        dogUnderEdit.setDateOfBirth(dog.getDateOfBirth());
        dogUnderEdit.setWeight(dog.getWeight());
        dogUnderEdit.setHeight(dog.getHeight());

        if(true)
            throw new RuntimeException("HOLY CRAB");


        session.getTransaction().commit();

        return true;

    }

    public Dog removeDoge(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Dog dog = (Dog) session.get(Dog.class, id);
        session.delete(dog);
        session.getTransaction().commit();

        return dog;
    }

    public DogDaoHibernate() {


        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Dog dog1 = new Dog( "Dog1_Hiber", "10/01/1981", 11, 21 );
        Dog dog2 = new Dog( "Dog2_Hiber", "10/01/1981", 12, 22 );
        Dog dog3 = new Dog( "Dog3_Hiber", "10/01/1981", 13, 23 );

        session.save(dog1);
        session.save(dog2);
        session.save(dog3);
        session.getTransaction().commit();

    }
}
