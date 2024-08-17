package br.com.corcunda.golden_raspberry_awards.film;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class)
public class ProducerWinnerIntervalFileValidation {

    @Autowired
    private WebApplicationContext context;
    @Mock
    private FilmRepository repository;

    private LoadDatabase loadDatabase;

    @BeforeEach
    private void setup() {
        this.loadDatabase = context.getBean(LoadDatabase.class);
    }

    @Test
    void givenApplicationStartup_WhenNoFile_ThenError() throws Exception {
        ReflectionTestUtils.setField(loadDatabase, "dbfile_path", "");
        assertThrows(IOException.class, () -> loadDatabase.initDatabase(null).run());
    }

    @Test
    void givenApplicationStartup_WhenInvalidLine_ThenIgoreAndLog(CapturedOutput output) throws Exception {
        ReflectionTestUtils.setField(loadDatabase, "dbfile_path", "src/test/data/movielist_invalid_line.csv");
        loadDatabase.initDatabase(null).run();
        assertTrue(output.getOut().contains(
                "Invalid Line at 3 with error: Incorrect number of tokens found in record: expected 5 actual 4"));
    }

    @Test
    void givenApplicationStartup_WhenInvalidColumnValue_ThenIgoreAndLog(CapturedOutput output) throws Exception {
        ReflectionTestUtils.setField(loadDatabase, "dbfile_path", "src/test/data/movielist_invalid_column_value.csv");
        loadDatabase.initDatabase(null).run();
        assertTrue(output.getOut().contains("Invalid Line at 3 with error: Unparseable number: invalid year"));
    }

}
