package pro.mikhail.learnsomejava.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;
import pro.mikhail.learnsomejava.model.Dog;
import pro.mikhail.learnsomejava.model.InitialDogsInitializer;
import pro.mikhail.learnsomejava.service.DogService;

import java.util.List;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */


@Controller
public class DogController {


    InitialDogsInitializer initializer = new InitialDogsInitializer();

    private DogService dogService;

    public DogController(DogService dogService) {
            this.dogService = dogService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public ResponseEntity<List<Dog>> getAllDogsTest()  {

        List<Dog> dogList = dogService.getAllDogs();

        if(dogList.size() > 0){
            return new ResponseEntity<>(dogList, HttpStatus.OK);
        }
        //TODO: put right status here
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
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

    @RequestMapping(method = RequestMethod.POST, value = "/dog", produces = "text/html")
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

        //not sure if it is needed, but let's keep for now
        //probably better to check parameters in the request
        Dog updatedDog = initializer.getDogList().get(id);
        if (dog.getName() != null)
            updatedDog.setName(dog.getName());
        if (dog.getDateOfBirth() != null)
            updatedDog.setDateOfBirth(dog.getDateOfBirth());
        if (dog.getHeight() != 0)
            updatedDog.setHeight(dog.getHeight());
        if (dog.getWeight() != 0)
            updatedDog.setWeight(dog.getWeight());


        initializer.getDogList().set(id, updatedDog);
        HttpHeaders headers = new HttpHeaders();
        int dogIndex = initializer.getDogList().indexOf(updatedDog);

        if(dogIndex != -1){
            headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dogIndex).toUri());
            return new ResponseEntity<>(headers, HttpStatus.OK);
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
