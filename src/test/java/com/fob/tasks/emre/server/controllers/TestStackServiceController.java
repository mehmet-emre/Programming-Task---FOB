package com.fob.tasks.emre.server.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import com.fob.tasks.emre.common.InjectionManager;
import com.fob.tasks.emre.common.PropertyManager;
import com.fob.tasks.emre.server.RestApiModule;
import com.fob.tasks.emre.server.RestApiServer;
import com.google.inject.Guice;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestApiServer.class)
@WebAppConfiguration
public class TestStackServiceController {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        PropertyManager.loadProperties();
        InjectionManager.loadInjector(Guice.createInjector(new RestApiModule()));
    }

    @Test
    public void testMainFlow() throws Exception {
        // add 1
        mockMvc.perform(get("/stackservice/push?item=1").header("authorization", PropertyManager.getAuthKey()));
        // view 1
        mockMvc.perform(get("/stackservice/view").header("authorization", PropertyManager.getAuthKey())
                .contentType(contentType))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult arg0) throws Exception {
                        assertTrue(arg0.getResponse().getContentAsString().equals("[1]"));
                    }
                });
        // pop 1
        mockMvc.perform(get("/stackservice/pop").contentType(contentType).header("authorization", PropertyManager.getAuthKey()))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult arg0) throws Exception {
                        assertTrue(arg0.getResponse().getContentAsString().equals("1"));
                    }
                });
        // view emtpy stack
        mockMvc.perform(get("/stackservice/view").header("authorization", PropertyManager.getAuthKey())
                .contentType(contentType))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult arg0) throws Exception {
                        assertTrue(arg0.getResponse().getContentAsString().equals("[]"));
                    }
                });
        // try to push bunch of ints and one char
        mockMvc.perform(get("/stackservice/push?item=2").header("authorization", PropertyManager.getAuthKey()));
        mockMvc.perform(get("/stackservice/push?item=3").header("authorization", PropertyManager.getAuthKey()));
        mockMvc.perform(get("/stackservice/push?item=a").header("authorization", PropertyManager.getAuthKey()));
        // push last int item
        mockMvc.perform(get("/stackservice/pop").header("authorization", PropertyManager.getAuthKey())
                .contentType(contentType))
                .andExpect(new ResultMatcher() {
            @Override
            public void match(MvcResult arg0) throws Exception {
                assertTrue(arg0.getResponse().getContentAsString().equals("3"));
            }
        });
        // view only int items
        mockMvc.perform(get("/stackservice/view").header("authorization", PropertyManager.getAuthKey())
                .contentType(contentType))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult arg0) throws Exception {
                        assertTrue(arg0.getResponse().getContentAsString().equals("[2]"));
                    }
                });
    }
}
