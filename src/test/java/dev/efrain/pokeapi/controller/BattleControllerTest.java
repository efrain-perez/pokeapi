package dev.efrain.pokeapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.efrain.pokeapi.service.error.ErrorEntity;
import dev.efrain.pokeapi.service.model.PokemonBattleInfo;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BattleControllerTest {

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
     * Test for happy path, just asserting names and multipliers.
     * @throws Exception
     */
    @Test
    public void getBattleInformationTest() throws Exception {
        MvcResult rawResult = mockMvc.perform(get("/battle")
                    .param("firstPokemon", "charmander")
                    .param("secondPokemon", "squirtle"))
                .andExpect(status().isOk())
                .andReturn();

        PokemonBattleInfo result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), PokemonBattleInfo.class);

        Assert.assertEquals(result.getFirstPokemon().getName(), "charmander");
        Assert.assertTrue(result.getFirstPokemon().getAttackMultiplier() == 0.5);
        Assert.assertEquals(result.getSecondPokemon().getName(), "squirtle");
        Assert.assertTrue(result.getSecondPokemon().getAttackMultiplier() == 2.0);
    }

    /**
     * Test for happy path, just asserting names and multipliers.
     * @throws Exception
     */
    @Test
    public void pokemonNotFoundTest() throws Exception {
        final String badPokemonName = "NotAPokemon";
        MvcResult rawResult = mockMvc.perform(get("/battle")
                .param("firstPokemon", badPokemonName)
                .param("secondPokemon", "squirtle"))
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorEntity result = objectMapper.readValue(rawResult.getResponse().getContentAsString(), ErrorEntity.class);

        Assert.assertEquals(result.getMessage(), "Pokemon with id or name '" + badPokemonName + "' not found");
    }

}
