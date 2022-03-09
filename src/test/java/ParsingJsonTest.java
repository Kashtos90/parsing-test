import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingJsonTest {

    @Test
    void jsonTest () throws Exception {

        String pathFileJson = "src/test/resources/sample3.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File (pathFileJson);
            Color color = mapper.readValue(file, Color.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
