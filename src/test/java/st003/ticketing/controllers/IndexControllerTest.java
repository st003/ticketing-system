package st003.ticketing.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// The custom config for String Security does not get loaded as part of the slice test, so we use the
// "excludeAutoConfiguration = {SecurityAutoConfiguration.class}" argument to ignore Spring Security for this test
@WebMvcTest(controllers = IndexController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class IndexControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk());
    }
}
