package pro.mikhail.learnsomejava;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */


@Controller
public class DogController {


    InitialDogsInitializer initializer = new InitialDogsInitializer();

    @Autowired
    public DogController() {

    }

    @RequestMapping(value = "/greeting")
    @ResponseBody
    public String greeting()    {
        return "holy crab";
    }

    @RequestMapping(method= RequestMethod.GET, value = "/dog", produces = "application/json")
    @ResponseBody
    public List<Dog> getAllDogs()  {

        return initializer.getDogList();

    }

    @RequestMapping(method= RequestMethod.GET, value = "/dog/{id}", produces = "application/json")
    @ResponseBody
    public Dog getOneDogs(@PathVariable("id") int id)  {

            return initializer.getDogList().get(id);
    }

    @RequestMapping(method= RequestMethod.POST, value = "/dog", produces = "application/json")
    public ResponseEntity<Void> createDog(@RequestBody Dog dog, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Dog " + dog.getName());

        initializer.getDogList().add(dog);

        //some copypasted bullshit here
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dog.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

        //TODO: add return of the created Dog
    }



    @RequestMapping(method= RequestMethod.PUT, value = "/dog/{id}", produces = "application/json")
    @ResponseBody
    public Dog updateDog(@PathVariable("id") int id, @RequestBody Dog dog) {

        initializer.getDogList().set(id, dog);
        return initializer.getDogList().get(id);
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/dog/{id}", produces = "application/json")
    @ResponseBody
    public Dog removeDog(@PathVariable("id") int id) {

        Dog removedDog = initializer.getDogList().get(id);
        initializer.getDogList().remove(id);

        return removedDog;

    }

}
