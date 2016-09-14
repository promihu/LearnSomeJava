package pro.mikhail.learnsomejava.service;

import pro.mikhail.learnsomejava.dao.DogDao;
import pro.mikhail.learnsomejava.model.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail_Prosuntsov on 9/13/2016.
 */
public class DogService {


    private DogDao dogDao;

    public DogService(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    public List<Dog> getAllDogs(){

        return dogDao.getAllDogs();
    }


    public Dog getDog(int id){

        return dogDao.getDog(id);
    }


    public void saveDog(Dog dog){

        dogDao.saveDog(dog);
        return;
    }

    public void updateDog(int id, Dog dog){

        dogDao.updateDog(id, dog);
        return;
    }


    public Dog removeDoge(int id){

        return dogDao.removeDoge(id);
    }


}
