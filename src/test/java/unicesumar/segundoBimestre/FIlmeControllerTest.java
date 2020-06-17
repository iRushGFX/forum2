package unicesumar.segundoBimestre;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmeController controller;

    @MockBean
    private FilmeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testeGet() throws  Exception{
        Filme filme = new Filme("1","testando",120);
        when(service.getById("1")).thenReturn(filme);

        mockMvc.perform(get("/api/filmes/1"))
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.titulo").value("Teste"))
        .andExpect(jsonPath("$.duracao").value(33))
        .andExpect(status().isOk());
    }

    @Test
    void testePost() throws Exception {
        when(service.save(ArgumentMatchers.any(Filme.class))).thenReturn("80");

        Map<String, String> filme  = new HashMap<String, String>() {{
            put("id", "80");
            put("titulo", "Titanic");
            put("duracao", "200");
        }};

        String filmeComoJson = objectMapper.writeValueAsString(filme);

        mockMvc.perform(post("/api/filmes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(filmeComoJson))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) content().string("80"));
    }

    @Test
    void testePut() throws Exception {
        when(service.save(ArgumentMatchers.any(Filme.class))).thenReturn("80");

        Map<String, String> filme  = new HashMap<String, String>() {{
            put("id", "80");
            put("titulo", "Titanic");
            put("duracao", "200");
        }};

        String filmeComoJson = objectMapper.writeValueAsString(filme);

        mockMvc.perform(put("/api/filmes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(filmeComoJson))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("80"));
    }

    @Test
    void testeDelete() throws Exception{
        mockMvc.perform(get("/api/filmes/1"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.titulo").value("Teste"))
                .andExpect(jsonPath("$.duracao").value(33))
                .andExpect(status().isOk());
    }
}