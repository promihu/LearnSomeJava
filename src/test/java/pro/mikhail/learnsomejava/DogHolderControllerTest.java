package pro.mikhail.learnsomejava;


import org.testng.annotations.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by Mikhail_Prosuntsov on 9/2/2016.
 */


//@RunWith(value=SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("../../../../webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DogHolderControllerTest {

        @Test
        public void shouldHaveAtleastOneDogName(){
        given().when().get("/dog").then().
                body(containsString("name"));
}

//
//        @Autowired
//        private WebApplicationContext wac;
//
//        private MockMvc mockMvc;
//
//
//        @Before
//        public void setup() {
//            //
//               // this.mockMvc = MockMvcBuilders.standaloneSetup(new DogHolderController()).build();
//        }
//
//
//        @Test
//        public void getAccount() throws Exception {
//
//                System.out.println("**************************");
//                this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//
//                this.mockMvc.perform(get("/greeting")).andExpect(status().isOk());
//                System.out.println("**************************");
//
//                System.out.println((this.mockMvc).toString());

            //this.mockMvc.perform(get("/greeting"));
                    //.andExpect(status().isOk());
                    //.andExpect(content().contentType("application/json;charset=UTF-8")));
//        }




}
