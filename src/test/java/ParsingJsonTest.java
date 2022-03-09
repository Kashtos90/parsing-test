import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingJsonTest {

    @Test
    void jsonTest () {

        String pathFileJson = "src/test/resources/sample3.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File (pathFileJson);
            Color color = mapper.readValue(file, Color.class);
            assertThat(color.color).isEqualTo("red");
            assertThat(color.value).isEqualTo("#f00");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
