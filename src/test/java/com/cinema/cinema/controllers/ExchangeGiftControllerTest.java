package com.cinema.cinema.controllers;

import com.cinema.cinema.CinemaApplication;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@WebAppConfiguration
class ExchangeGiftControllerTest {

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
    void showExchangeGiftViewTC0601() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("card_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/exchange-gifts"))
                .andExpect(model().attribute("memberCard", hasProperty("id", is(1))))
                .andExpect(model().attribute("memberCard", hasProperty("point", is(1000))))
                .andExpect(model().attribute("memberCard", hasProperty("customer", hasProperty("name", is("ngo dinh ba")))));
    }

    @Test
    void showExchangeGiftViewTC0602() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("card_id", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/exchange-gifts"))
                .andExpect(model().attribute("memberCard", hasProperty("id", is(2))))
                .andExpect(model().attribute("memberCard", hasProperty("point", is(500))))
                .andExpect(model().attribute("memberCard", hasProperty("customer", hasProperty("name", is("nguyen van a")))));
    }

    @Test
    void showExchangeGiftViewTC0603() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("card_id", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/exchange-gifts"))
                .andExpect(model().attribute("memberCard", nullValue()));
    }

    @Test
    void showExchangeGiftViewTC0604() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("card_id", "abc$%^"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/exchange-gifts"))
                .andExpect(model().attribute("memberCard", nullValue()));
    }

    @Test
    void showExchangeGiftViewTC0605() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("card_id", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/exchange-gifts"))
                .andExpect(model().attribute("memberCard", hasProperty("id", is(3))))
                .andExpect(model().attribute("memberCard", hasProperty("point", is(0))))
                .andExpect(model().attribute("memberCard", hasProperty("customer", hasProperty("name", is("Tran van b")))));
    }

    @Test
    void showSearchGiftViewTC0701() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("gift_search", "co"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/list-gifts"))
                .andExpect(model().attribute("memberCard", hasProperty("id", is(1))))
                .andExpect(model().attribute("memberCard", hasProperty("point", is(1000))))
                .andExpect(model().attribute("memberCard", hasProperty("customer", hasProperty("name", is("ngo dinh ba")))))
                .andExpect(model().attribute("gifts", hasSize(3)))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("point", is(200)),
                                hasProperty("ticket", hasProperty("showtime", hasProperty("film", hasProperty("name", is("co dau 8 tuoi")))))
                        )
                )))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(3)),
                                hasProperty("point", is(300)),
                                hasProperty("ticket", hasProperty("showtime", hasProperty("film", hasProperty("name", is("co giao thao")))))
                        )
                )))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(4)),
                                hasProperty("point", is(250)),
                                hasProperty("food", hasProperty("name", is("coca")))
                        )
                )));
    }

    @Test
    void showSearchGiftViewTC0702() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("gift_search", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/list-gifts"))
                .andExpect(model().attribute("memberCard", hasProperty("id", is(1))))
                .andExpect(model().attribute("memberCard", hasProperty("point", is(1000))))
                .andExpect(model().attribute("memberCard", hasProperty("customer", hasProperty("name", is("ngo dinh ba")))))
                .andExpect(model().attribute("gifts", hasSize(4)))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("point", is(200)),
                                hasProperty("ticket", hasProperty("showtime", hasProperty("film", hasProperty("name", is("co dau 8 tuoi")))))
                        )
                )))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(3)),
                                hasProperty("point", is(300)),
                                hasProperty("ticket", hasProperty("showtime", hasProperty("film", hasProperty("name", is("co giao thao")))))
                        )
                )))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(4)),
                                hasProperty("point", is(250)),
                                hasProperty("food", hasProperty("name", is("coca")))
                        )
                )))
                .andExpect(model().attribute("gifts", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("point", is(100)),
                                hasProperty("food", hasProperty("name", is("bim bim")))
                        )
                )));
    }

    @Test
    void showSearchGiftViewTC0703() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("gift_search", "abc"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/list-gifts"))
                .andExpect(model().attribute("memberCard", hasProperty("id", is(1))))
                .andExpect(model().attribute("memberCard", hasProperty("point", is(1000))))
                .andExpect(model().attribute("memberCard", hasProperty("customer", hasProperty("name", is("ngo dinh ba")))))
                .andExpect(model().attribute("gifts", hasSize(0)));
    }

    @Test
    void showGiftBillTC0801() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift/1/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("quantity", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/gift-bill"))
                .andExpect(model().attribute("giftBill", hasProperty("orderGifts", hasItem(
                        allOf(
                                hasProperty("point", is(100)),
                                hasProperty("quantity", is(1)),
                                hasProperty("gift", hasProperty("food", hasProperty("name", is("bim bim"))))
                        )
                ))))
                .andExpect(model().attribute("giftBill", hasProperty("membershipCard", hasProperty("point", is(900)))));
    }

    @Test
    void showGiftBillTC0802() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift/1/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("quantity", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/gift-bill"))
                .andExpect(model().attribute("giftBill", hasProperty("orderGifts", hasItem(
                        allOf(
                                hasProperty("point", is(300)),
                                hasProperty("quantity", is(3)),
                                hasProperty("gift", hasProperty("food", hasProperty("name", is("bim bim"))))
                        )
                ))))
                .andExpect(model().attribute("giftBill", hasProperty("membershipCard", hasProperty("point", is(700)))));
    }

    @Test
    void showGiftBillTC0803() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exchange-gift/1/3").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("quantity", "4"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("not_enough_point", "Thẻ không đủ điểm"));
    }
}