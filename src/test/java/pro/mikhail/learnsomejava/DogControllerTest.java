package pro.mikhail.learnsomejava;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.core.IsEqual;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.http.ContentType.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static io.restassured.RestAssured.given;


/**
 * Created by Mikhail_Prosuntsov on 9/2/2016.
 */

public class DogControllerTest {


        private List<Dog> testDogList;


        public DogControllerTest(){

        }

        @Test
        public void shouldHaveAtleastOneDogName(){
        given().when().get("localhost:8080/dog").then().
                body(containsString("name"));

                System.out.println("It works :)");
        }


    //post tests

    @Test
    public void addDogShouldReturnNoError(){

            Dog testDog = new Dog("Test1", "10/01/1981", 10, 20);

            given()
                .contentType("application/json")
                .body("{\"name\":\"Superdog1\",\"weight\":20}")
            .when()
                .post("http://localhost:8080/dog")
            .then()
                .assertThat()
                    .statusCode(201)
            ;
        }

    @Test
    public void shouldAddDog(){

        Dog testDog = new Dog("Test1", "10/01/1981", 10, 20);

        Response response =

        given()
                .contentType("application/json")
                .body(testDog)
            .when()
                .post("http://localhost:8080/dog")
            .then()
                .assertThat()
                .statusCode(201)
            .extract()
                .response();

        String addedDogAddress = response.getHeader("Location");

        given()
        .when()
            .get(addedDogAddress)
        .then()
            .assertThat()
            .statusCode(200)
            .body("name", equalTo(testDog.getName()))
            .body("height", equalTo(testDog.getHeight()))
            .body("weight", equalTo(testDog.getWeight()))
        ;

        given()
        .when()
            .delete(addedDogAddress)
        .then()
            .assertThat()
            .statusCode(200)
            .body("name", equalTo(testDog.getName()))
            .body("height", equalTo(testDog.getHeight()))
            .body("weight", equalTo(testDog.getWeight()))
        ;
    }


    //get tests

    @Test
    public void shouldGetDog(){

        Dog testDog = new Dog("shouldGetDog", "10/01/1981", 10, 20);

        //add dog
        Response response =

                given()
                        .contentType("application/json")
                        .body(testDog)
                        .when()
                        .post("http://localhost:8080/dog")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .response();

        String addedDogAddress = response.getHeader("Location");

        //test get
        given()
                .when()
                .get(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(testDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(testDog.getWeight()))
        ;

        //delete it
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(testDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(testDog.getWeight()))
        ;
    }

    @Test
    public void shouldReturnErrorIfNoDogToGet(){

        Dog testDog = new Dog("shouldGetDog", "10/01/1981", 10, 20);

        //add dog
        Response response =

                given()
                        .contentType("application/json")
                        .body(testDog)
                        .when()
                        .post("http://localhost:8080/dog")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .response();

        String addedDogAddress = response.getHeader("Location");

        //delete it
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(testDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(testDog.getWeight()))
        ;

        //test get
        given()
                .when()
                .get(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(500)
        ;
    }

    //put tests

    @Test
    public void shouldUpdateDog(){

        Dog testDog = new Dog("shouldGetDog", "10/01/1981", 10, 20);
        Dog oldNewDog = new Dog("shouldUpdateDog", 20);

        //add dog
        Response response =

                given()
                        .contentType("application/json")
                        .body(testDog)
                        .when()
                        .post("http://localhost:8080/dog")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .response();

        String addedDogAddress = response.getHeader("Location");

        //update the dog
        given()
                .when()
                .contentType("application/json")
                .body(oldNewDog)
                .put(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
        ;

        //check dog is updated
        given()
                .when()
                .get(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(oldNewDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(oldNewDog.getWeight()))
        ;

        //delete the dog
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(oldNewDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(oldNewDog.getWeight()))
        ;


    }

    @Test
    public void shouldReturnErrorIfNoDogToUpdate(){

        Dog testDog = new Dog("shouldGetDog", "10/01/1981", 10, 20);
        Dog oldNewDog = new Dog("shouldUpdateDog", 20);

        //add dog
        Response response =

                given()
                        .contentType("application/json")
                        .body(testDog)
                        .when()
                        .post("http://localhost:8080/dog")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .response();

        String addedDogAddress = response.getHeader("Location");

        //delete it
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(testDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(testDog.getWeight()))
        ;

        //update the dog
        given()
                .when()
                .contentType("application/json")
                .body(oldNewDog)
                .put(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(500)
        ;
    }


    //delete tests

    @Test
    public void shouldDeleteDog(){

        Dog testDog = new Dog("shouldGetDog", "10/01/1981", 10, 20);

        //add dog
        Response response =

                given()
                        .contentType("application/json")
                        .body(testDog)
                        .when()
                        .post("http://localhost:8080/dog")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .response();

        String addedDogAddress = response.getHeader("Location");

        //delete the dog
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(testDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(testDog.getWeight()))
        ;
    }

    @Test
    public void shouldReturnErrorIfNoDogToDelete(){

        Dog testDog = new Dog("shouldGetDog", "10/01/1981", 10, 20);

        //add dog
        Response response =

                given()
                        .contentType("application/json")
                        .body(testDog)
                        .when()
                        .post("http://localhost:8080/dog")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .response();

        String addedDogAddress = response.getHeader("Location");

        //delete the dog
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(testDog.getName()))
                .body("height", equalTo(testDog.getHeight()))
                .body("weight", equalTo(testDog.getWeight()))
        ;

        //delete the dog again
        given()
                .when()
                .delete(addedDogAddress)
                .then()
                .assertThat()
                .statusCode(500)
        ;
    }




    private Dog createAndGetDog(Dog dog){

        return dog;
    }
}
