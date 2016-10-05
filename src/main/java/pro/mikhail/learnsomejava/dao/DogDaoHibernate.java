package pro.mikhail.learnsomejava.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pro.mikhail.learnsomejava.model.Dog;
import pro.mikhail.learnsomejava.util.HibernateUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mikhail_Prosuntsov on 9/23/2016.
 */
public class DogDaoHibernate implements DogDao {

    private Map<Integer, Dog> dogKeeper = new ConcurrentHashMap<>();
    private static Integer dogCounter = 0;

    public Map<Integer, Dog> getAllDogs(){

        return dogKeeper;
    }

    public Dog getDog(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Dog dog = (Dog) session.get(Dog.class, id);
        session.getTransaction().commit();

        return dog;

//        return getAllDogs().get(id);
    }

    public int saveDog(Dog dog){

        int savedDogID = generateNextId();
        getAllDogs().put(savedDogID, dog);

        return savedDogID;
    }

    public boolean updateDog(int id, Dog dog){

        if(getAllDogs().containsKey(id)) {
            getAllDogs().replace(id, dog);
            return true;
        }
        return false;
    }

    public Dog removeDoge(int id){

        return getAllDogs().remove(id);
    }

    public static synchronized Integer generateNextId(){
        return dogCounter++;
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
