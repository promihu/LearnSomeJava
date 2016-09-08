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

    @RequestMapping(method = RequestMethod.GET, value = "/dog", produces = "application/json")
    public ResponseEntity<List<Dog>> getAllDogs()  {

        List<Dog> dogList = initializer.getDogList();

        if(dogList.size() > 0){
            return new ResponseEntity<>(dogList, HttpStatus.OK);
        }
        //TODO: put right status here
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dog/{id}", produces = "application/json")
    public ResponseEntity<Dog> getOneDog(@PathVariable("id") int id)  {

        Dog dog = initializer.getDogList().get(id);

        if(dog != null){
            return new ResponseEntity<>(dog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dog", produces = "application/json")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog, UriComponentsBuilder ucBuilder) {

        initializer.getDogList().add(dog);
        HttpHeaders headers = new HttpHeaders();
        int dogIndex = initializer.getDogList().indexOf(dog);

        if(dogIndex != -1){
            headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dogIndex).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/dog/{id}", produces = "application/json")
    public ResponseEntity<Dog> updateDog(@PathVariable("id") int id, @RequestBody Dog dog, UriComponentsBuilder ucBuilder) {

        initializer.getDogList().set(id, dog);
        HttpHeaders headers = new HttpHeaders();
        int dogIndex = initializer.getDogList().indexOf(dog);

        if(dogIndex != -1){
            headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dogIndex).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/dog/{id}", produces = "application/json")
    public ResponseEntity<Dog> removeDog(@PathVariable("id") int id) {

        Dog removedDog = initializer.getDogList().remove(id);

        if (removedDog != null){
            return new ResponseEntity<>(removedDog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
