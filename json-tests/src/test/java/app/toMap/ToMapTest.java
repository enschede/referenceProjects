package app.toMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by marc on 22/04/16.
 */
public class ToMapTest {

    @Test
    public void mapTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"name\":\"marc\",\"daughter\":{\"firstname\":\"yvette\", \"lastname\":\"enschede\"}}";
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});

        Assert.assertThat((String)map.get("name"), CoreMatchers.is("marc"));

        Assert.assertThat(map.get("daughter"), CoreMatchers.instanceOf(Map.class));
        Assert.assertThat((String)((Map)map.get("daughter")).get("firstname"), CoreMatchers.is("yvette"));
    }

}