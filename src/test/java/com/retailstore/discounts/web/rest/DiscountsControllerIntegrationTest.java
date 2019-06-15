package com.retailstore.discounts.web.rest;

import com.retailstore.discounts.helpers.TestHelper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountsControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = TestHelper.EMPLOYEE_ACC, password = TestHelper.COMMON_PASSWORD)
    public void givenAuthRequestOnDiscountEndpoint_shouldSucceedWith200() throws Exception {

        JSONObject payload = new JSONObject();
        payload.put("bill", 50);
        payload.put("includeGroceries", false);

        MvcResult mvcResult = mvc.perform(post("/api/discounts").contentType(MediaType.APPLICATION_JSON).content(payload.toString()))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("35"));
    }

    @Test
    public void givenNonAuthRequestOnDiscountEndpoint_shouldReturn401() throws Exception {

        JSONObject payload = new JSONObject();
        payload.put("bill", 50);
        payload.put("includeGroceries", false);

        mvc.perform(post("/api/discounts").contentType(MediaType.APPLICATION_JSON).content(payload.toString()))
                .andExpect(status().isUnauthorized());

    }

}
