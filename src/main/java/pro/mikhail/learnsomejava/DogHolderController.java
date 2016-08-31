package pro.mikhail.learnsomejava;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.google.gson.Gson;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */


@Controller
public class DogHolderController {


    InitialDogsInitializer initializer = new InitialDogsInitializer();

    public DogHolderController(DogHolderController dogHolderController) {
    }

    @RequestMapping(value = "/greeting")
    @ResponseBody
    public String greeting()  {
        Dog dog1 = initializer.getDogList().get(1);
        Gson gson = new Gson();
        return gson.toJson(dog1);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/dog")
    @ResponseBody
    public String getAllDogs()  {
        Gson gson = new Gson();
        return gson.toJson(initializer.getDogList());
    }

    @RequestMapping(method= RequestMethod.GET, value = "/dog/{id}")
    @ResponseBody
    public String getOneDogs(@PathVariable("id") int id)  {
        Dog dog = initializer.getDogList().get(id);
        Gson gson = new Gson();
        return gson.toJson(dog);

    }

    @RequestMapping(method= RequestMethod.POST, value = "/dog")
    public ResponseEntity<Void> createDog(@RequestBody Dog dog, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Dog " + dog.getName());

        initializer.getDogList().add(dog);

        //some copypasted bullshit here
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dog.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    @RequestMapping(method= RequestMethod.PUT, value = "/dog/{id}")
    @ResponseBody
    public String updateDog(@PathVariable("id") int id, @RequestBody Dog dog) {

        initializer.getDogList().set(id, dog);


        Gson gson = new Gson();

        return "Dog " + gson.toJson(initializer.getDogList().get(id)) +  " modified";
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/dog/{id}")
    @ResponseBody
    public String removeDog(@PathVariable("id") int id) {
        Dog dog = initializer.getDogList().get(id);
        Gson gson = new Gson();
        String removedDog = gson.toJson(dog);
        initializer.getDogList().remove(id);
        return "Dog " + removedDog +  " removed";

    }

}
