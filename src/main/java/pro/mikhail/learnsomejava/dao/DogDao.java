package pro.mikhail.learnsomejava.dao;

import pro.mikhail.learnsomejava.model.Dog;

import java.util.Map;

/**
 * Created by Mikhail_Prosuntsov on 9/23/2016.
 */
public interface DogDao {

        public Map<Integer, Dog> getAllDogs();

        public Dog getDog(int id);

        public int saveDog(Dog dog);

        public boolean updateDog(int id, Dog dog);

        public Dog removeDoge(int id);

}
