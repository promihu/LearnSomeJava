package pro.mikhail.learnsomejava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pro.mikhail.learnsomejava.dao.DogDaoInMemory;
import pro.mikhail.learnsomejava.model.Dog;
import pro.mikhail.learnsomejava.service.DogService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
//MockHttpServletRequestBuilder

/**
 * Created by Mikhail_Prosuntsov on 9/19/2016.
 */

@ContextConfiguration(locations = "classpath:/mockcontext.xml")
@WebAppConfiguration
public class DogControllerMockMVCTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpServletRequest request;
    @Autowired
    DogController   dogController;

    @Autowired
    DogService dogService;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void shouldReturnAllDogs() throws Exception {

        mockMvc.perform(get("/dog"))
                .andExpect(status().isOk());

    }

    //post tests
    @Test
    public void addDogShouldReturnNoError()throws Exception {

        Dog testDog = new Dog(DogDaoInMemory.generateNextId(), "Test1", "10/01/1981", 10, 20);

        ResultActions result = mockMvc.perform(post("/dog")
                .content(asJsonString(testDog))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk());
        ;

    }

    @Test
    public void shouldAddDogGetDog() throws Exception {

        Dog testDog = new Dog(DogDaoInMemory.generateNextId(),"Test1", "10/01/1981", 10, 20);

        ResultActions result = mockMvc
                .perform(post("/dog")
                    .content(asJsonString(testDog))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        ObjectMapper mapper = new ObjectMapper();



        String returnedDogString = mockMvc
                .perform(get(addedDogAddress))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        ;

        Dog receivedDog = mapper.readValue(returnedDogString ,Dog.class);

        assertReflectionEquals(testDog, receivedDog);

       //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk());
        ;
    }


    //get tests


    @Test
    public void shouldReturnErrorIfNoDogToGet() throws Exception {

        Dog testDog = new Dog(DogDaoInMemory.generateNextId(),"Test1", "10/01/1981", 10, 20);

        ResultActions result = mockMvc
                .perform(post("/dog")
                        .content(asJsonString(testDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        ObjectMapper mapper = new ObjectMapper();



        //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk());
        ;


        //test get
        String returnedDogString = mockMvc
                .perform(get(addedDogAddress))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString()
                ;
    }

    //put tests

    @Test
    public void shouldUpdateDog() throws Exception {

        Dog testDog = new Dog(DogDaoInMemory.generateNextId(), "shouldGetDog", "10/01/1981", 10, 20);
        Dog oldNewDog = new Dog(DogDaoInMemory.generateNextId(),"shouldUpdateDog", "10/01/1981", 1, 2);

        //add dog
        ResultActions result = mockMvc
                .perform(post("/dog")
                        .content(asJsonString(testDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        ObjectMapper mapper = new ObjectMapper();

        //update the dog
        result = mockMvc
                .perform(put(addedDogAddress)
                        .content(asJsonString(oldNewDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        //check dog is updated
        String returnedDogString = mockMvc
                .perform(get(addedDogAddress))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;

        Dog receivedDog = mapper.readValue(returnedDogString ,Dog.class);

        assertReflectionEquals(oldNewDog, receivedDog);

        //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk());
        ;

    }

    @Test
    public void shouldReturnErrorIfNoDogToUpdate() throws Exception {


        Dog testDog = new Dog(DogDaoInMemory.generateNextId(), "shouldGetDog", "10/01/1981", 10, 20);
        Dog oldNewDog = new Dog(DogDaoInMemory.generateNextId(),"shouldUpdateDog", "10/01/1981", 1, 2);

        //add dog
        ResultActions result = mockMvc
                .perform(post("/dog")
                        .content(asJsonString(testDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk());
        ;

        //update the dog
        result = mockMvc
                .perform(put(addedDogAddress)
                        .content(asJsonString(oldNewDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }


    //delete tests

    @Test
    public void shouldDeleteDog() throws Exception {


        Dog testDog = new Dog(DogDaoInMemory.generateNextId(),"Test1", "10/01/1981", 10, 20);

        ResultActions result = mockMvc
                .perform(post("/dog")
                        .content(asJsonString(testDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        ObjectMapper mapper = new ObjectMapper();



        String returnedDogString = mockMvc
                .perform(get(addedDogAddress))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;

        Dog receivedDog = mapper.readValue(returnedDogString ,Dog.class);

        assertReflectionEquals(testDog, receivedDog);

        //delete the dog
        returnedDogString = mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;

        Dog deletedDog = mapper.readValue(returnedDogString ,Dog.class);

        assertReflectionEquals(testDog, deletedDog);

    }

    @Test
    public void shouldReturnErrorIfNoDogToDelete() throws Exception {

        Dog testDog = new Dog(DogDaoInMemory.generateNextId(),"Test1", "10/01/1981", 10, 20);

        ResultActions result = mockMvc
                .perform(post("/dog")
                        .content(asJsonString(testDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String addedDogAddress = result.andReturn().getResponse().getHeader("Location");

        //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isOk())
                ;
        ;

        //delete the dog
        mockMvc.perform(delete(addedDogAddress))
                .andExpect(status().isNotFound())
        ;
        ;

    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}