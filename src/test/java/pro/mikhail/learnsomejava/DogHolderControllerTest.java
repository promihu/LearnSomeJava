package pro.mikhail.learnsomejava;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by Mikhail_Prosuntsov on 9/2/2016.
 */


@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("../../../../webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DogHolderControllerTest {


        @Autowired
        private WebApplicationContext wac;

        private MockMvc mockMvc;


        @Before
        public void setup() {
            //
               // this.mockMvc = MockMvcBuilders.standaloneSetup(new DogHolderController()).build();
        }


        @Test
        public void getAccount() throws Exception {

                System.out.println("**************************");
                this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

                this.mockMvc.perform(get("/greeting")).andExpect(status().isOk());
                System.out.println("**************************");
//
//                System.out.println((this.mockMvc).toString());

            //this.mockMvc.perform(get("/greeting"));
                    //.andExpect(status().isOk());
                    //.andExpect(content().contentType("application/json;charset=UTF-8")));
        }

//    @Test
//    public void testGetAllDogsReturnsDogs(){
//        given().when().get("/Dogs").then().
//                body(containsString("name"));
//    }


}
