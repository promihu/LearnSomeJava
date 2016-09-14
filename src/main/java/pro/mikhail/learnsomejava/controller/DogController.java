package pro.mikhail.learnsomejava.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;
import pro.mikhail.learnsomejava.model.Dog;
import pro.mikhail.learnsomejava.service.DogService;

import java.util.List;
import java.util.Map;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */


@Controller
public class DogController {

    private DogService dogService;

    public DogController(DogService dogService) {
            this.dogService = dogService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public ResponseEntity<Map<Integer, Dog>> getAllDogsTest()  {

        Map<Integer, Dog> dogList = dogService.getAllDogs();

        if(dogList.size() > 0){
            return new ResponseEntity<>(dogList, HttpStatus.OK);
        }
        //TODO: put right status here
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dog", produces = "application/json")
    public ResponseEntity<Map<Integer, Dog>> getAllDogs()  {

        Map<Integer, Dog> dogList = dogService.getAllDogs();

        if(dogList.size() > 0){
            return new ResponseEntity<>(dogList, HttpStatus.OK);
        }
        //TODO: put right status here
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dog/{id}", produces = "application/json")
    public ResponseEntity<Dog> getOneDog(@PathVariable("id") int id)  {

        Dog dog = dogService.getDog(id);

        if(dog != null){
            return new ResponseEntity<>(dog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dog", produces = "text/html")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog, UriComponentsBuilder ucBuilder) {

        int dogIndex = dogService.saveDog(dog);
        HttpHeaders headers = new HttpHeaders();

        if(dogIndex != -1){
            headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dogIndex).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/dog/{id}", produces = "application/json")
    public ResponseEntity<Dog> updateDog(@PathVariable("id") int id, @RequestBody Dog dog, UriComponentsBuilder ucBuilder) {


        if (dogService.updateDog(id, dog)) {

            HttpHeaders headers = new HttpHeaders();

            headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(id).toUri());
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/dog/{id}", produces = "application/json")
    public ResponseEntity<Dog> removeDog(@PathVariable("id") int id) {

        Dog removedDog = dogService.removeDoge(id);

        if (removedDog != null){
            return new ResponseEntity<>(removedDog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
