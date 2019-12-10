package com.cinema.cinema.controllers;

import com.cinema.cinema.CinemaApplication;
import com.cinema.cinema.models.Showtime;
import com.cinema.cinema.repositories.FilmRepository;
import com.cinema.cinema.repositories.RoomRepository;
import com.cinema.cinema.repositories.ShowTimeRepository;
import com.cinema.cinema.repositories.TimePriceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@WebAppConfiguration
class ShowtimesControllerTest {

    protected MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TimePriceRepository timePriceRepository;



    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    private static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void store() throws Exception {
        String uri = "/show-times/create";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("film", "1")
                        .param("room", "1")
                        .param("timePrice", "1")
                        .param("date", "2019-12-11")
                )
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String view = mvcResult.getModelAndView().getViewName();
        assertEquals(302, status);
        assertEquals("redirect:/show-times", view);
    }

    @Test
    void create() throws Exception {
        String uri = "/show-times/create";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String view = mvcResult.getModelAndView().getViewName();
        assertEquals(200, status);
        assertEquals("fragments/show-time-create", view);
    }
}