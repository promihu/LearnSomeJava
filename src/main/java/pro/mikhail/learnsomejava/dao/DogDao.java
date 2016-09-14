
package pro.mikhail.learnsomejava.dao;

import pro.mikhail.learnsomejava.model.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail_Prosuntsov on 9/13/2016.
 */
public class DogDao {

    private List<Dog> dogList = new ArrayList<>();

    public List<Dog> getAllDogs(){

        return dogList;
    }


    public Dog getDog(int id){

        return getAllDogs().get(id);
    }


    public void saveDog(Dog dog){

        getAllDogs().add(dog);
        return;
    }

    public void updateDog(int id, Dog dog){

        Dog updatedDog = getAllDogs().get(id);
        if (dog.getName() != null)
            updatedDog.setName(dog.getName());
        if (dog.getDateOfBirth() != null)
            updatedDog.setDateOfBirth(dog.getDateOfBirth());
        if (dog.getHeight() != 0)
            updatedDog.setHeight(dog.getHeight());
        if (dog.getWeight() != 0)
            updatedDog.setWeight(dog.getWeight());


        getAllDogs().set(id, updatedDog);
        return;
    }


    public Dog removeDoge(int id){

        return getAllDogs().remove(id);
    }

    public DogDao() {

        Dog dog1 = new Dog("Dog1", "10/01/1981", 10, 20);
        dogList.add(dog1);

        Dog dog2 = new Dog("Dog2", "20/02/1982", 11, 21);
        dogList.add(dog2);

        Dog dog3 = new Dog("Dog3", "30/03/1983", 12, 22);
        dogList.add(dog3);
    }

}
