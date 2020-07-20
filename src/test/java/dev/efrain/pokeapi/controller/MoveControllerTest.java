package dev.efrain.pokeapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.efrain.pokeapi.service.error.ErrorEntity;
import dev.efrain.pokeapi.service.model.MoveTranslation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MoveControllerTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Test for happy path, just asserting response is not empty.
     * @throws Exception
     */
    @Test
    public void getSharedMovesTest() throws Exception {
        MvcResult rawResult = mockMvc.perform(get("/moves/shared")
                .param("pokemons", "charmander"))
                .andExpect(status().isOk())
                .andReturn();

        MoveTranslation[] result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), MoveTranslation[].class);
        Assert.assertTrue(result.length > 0);
    }

    /**
     * Test for Pokemon not found
     * @throws Exception
     */
    @Test
    public void pokemonNotFoundTest() throws Exception {
        final String pokemonNotValid = "Batman";
        MvcResult rawResult = mockMvc.perform(get("/moves/shared")
                .param("pokemons", pokemonNotValid))
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorEntity result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), ErrorEntity.class);
        Assert.assertTrue(result.getMessage().equals("Pokemon with id or name '" + pokemonNotValid + "' not found"));
    }

    /**
     * Test for Language not found
     * @throws Exception
     */
    @Test
    public void languageNotFoundTest() throws Exception {
        final String languageNotValid = "mexican";
        MvcResult rawResult = mockMvc.perform(get("/moves/shared")
                .param("pokemons", "charmander")
                .param("language", languageNotValid))
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorEntity result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), ErrorEntity.class);
        Assert.assertTrue(result.getMessage().equals("Language '" + languageNotValid + "' not found"));
    }

    /**
     * Test invalid offset
     * @throws Exception
     */
    @Test
    public void invalidOffsetTest() throws Exception {
        final Integer invalidOffset = -3;
        MvcResult rawResult = mockMvc.perform(get("/moves/shared")
                .param("pokemons", "charmander")
                .param("offset", invalidOffset.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorEntity result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), ErrorEntity.class);
        Assert.assertTrue(result.getMessage().equals("Offset value " + invalidOffset.toString() + " is not valid. Please use positive values only."));
    }


    /**
     * Test invalid offset
     * @throws Exception
     */
    @Test
    public void invalidPageSizeTest() throws Exception {
        final Integer invalidPageSize = -3;
        MvcResult rawResult = mockMvc.perform(get("/moves/shared")
                .param("pokemons", "charmander")
                .param("size", invalidPageSize.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorEntity result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), ErrorEntity.class);
        Assert.assertTrue(result.getMessage().equals("Page size " + invalidPageSize.toString() + " is not valid."));
    }


}
