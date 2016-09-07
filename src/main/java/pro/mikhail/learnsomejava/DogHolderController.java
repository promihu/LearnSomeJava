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

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */


@Controller
public class DogHolderController {


    InitialDogsInitializer initializer = new InitialDogsInitializer();

    @Autowired
    public DogHolderController() {

    }

    @RequestMapping(value = "/greeting")
    @ResponseBody
    public String greeting()  {
//        Dog dog1 = initializer.getDogList().get(1);
//        Gson gson = new Gson();
//        return gson.toJson(dog1);

        return "holy crab";
    }

    @RequestMapping(method= RequestMethod.GET, value = "/dog")
    @ResponseBody
    public String getAllDogs()  {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(initializer.getDogList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "smth wrong";
    }

    @RequestMapping(method= RequestMethod.GET, value = "/dog/{id}")
    @ResponseBody
    public String getOneDogs(@PathVariable("id") int id)  {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(initializer.getDogList().get(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "smth wrong";

    }

    @RequestMapping(method= RequestMethod.POST, value = "/dog")
    public ResponseEntity<Void> createDog(@RequestBody Dog dog, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Dog " + dog.getName());

        initializer.getDogList().add(dog);

        //some copypasted bullshit here
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dog/{id}").buildAndExpand(dog.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

        //TODO: add return of the created Dog
    }



    @RequestMapping(method= RequestMethod.PUT, value = "/dog/{id}")
    @ResponseBody
    public String updateDog(@PathVariable("id") int id, @RequestBody Dog dog) {

        initializer.getDogList().set(id, dog);

        ObjectMapper mapper = new ObjectMapper();

        try {
            return "Dog " + mapper.writeValueAsString(initializer.getDogList().get(id)) +  " modified";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "smth wrong";
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/dog/{id}")
    @ResponseBody
    public String removeDog(@PathVariable("id") int id) {

        ObjectMapper mapper = new ObjectMapper();

        String removedDog = null;
        try {
            removedDog = mapper.writeValueAsString(initializer.getDogList().get(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        initializer.getDogList().remove(id);

        return "Dog " + removedDog +  " removed";

    }

}
