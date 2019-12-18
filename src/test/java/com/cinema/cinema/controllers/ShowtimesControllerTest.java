package com.cinema.cinema.controllers;

import com.cinema.cinema.CinemaApplication;
import com.cinema.cinema.repositories.FilmRepository;
import com.cinema.cinema.repositories.RoomRepository;
import com.cinema.cinema.repositories.ShowTimeRepository;
import com.cinema.cinema.repositories.TimePriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@WebAppConfiguration
class ShowtimesControllerTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Transactional
    void storeTestCase0401() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("film", "1")
                .param("room", "1")
                .param("timePrice", "1")
                .param("date", "2019-12-30"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-done"));
    }

    @Test
    @Transactional
    void storeTestCase0402() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("film", "1")
                .param("room", "2")
                .param("timePrice", "1")
                .param("date", "2019-12-30"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-done"));
    }

    @Test
    void storeTestCase0501() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-create"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","film","NotNull"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","room","NotNull"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","timePrice","NotNull"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","date","NotEmpty"));
    }

    @Test
    void storeTestCase0502() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("room", "2")
                .param("timePrice", "1")
                .param("date", "2019-12-30"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-create"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","film","NotNull"));
    }

    @Test
    void storeTestCase0503() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("film", "1")
                .param("timePrice", "1")
                .param("date", "2019-12-30"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-create"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","room","NotNull"));
    }
    @Test
    void storeTestCase0504() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("film", "1")
                .param("room", "2")
                .param("date", "2019-12-30"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-create"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","timePrice","NotNull"));
    }
    @Test
    void storeTestCase0505() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("film", "1")
                .param("room", "2")
                .param("timePrice", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-create"))
                .andExpect(model().attributeHasFieldErrorCode("showtime","date","NotEmpty"));
    }

    @Test
    void create() throws Exception {
        String uri = "/show-times/create";
        mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/show-time-create"));
    }
}