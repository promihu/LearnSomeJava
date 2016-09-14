
package pro.mikhail.learnsomejava.dao;

import pro.mikhail.learnsomejava.model.Dog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Mikhail_Prosuntsov on 9/13/2016.
 */
public class DogDao {

    private Map<Integer, Dog> dogKeeper = new ConcurrentHashMap<>();
    private static Integer dogCounter = 0;

    public Map<Integer, Dog> getAllDogs(){

        return dogKeeper;
    }

    public Dog getDog(int id){

        return getAllDogs().get(id);
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

    public DogDao() {

        int id1 = generateNextId();
        Dog dog1 = new Dog(id1, "Dog1", "10/01/1981", 11, 21);
        dogKeeper.put(id1, dog1);

        int id2 = generateNextId();
        Dog dog2 = new Dog(id2, "Dog2", "10/01/1981", 12, 22);
        dogKeeper.put(id2, dog2);

        int id3 = generateNextId();
        Dog dog3 = new Dog(id3, "Dog3", "10/01/1981", 13, 23);
        dogKeeper.put(id3, dog3);
    }

}
