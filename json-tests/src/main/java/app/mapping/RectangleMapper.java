package app.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marc on 18/01/16.
 */
abstract class RectangleMapper {

    @JsonProperty("width") abstract int getW();
    @JsonProperty("height") abstract int getH();
    @JsonIgnore abstract int getSize();
}
