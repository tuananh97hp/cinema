package com.cinema.cinema.controllers;

import com.cinema.cinema.CinemaApplication;
import com.cinema.cinema.models.Gift;
import com.cinema.cinema.models.GiftBill;
import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.models.OrderGift;
import com.cinema.cinema.repositories.GiftRepository;
import com.cinema.cinema.repositories.MemberShipCardRepository;
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

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@WebAppConfiguration
class BillGiftControllerTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @Autowired
    private GiftRepository giftRepository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void addBillGift0901() throws Exception {
        MembershipCard membershipCard = memberShipCardRepository.findById(1).get();
        Gift gift = giftRepository.findById(1).get();
        OrderGift orderGift = new OrderGift(gift, 3, 3 * gift.getPoint());
        membershipCard.setPoint(membershipCard.getPoint() - orderGift.getPoint());

        Set<OrderGift> setOrderGifts = new HashSet<>();
        setOrderGifts.add(orderGift);
        GiftBill giftBill = new GiftBill(setOrderGifts, membershipCard);
        orderGift.setGiftBill(giftBill);

        mvc.perform(MockMvcRequestBuilders.post("/bill-gift").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .sessionAttr("giftBill", giftBill))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/exchange-gift-done"));
    }
}