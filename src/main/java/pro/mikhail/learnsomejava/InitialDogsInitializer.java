package pro.mikhail.learnsomejava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */
public class InitialDogsInitializer {

    private List<Dog> dogList = new ArrayList<>();

    InitialDogsInitializer() {

        Dog dog1 = new Dog("Dog1", "10/01/1981", 10, 20);
        dogList.add(dog1);

        Dog dog2 = new Dog("Dog2", "20/02/1982", 11, 21);
        dogList.add(dog2);

        Dog dog3 = new Dog("Dog3", "30/03/1983", 12, 22);
        dogList.add(dog3);
    }

    public List<Dog> getDogList() {
        return dogList;
    }

}
