package pro.mikhail.learnsomejava.service;

import pro.mikhail.learnsomejava.dao.DogDao;
import pro.mikhail.learnsomejava.model.Dog;

import java.util.Map;

/**
 * Created by Mikhail_Prosuntsov on 9/13/2016.
 */
public class DogService {


    private DogDao dogDao;

    public DogService(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    public Map<Integer, Dog> getAllDogs(){

        return dogDao.getAllDogs();
    }


    public Dog getDog(int id){

        return dogDao.getDog(id);
    }


    public int saveDog(Dog dog){

        return dogDao.saveDog(dog);
    }

    public boolean updateDog(int id, Dog dog){
        return dogDao.updateDog(id, dog);
    }


    public Dog removeDoge(int id){

        return dogDao.removeDoge(id);
    }


}
