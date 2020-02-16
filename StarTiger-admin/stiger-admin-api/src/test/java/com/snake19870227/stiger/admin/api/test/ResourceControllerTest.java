package com.snake19870227.stiger.admin.api.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.http.RestResponseBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerTest {

    private String authorization;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void getAuthorization() throws Exception {
        Map<String, String> loginParams = new HashMap<>(2);
        loginParams.put("username", "snake");
        loginParams.put("password", "123456");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/process")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(objectMapper.writeValueAsString(loginParams))
        ).andDo(result -> {
            String respContent = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
            JsonNode respNode = objectMapper.readTree(respContent);
            authorization = respNode.get("data").get("accessToken").textValue();
        }).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testRes1() throws Exception {
        Assert.assertNotNull(authorization);

        RestResponse.DefaultRestResponse defaultSuccessRestResponse = RestResponseBuilder.createSuccessRestResp("res1");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/res1")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authorization)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8.name())
        ).andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(defaultSuccessRestResponse))
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }

    @Test
    public void testRes3() throws Exception {
        Assert.assertNotNull(authorization);

        RestResponse.DefaultRestResponse defaultSuccessRestResponse = RestResponseBuilder.createSuccessRestResp("res3");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/res3")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authorization)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8.name())
        ).andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(defaultSuccessRestResponse))
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }
}
