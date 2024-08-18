package br.com.corcunda.golden_raspberry_awards.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest
@AutoConfigureMockMvc
class ProducersWinnerIntervalIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilmRepository repository;
    private MockMvc mock;

    @BeforeEach
    public void setup() throws Exception {
        this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    void givenFilmRoot_WhenMockMvc_ThenGetJsonResult() throws Exception {
        this.mock.perform(get("/films")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min").exists())
                .andExpect(jsonPath("$.min[*].producer").value(hasSize(1)))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.max").exists())
                .andExpect(jsonPath("$.max[*].producer").value(hasSize(2)))
                .andExpect(jsonPath("$.max[*].producer").value(hasItems("Matthew Vaughn", "Mitsuharu Ishii")))
                .andExpect(jsonPath("$.max[*].followingWin").value(hasItems(2015, 1995)))
                .andExpect(jsonPath("$.max[*].previousWin").value(hasItems(2002, 1982)))
                .andExpect(jsonPath("$.max[*].interval").value(hasItems(13, 13)));
    }

    @Test
    void givenApplicationStartup_WhenInvalidColumnValue_ThenReturnEmpty() throws Exception {

        repository.deleteAll();
        LoadDatabase loadDatabase = context.getBean(LoadDatabase.class);
        ReflectionTestUtils.setField(loadDatabase, "dbfile_path", "src/test/data/movielist_invalid_column_value.csv");
        //assertEquals("loadDatabase", loadDatabase.dbfile_path);
        loadDatabase.initDatabase(repository).run();

        //assertEquals("", repository.count());

        this.mock.perform(get("/films")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min").exists())
                .andExpect(jsonPath("$.min[*]").value(hasSize(0)))
                .andExpect(jsonPath("$.max").exists())
                .andExpect(jsonPath("$.max[*]").value(hasSize(0)));
    }

}
