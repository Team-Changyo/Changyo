package com.shinhan.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinhan.api.api.controller.account.AccountController;
import com.shinhan.api.api.controller.trade.TradeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
}
