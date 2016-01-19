package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by marc on 18/01/16.
 */
public class RectangleTest {

    @Test
    public void shouldReadAndWriteJson() throws IOException {
        Rectangle rectangle = new Rectangle(2, 2);

        ObjectMapper objectMapper1 = new ObjectMapper();
        System.out.println(objectMapper1.writeValueAsString(rectangle));
    }

}