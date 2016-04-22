package app.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by marc on 18/01/16.
 */
public class RectangleMapperTest {

    @Test
    public void shouldReadAndWriteJson() throws IOException {
        Rectangle rectangle = new Rectangle(2, 2);

        ObjectMapper objectMapper1 = new ObjectMapper();
        System.out.println(objectMapper1.writeValueAsString(rectangle));
    }

    @Test
    public void shouldWriteJsonWithMapper() throws IOException {
        Rectangle rectangle = new Rectangle(2, 2);

        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.addMixIn(Rectangle.class, RectangleMapper.class);

        Assert.assertThat(objectMapper1.writeValueAsString(rectangle), CoreMatchers.is("{\"width\":2,\"height\":2}"));
    }

    @Test
    public void shouldReadJsonWithMapper() throws IOException {

        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.addMixIn(Rectangle.class, RectangleMapper.class);

        Rectangle rectangle1 = objectMapper1.readValue("{\"width\":2,\"height\":2}", Rectangle.class);

        Assert.assertThat(rectangle1.getH(), CoreMatchers.is(2));
        Assert.assertThat(rectangle1.getW(), CoreMatchers.is(2));
    }
}