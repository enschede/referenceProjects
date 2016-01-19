package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by marc on 18/01/16.
 */
public class RectangleMapperTest {

    @Test
    public void shouldReadAndWriteJsonWithMapper() throws IOException {
        Rectangle rectangle = new Rectangle(2, 2);

        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.addMixIn(Rectangle.class, RectangleMapper.class);

        System.out.println(objectMapper1.writeValueAsString(rectangle));

        Rectangle rectangle1 = objectMapper1.readValue("{\"width\":2,\"height\":2}", Rectangle.class);
        System.out.println(rectangle1.toString());
    }
}